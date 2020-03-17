package demo.endpoint;

import lombok.Builder;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "systemInfo")
@Builder
public class MyEndpoint {
    @ReadOperation
    public Map<String,String> systemInfo() {
        Map<String,String> map = new HashMap<>();
        map.put("name","kangpan");
        map.put("sex","boy");
        map.put("age","28");
        map.put("phoneNumber","13728897992");
        map.put("systemName","coffeeSystem");
        map.put("version","1");
        return map;
    }
}
