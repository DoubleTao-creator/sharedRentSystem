package com.xyc;

import entity.FTPConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
public class test {
    @Test
    public void test0126() throws IOException {

        FTPConstants fc = new FTPConstants();
        fc.setHost("192.168.18.1");
        fc.setUsername("Yichan");
        fc.setPassword("001017");
        fc.setFilename("xyc.png");
        fc.setInput(new FileInputStream(new File("C:\\Users\\徐一婵\\Desktop\\x.jpg")));

        System.out.println(fc.toString());

        Boolean result= PhotoUtils.uploadFile(fc);
        System.out.println(result);
    }
}
