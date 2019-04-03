package com.easywork.easywork.springboot.quartz.task;

import com.easywork.easywork.springboot.quartz.model.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 04:02
 * Description:
 */
public interface IStepHandler {

    final Logger LOGGER = LoggerFactory.getLogger(IStepHandler.class);

    boolean executer(Step step);
}
