package com.easywork.easywork.springboot.quartz.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:54
 * Description:
 */
public class JobDefinitionError extends Error {
    public final static JobDefinitionError TASK_ID_MISSING = new JobDefinitionError(1001, "未定义任务id");

    public JobDefinitionError(Integer code, String msg) {
        super(code, msg);
    }
}
