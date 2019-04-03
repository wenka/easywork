package com.easywork.easywork.springboot.quartz.exception;

import com.easywork.easywork.springboot.quartz.constant.Error;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:49
 * Description:
 */
public final class JobException extends RuntimeException {

    public JobException(String message) {
        super(message);
    }

    public static JobException newJobException(String message) {
        return new JobException(message);
    }

    public static JobException newJobExce(Error error) {
        return newJobException(error.getMsg());
    }
}
