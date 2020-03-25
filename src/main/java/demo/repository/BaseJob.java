package demo.repository;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
