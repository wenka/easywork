import com.easywork.ssh2.model.SSH2;
import com.easywork.ssh2.service.SftpService;
import com.easywork.ssh2.util.SSH2Client;
import com.jcraft.jsch.JSchException;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 11:17
 * Description:
 */
public class ScpTest {
    private final static String USERNAME = "wk";

    private final static String PASSWORD = "abc123..";

    private final static String HOST = "192.168.111.128";

    private final static String localFile = "E:\\workspaces\\IdeaProjects\\easywork-parent\\easywork-ssh2\\src\\test\\java\\ScpTest2.java";
    private final static String remoteFile = "/home/wk/a.java";

    @Test
    public void upload() throws JSchException, FileNotFoundException {
        SSH2 ssh2 = new SSH2().setUsername(USERNAME).setPassword(PASSWORD).setHost(HOST);
        SftpService sftpService = SSH2Client.connect(ssh2).getSftpService();
        sftpService.upload(localFile, remoteFile);
        SSH2Client.close();
    }

    @Test
    public void downLoad() throws JSchException, FileNotFoundException {
        SSH2 ssh2 = new SSH2().setUsername(USERNAME).setPassword(PASSWORD).setHost(HOST);
        SftpService sftpService = SSH2Client.connect(ssh2).getSftpService();
        sftpService.download(remoteFile,localFile);
        SSH2Client.close();
    }
}
