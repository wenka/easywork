package com.easywork.easywork.springboot.quartz.constant;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:54
 * Description:
 */
public class JobRunningError extends Error {
    public final static JobRunningError STEP_ERROR = new JobRunningError(3001, "运行失败");

    public JobRunningError(Integer code, String msg) {
        super(code, msg);
    }
}
