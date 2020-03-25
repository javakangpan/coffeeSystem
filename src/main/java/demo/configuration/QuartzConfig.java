package demo.configuration;

import demo.test.TestTaskOne;
import demo.test.TestTaskTwo;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration*/
public class QuartzConfig {
    /*@Bean
    public JobDetail jobDetail_one(){
        return JobBuilder.newJob(TestTaskOne.class).withIdentity("taskOne").storeDurably(true).build();
    }
    @Bean
    public JobDetail jobDetail_two(){
        return JobBuilder.newJob(TestTaskTwo.class).withIdentity("taskTwo").storeDurably(true).build();
    }*/

//    @Bean
//    public Trigger taskTrigger_one() {
//        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(jobDetail_one())
//                .withIdentity("taskOne")
//                .withSchedule(simpleScheduleBuilder).build();
//    }
//    @Bean
//    public Trigger testQuartzTrigger2() {
//        //cron方式，每隔5秒执行一次
//        return TriggerBuilder.newTrigger().forJob(jobDetail_two())
//                .withIdentity("taskTwo")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
//    }
}
