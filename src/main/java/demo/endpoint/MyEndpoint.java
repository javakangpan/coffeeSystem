package demo.endpoint;

import com.google.gson.Gson;
import demo.mapper.QrtzJobDetailsMapper;
import demo.service.CoffeeService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "systemInfo")
@Builder
public class MyEndpoint {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;
    @ReadOperation
    public Map<String,String> systemInfo() {
        Gson gson = new Gson();
        Map<String,String> map = new HashMap<>();
        map.put("name","kangpan");
        map.put("sex","boy");
        map.put("age","28");
        map.put("phoneNumber","13728897992");
        map.put("systemName","coffeeSystem");
        map.put("version","1");
        map.put("coffee",gson.toJson(coffeeService.findAll()));
        map.put("task",qrtzJobDetailsMapper.mybatiesXMLTest());
        return map;
    }
}
