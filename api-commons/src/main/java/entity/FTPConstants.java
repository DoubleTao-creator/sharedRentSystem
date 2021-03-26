package entity;
/**
 * @author xtt
 * @date 2020/1/17
 * @info FTP常量信息封装
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FTPConstants {
    /**
     * 主机
     */
    private String host="8.140.3.126";
    /**
     * 端口
     */
    private Integer port=21;
    /**
     * 用户名
     */
    private String  username="root";
    /**
     * 密码
     */
    private String password="xuyichan.1017.";
    /**
     * 文件上传路径
     */
    private String filepath="/photo";
    /**
     * 文件名
     */
    private String filename;
    /**
     * 输入流
     */
    private InputStream input;
}
