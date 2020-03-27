package demo;

import demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MailServiceTest extends SpringBootDemoEmailApplicationTests {

    @Autowired
    private MailService mailService;
    /**
     * 测试简单邮件
     */
    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("13728897992@163.com", "这是一封简单邮件", "这是一封普通的SpringBoot测试邮件");
    }
}
