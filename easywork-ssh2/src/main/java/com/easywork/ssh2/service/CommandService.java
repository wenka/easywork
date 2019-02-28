package com.easywork.ssh2.service;

import com.easywork.ssh2.ChannelType;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 09:47
 * Description:命令服务
 */
public class CommandService {

    private final static Logger LOG = LogManager.getLogger(CommandService.class);

    private Session session;

    private ChannelExec channelExec;

    public CommandService(Session session) {
        this.session = session;
    }

    /**
     * 运行
     *
     * @param cmd
     */
    public void run(String cmd) throws IOException, JSchException {
        LOG.info("===== run：{} =====", cmd);
        this.openChannel();
        channelExec.setCommand(cmd);
        channelExec.connect();
        this.readInputStream(channelExec.getInputStream());
        channelExec.disconnect();
    }

    /**
     * 运行 sudo
     *
     * @param cmd
     */
    public void runSudo(String cmd, String password) throws IOException, JSchException {
        LOG.info("===== run：{} =====", cmd);
        this.openChannel();
        OutputStream outputStream = channelExec.getOutputStream();
        channelExec.setCommand("sudo -S -p  '' " + cmd);
        channelExec.connect();
        outputStream.write((password + "\n").getBytes());
        outputStream.flush();
        outputStream.close();
        this.readInputStream(channelExec.getInputStream());
        channelExec.disconnect();
    }

    /**
     * 打开连接频道
     *
     * @throws JSchException
     */
    private void openChannel() throws JSchException {
        channelExec = (ChannelExec) session.openChannel(ChannelType.EXEC);
        channelExec.setErrStream(System.err);
        LOG.info("===== ssh2 channelExec opened =====");
    }

    private void readInputStream(InputStream in) throws IOException {
        LOG.info("***************************↓result↓**********************************");
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                LOG.info(new String(tmp, 0, i));
            }
            if (channelExec.isClosed()) {
                LOG.error("exit-status: {}", channelExec.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
        in.close();
        LOG.info("***************************↑result↑**********************************");
    }
}
