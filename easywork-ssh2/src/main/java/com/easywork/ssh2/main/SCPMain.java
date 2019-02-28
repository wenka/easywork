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
public class SCPMain {

    private final static String USERNAME = "wk";

    private final static String PASSWORD = "abc123..";

    private final static String HOST = "192.168.111.128";

    private final static int PORT = 22;

    private final static String L_PATH = "E:\\workspaces\\IdeaProjects\\easywork-parent\\easywork-ssh2\\src\\main\\java\\com\\easywork\\ssh2\\main\\SCPMain.java";
    private final static String R_PATH = "/home/wk/a.java";

    public static void main(String[] args) throws JSchException, IOException, SftpException {
        JSch jSch = new JSch();
        Session session = jSch.getSession(USERNAME, HOST, PORT);
        session.setPassword(PASSWORD);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);// 为Session对象设置properties
        session.setTimeout(1500);// 设置超时
        session.connect();// 通过Session建立连接

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        File file = new File(L_PATH);
        boolean exists = file.exists();
        if (exists){
            channelSftp.put(new FileInputStream(file),R_PATH,ChannelSftp.OVERWRITE);
        }

        channelSftp.quit();
        session.disconnect();
    }

    static int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if (b == 0) return b;
        if (b == -1) return b;

        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            }
            while (c != '\n');
            if (b == 1) { // error
                System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }

}
