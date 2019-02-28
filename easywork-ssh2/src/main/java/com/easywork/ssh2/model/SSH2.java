package com.easywork.ssh2.model;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 10:03
 * Description:
 */
public class SSH2 {

    private String username;

    private String password;

    private String host;

    private int port = 22;

    private int sessionTimeOut = 1500;

    public String getUsername() {
        return username;
    }

    public SSH2 setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SSH2 setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHost() {
        return host;
    }

    public SSH2 setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public SSH2 setPort(int port) {
        this.port = port;
        return this;
    }

    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

    public SSH2 setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
        return this;
    }

    @Override
    public String toString() {
        return "SSH2{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", sessionTimeOut=" + sessionTimeOut +
                '}';
    }
}
