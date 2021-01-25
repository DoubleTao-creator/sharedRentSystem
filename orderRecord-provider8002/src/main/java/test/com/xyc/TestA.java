package test.com.xyc;

import com.xtt.util.MailUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestA {
    @Autowired
    MailUtils mailUtils;
    @Test
    public void test(){
        System.out.println(mailUtils);
    }
}
