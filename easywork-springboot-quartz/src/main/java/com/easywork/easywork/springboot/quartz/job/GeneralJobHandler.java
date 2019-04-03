package com.easywork.easywork.springboot.quartz.job;

import com.easywork.easywork.springboot.quartz.constant.JobDefinitionError;
import com.easywork.easywork.springboot.quartz.exception.JobException;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

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
            LocalDateTime end = LocalDateTime.now();
            Duration between = Duration.between(begin, end);
            LOGGER.info("job over. endTime = {}, cost = {}", end, between);
        } else {
            JobException jobException = JobException.newJobExce(JobDefinitionError.TASK_ID_MISSING);
            LOGGER.error("<ERROR> Task execution failed：{}", jobException.getMessage());
            throw jobException;
        }
    }
}
