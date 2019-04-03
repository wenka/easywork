package com.easywork.easywork.springboot.quartz.constant;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/04/03  下午 02:54
 * Description:
 */
public class CommonError extends Error {
    public final static CommonError O = new CommonError(2001, "对象实例化失败");

    public CommonError(Integer code, String msg) {
        super(code, msg);
    }
}
