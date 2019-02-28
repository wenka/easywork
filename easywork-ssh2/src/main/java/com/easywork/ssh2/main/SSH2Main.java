package com.easywork.ssh2.main;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 09:02
 * Description:
 */
public class SSH2Main {

    private final static String USERNAME = "wk";

    private final static String PASSWORD = "abc123..";

    private final static String HOST = "192.168.111.128";

    private final static int PORT = 22;

    public static void main(String[] args) throws JSchException, IOException {
        JSch jSch = new JSch();
        Session session = jSch.getSession(USERNAME, HOST, PORT);
        session.setPassword(PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);// 为Session对象设置properties
        session.setTimeout(1500);// 设置超时
        session.connect();// 通过Session建立连接

        ChannelExec exec = (ChannelExec) session.openChannel("exec");
//        exec.setCommand("sudo -S -p  ''  docker ps -a");
//        OutputStream outputStream = exec.getOutputStream();

        exec.setErrStream(System.err);
        InputStream in = exec.getInputStream();
        exec.setCommand("ls -a");
        exec.connect();
        exec.start();
        exec.setCommand("ls -l");
        exec.connect();

//        outputStream.write((PASSWORD+"\n").getBytes());
//        outputStream.flush();

        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                System.out.print(new String(tmp, 0, i));
            }
            if(exec.isClosed()){
                System.out.println("exit-status: "+exec.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }

        exec.disconnect();
        session.disconnect();
    }
}
