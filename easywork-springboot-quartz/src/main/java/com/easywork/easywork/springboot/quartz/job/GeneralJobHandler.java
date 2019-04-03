package com.easywork.easywork.springboot.quartz.job;

import com.easywork.easywork.springboot.quartz.constant.CommonError;
import com.easywork.easywork.springboot.quartz.constant.JobDefinitionError;
import com.easywork.easywork.springboot.quartz.constant.JobRunningError;
import com.easywork.easywork.springboot.quartz.exception.JobException;
import com.easywork.easywork.springboot.quartz.model.Step;
import com.easywork.easywork.springboot.quartz.model.Task;
import com.easywork.easywork.springboot.quartz.task.IStepHandler;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:03
 * Description:
 */
public class GeneralJobHandler implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(GeneralJobHandler.class);
    private final static String TASKID = "taskId";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime begin = LocalDateTime.now();
        LOGGER.info("job begin.  beginTime = {}", begin);
        LOGGER.info("jobExecutionContext = {}", jobExecutionContext.toString());
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        if (!mergedJobDataMap.isEmpty()) {
            String taskId = (String) mergedJobDataMap.get(TASKID);
            LOGGER.info("task id = {}", taskId);

            // *************************************************** //
            boolean flag = true;
            URL resource = GeneralJobHandler.class.getClassLoader().getResource("tasks/taskFiles/" + taskId + ".xml");
            File file = new File(resource.getFile());
            if (file.exists()) {
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    Task task = (Task) unmarshaller.unmarshal(file);
                    LOGGER.info("task = {}", task);
                    List<Step> steps = task.getSteps();
                    if (steps != null && !steps.isEmpty()) {
                        for (Step step : steps) {
                            Class<? extends IStepHandler> processor = step.getProcessor();
                            try {
                                IStepHandler stepHandler = processor.newInstance();
                                boolean executer = stepHandler.executer(step);
                                Assert.state(executer, JobRunningError.STEP_ERROR.getMsg());
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                                throw JobException.newJobExce(CommonError.O);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                                throw JobException.newJobExce(CommonError.O);
                            }
                        }
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                    JobException jobException = JobException.newJobExce(JobDefinitionError.TASK_FILE_READ_FAIL);
                    throw jobException;
                }
            } else {
                JobException jobException = JobException.newJobExce(JobDefinitionError.TASK_FILE_MISSING);
                throw jobException;
            }
            // *************************************************** //

            LocalDateTime end = LocalDateTime.now();
            Duration between = Duration.between(begin, end);
            LOGGER.info("job over. endTime = {}, cost = {}", end, between);
        } else {
            JobException jobException = JobException.newJobExce(JobDefinitionError.TASK_ID_MISSING);
            throw jobException;
        }
    }
}
