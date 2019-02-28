import com.easywork.ssh2.model.SSH2;
import com.easywork.ssh2.service.CommandService;
import com.easywork.ssh2.util.SSH2Client;
import com.jcraft.jsch.JSchException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/02/28  上午 10:23
 * Description:
 */
public class CommandTest {

    private final static String USERNAME = "wk";

    private final static String PASSWORD = "abc123..";

    private final static String HOST = "192.168.111.128";

    @Test
    public void test() throws JSchException, IOException {
//        SSH2 ssh2 = new SSH2().setUsername(USERNAME).setPassword(PASSWORD).setHost(HOST);
//        SSH2Client connect = SSH2Client.connect(ssh2);
//        CommandService commandService = connect.getCommandService();
//        commandService.run("ls -a");
//        SSH2Client.close();
    }

    @Test
    public void testWidthSudo() throws JSchException, IOException {
//        SSH2 ssh2 = new SSH2().setUsername(USERNAME).setPassword(PASSWORD).setHost(HOST);
//        SSH2Client connect = SSH2Client.connect(ssh2);
//        CommandService commandService = connect.getCommandService();
//        commandService.runSudo("docker ps -a", PASSWORD);
//        SSH2Client.close();
    }

}
