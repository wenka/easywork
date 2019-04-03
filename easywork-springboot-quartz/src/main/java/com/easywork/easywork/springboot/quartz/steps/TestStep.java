package com.easywork.easywork.springboot.quartz.steps;

import com.easywork.easywork.springboot.quartz.model.Step;
import com.easywork.easywork.springboot.quartz.task.IStepHandler;
import com.easywork.easywork.springboot.quartz.task.StepStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 04:04
 * Description:
 */
public class TestStep implements IStepHandler {

    @Override
    public boolean executer(Step step) throws RuntimeException{
        this.LOGGER.info("step = {}", step);
        return true;
    }
}
