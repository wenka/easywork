package com.easywork.ssh2.util;

import com.easywork.ssh2.model.SSH2;
import com.easywork.ssh2.service.CommandService;
import com.easywork.ssh2.service.SftpService;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 09:58
 * Description:
 */
public class SSH2Client {
    private final static Logger LOG = LogManager.getLogger(SSH2Client.class);

    private final static ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();

    private SSH2Client() {

    }

    /**
     * 建立连接
     *
     * @param ssh2
     * @return
     * @throws JSchException
     */
    public static SSH2Client connect(SSH2 ssh2) throws JSchException {
        LOG.info("===== ssh2 is connecting... =====");
        Session session = sessionThreadLocal.get();
        if (session == null) {
            JSch jSch = new JSch();
            session = jSch.getSession(ssh2.getUsername(), ssh2.getHost(), ssh2.getPort());
            session.setPassword(ssh2.getPassword());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);// 为Session对象设置properties
            session.setTimeout(ssh2.getSessionTimeOut());// 设置超时
            session.connect();// 通过Session建立连接
            sessionThreadLocal.set(session);
            LOG.info("===== ssh2 session created =====");
        }
        return new SSH2Client();
    }

    /**
     * 关闭连接
     */
    public static void close() {
        Session session = sessionThreadLocal.get();
        if (session != null) {
            session.disconnect();
            sessionThreadLocal.remove();
            LOG.info("===== ssh2 session disconnect =====");
        }
    }

    /**
     * 获取session
     *
     * @return
     */
    public Session getSession() {
        Session session = sessionThreadLocal.get();
        return session;
    }

    /**
     * 命令服务
     *
     * @return
     */
    public CommandService getCommandService() {
        return new CommandService(getSession());
    }

    /**
     * sftp 服务
     *
     * @return
     */
    public SftpService getSftpService() {
        return new SftpService(getSession());
    }
}
