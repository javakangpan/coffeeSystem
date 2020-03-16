package demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*import javax.xml.bind.ValidationException;*/
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
/**
 * ==> SpringMVC中的异常处理机制
 * SpringMVC异常解析
 * 核心接口
 *  HandlerExceptionResolver
 * 实现类
 *  SimpleMappingExceptionResolver
 *  DefaultHandlerExceptionResolver
 *  ResponseStatusExceptionResolver
 *  ExceptionHandlerExceptionResolver
 * ==> 异常处理方法
 * 处理方法
 *  @ExceptionHandler
 * 添加位置
 * @Controller / @RestController
 * @ControllerAdvice / @RestControllerAdvice
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> validationExceptionHandler(ValidationException validationException) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("message",validationException.getMessage());
        return map;
    }

}
