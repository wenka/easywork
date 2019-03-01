# 1. SSH2远程命令服务

使用 java 连接到一个sshd 服务器，进行远程命令操作远程服务器，文件传输等。

# 2. 内置依赖

```xml
<dependencies>
    <dependency>
        <groupId>com.jcraft</groupId>
        <artifactId>jsch</artifactId>
        <version>0.1.55</version>
    </dependency>
    
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.11.2</version>
    </dependency>
</dependencies>
```
# 3. 使用讲解

## 3.1 身份配置

com.easywork.ssh2.model.SSH2

```java
public class SSH2 {
    /**
     * 远程服务器登录名
     */
    private String username;
    
    /**
     * 远程服务器登录密码
     */
    private String password;
    
    /**
     * 远程服务器主机地址
     */
    private String host;
    
    private int port = 22;
}
```
## 3.2 创建链接客户端并获取对应服务

com.easywork.ssh2.util.SSH2Client

① 使用 **com.easywork.ssh2.util.SSH2Client.connect(SSH2 ssh2)** 创建一个 ssh2 客户端 _ssh2Clien_

② 使用 _ssh2Client_ 示例获取所需要的服务：

- CommandService： 命令服务
- SftpService：sft服务
    
## 3.3 CommandService 命令服务

com.easywork.ssh2.service.CommandService

- com.easywork.ssh2.service.CommandService.run(String cmd)：运行 cmd 命令
- com.easywork.ssh2.service.CommandService.runSudo(String cmd, String password)：使用 sudo 运行 cmd 命令

## 3.3 SftpService sft服务

com.easywork.ssh2.service.SftpService

- com.easywork.ssh2.service.SftpService.upload(String localFilePath, String remoteFilePath)：将本地 localFile 上传到 远程服务器 remoteFilePath。
- com.easywork.ssh2.service.SftpService.download(String remoteFilePath, String localFilePath)：将远程服务器 remoteFile 下载到本地 localFilePath。 

