package com.easywork.ssh2.exception;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/01  下午 05:16
 * Description:
 */
public class SSH2Exception extends RuntimeException {

    private String message;


    public SSH2Exception(String message) {
        super(message);
        this.message = message;
    }

    public static SSH2Exception createException(String message) {
        return new SSH2Exception(message);
    }
}
