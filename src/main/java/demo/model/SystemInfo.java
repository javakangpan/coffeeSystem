package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 提供作者的信息 用于测试 分布式 Session
 * url: localhost:8080/SystemInfo
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component(value = "systemInfo")
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = -2573852727142303465L;
    @Autowired
    private Author author;
    @Value("${systemInfo.systemName}")
    public String systemName;
    @Value("${systemInfo.remarks}")
    public String remarks;
    @Value("${systemInfo.version}")
    public int version;
}
