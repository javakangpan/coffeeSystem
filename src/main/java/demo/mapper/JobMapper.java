package demo.mapper;

import demo.model.JobAndTrigger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
}
