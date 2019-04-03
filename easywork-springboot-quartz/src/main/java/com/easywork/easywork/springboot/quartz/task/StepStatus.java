package com.easywork.easywork.springboot.quartz.task;

import com.easywork.easywork.springboot.quartz.model.Step;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 05:27
 * Description:
 */
@Data
@ToString
@Accessors(chain = true)
public class StepStatus {

    private Step step;

    private Object result;

    private boolean success;
}
