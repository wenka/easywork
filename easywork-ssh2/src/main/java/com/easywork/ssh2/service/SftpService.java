package com.easywork.ssh2.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 11:17
 * Description:
 */
public class SftpService {

    private final static Logger LOG = LogManager.getLogger(SftpService.class);


    private Session session;

    private ChannelSftp channelSftp;

    public SftpService(Session session) {
        this.session = session;
    }

    public void init() throws JSchException {
        channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        LOG.info("===== ssh2 channelSftp opened =====");
    }

    /**
     * 上传
     *
     * @param localFilePath
     * @param remoteFilePath
     */
    public void upload(String localFilePath, String remoteFilePath) throws FileNotFoundException, JSchException {
        LOG.info("localFilePath:{}\nremoteFilePath:{}", localFilePath, remoteFilePath);
        this.init();
        File file = new File(localFilePath);
        boolean exists = file.exists();
        if (exists) {
            try {
                channelSftp.put(new FileInputStream(file), remoteFilePath, ChannelSftp.OVERWRITE);
                LOG.info("upload success!!!");
            } catch (SftpException e) {
                LOG.error("===== ssh2 upload error =====");
                e.printStackTrace();
            }
        } else {
            LOG.error("===== localFilePath:{} is not existed=====", localFilePath);
        }
        channelSftp.quit();
    }

    /**
     * 下载
     *
     * @param localFilePath
     * @param remoteFilePath
     */
    public void download(String remoteFilePath, String localFilePath) throws JSchException {
        LOG.info("localFilePath:{}\nremoteFilePath:{}", localFilePath, remoteFilePath);
        this.init();
        try {
            channelSftp.get(remoteFilePath,localFilePath);
            LOG.info("download success!!!");
        } catch (SftpException e) {
            LOG.error("===== ssh2 download error =====");
            e.printStackTrace();
        }
        channelSftp.quit();
    }
}
