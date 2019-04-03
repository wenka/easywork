package com.easywork.easywork.springboot.quartz.exception;

import com.easywork.easywork.springboot.quartz.constant.Error;
import com.easywork.easywork.springboot.quartz.job.GeneralJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:49
 * Description:
 */
public final class JobException extends RuntimeException {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobException.class);

    public JobException(String message) {
        super(message);
    }

    public static JobException newJobException(String message) {
        return new JobException(message);
    }

    public static JobException newJobExce(Error error) {
        LOGGER.error("<ERROR> Task execution failed：{}", error.getMsg());
        return newJobException(error.getMsg());
    }
}
