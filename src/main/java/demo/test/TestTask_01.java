package demo.test;

import cn.hutool.core.date.DateUtil;
import demo.repository.BaseJob;
import demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TestTask_01 implements BaseJob {
    @Autowired
    private MailService mailService;
    @Override
    public void execute(JobExecutionContext context) {
        //mailService.sendSimpleMail("@163.com", "", "");
        log.error("TestTask_01 Job 执行时间: {}", DateUtil.now());
    }
}
