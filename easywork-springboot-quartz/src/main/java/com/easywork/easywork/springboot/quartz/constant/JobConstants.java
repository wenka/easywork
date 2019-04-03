package com.easywork.easywork.springboot.quartz.constant;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 05:29
 * Description:
 */
public class JobConstants {


    // 等待中
    public final static Integer WAITING = Integer.valueOf(0);

    // 运行中
    public final static Integer RUNNING = Integer.valueOf(1);

    // 成功
    public final static Integer SUCCESS = Integer.valueOf(2);

    // 失败
    public final static Integer FAILURE = Integer.valueOf(-1);
}
