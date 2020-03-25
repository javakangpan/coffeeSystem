package demo.test;

import cn.hutool.core.date.DateUtil;
import demo.repository.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
@Slf4j
public class TestTask_02 implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) {
        log.error("TestTask_02 Job 执行时间: {}", DateUtil.now());
    }
}
