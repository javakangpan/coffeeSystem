package demo.controller;

import demo.exception.FormValidationException;
import demo.model.Coffee;
import demo.model.requestModel.CoffeeRequest;
import demo.service.CoffeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequestMapping("/coffee")
@Api(tags = "咖啡相关接口")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    /**
     * upload file to update
     */
    @ResponseBody
    @PostMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("批量更新咖啡")
    public List<Coffee> batchAddCoffee(@RequestParam("file")MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();
        if(!file.isEmpty()){
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str = "";
                while ((str=bufferedReader.readLine())!=null){
                    String[] arr = StringUtils.split(str," ");
                    if(arr!=null && arr.length==2){
                        coffees.add(coffeeService.saveCoffee(arr[0],
                                Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            } catch (Exception e) {
                log.error("exception",e);
            } finally {
                IOUtils.closeQuietly(bufferedReader);
            }
        }
        return coffees;
    }
    /**
    Accept:application/json;charset=UTF-8
    Content-Type:application/json
     curl -X GET "http://localhost:8080/coffee/?name=latte" -H "accept: application/json;charset=UTF-8" -H "content-Type:application/json"
     **/
    @GetMapping(value = "/",params = "name" ,consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("根据咖啡名查询咖啡")
    public List<Coffee> findByName(@RequestParam(name = "name") String name) {
        log.info("findByName:{}",name);
        return coffeeService.findCoffeeByName(name);
    }

    @GetMapping(value = "/",params = "!name")
    @ResponseBody
    @ApiOperation("查询所有的咖啡")
    public List<Coffee> findAll() {
        log.info("findAll");
        return coffeeService.findAll();
    }

    @PostMapping(value = "/update")
    @ResponseBody
    @ApiOperation("更新咖啡")
    public void update(@Validated @RequestBody Coffee coffee) {
        log.info("coffee:{}",coffee);
        coffeeService.updateCoffee(coffee);
    }

    @GetMapping(path = "/{name}/{price}/{id}")
    @ResponseBody
    @ApiOperation("更新咖啡")
    public void update(@PathVariable(name="name")@Validated String name,
                       @PathVariable(name="price")long price,
                       @PathVariable(name="id")@Validated long id) {
        coffeeService.updateCoffee(name,price,id);
    }

    @RequestMapping(value = "/clear",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("清除缓存")
    public void clear() {
        coffeeService.clearCache();
    }

    /*@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)*/

    /**
     * Controller 中手工设置缓存
     * return ResponseEntity.ok().cacheControl(...).
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    @ApiOperation("根据ID查询咖啡")
    public ResponseEntity<Coffee> findById(@PathVariable(name = "id") long id) {
        Coffee coffee = coffeeService.findById(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(coffee);
    }

    /**
     *Valid -> BindingResult
     */
    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("保存咖啡")
    public Coffee saveCoffeeWithBindingResult(@Valid  CoffeeRequest coffeeRequest,
                                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.warn("BindingResultErrors:{}",bindingResult);
            throw new FormValidationException(bindingResult);
        }
        return coffeeService.saveCoffee(coffeeRequest.getName(),coffeeRequest.getPrice());
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("保存咖啡")
    public Coffee saveJsonCoffeeWithBindingResult(@Valid @RequestBody CoffeeRequest coffeeRequest,
                                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.warn("BindingResultErrors:{}",bindingResult);
            throw new ValidationException(bindingResult.toString());
        }
        return coffeeService.saveCoffee(coffeeRequest.getName(),coffeeRequest.getPrice());
    }


    @GetMapping(value = "/updateCache")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("更新咖啡")
    public void updateJedisCache() {
        try {
            coffeeService.insertAllIntoJedis();
        } catch (Exception e) {
            log.info("updateJedisCache:{}",e);
        }
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation("根据名称列表查询咖啡")
    public List<Coffee> findCoffeesByNames(@RequestBody List<String> names) {
        return coffeeService.findCoffeeByNames(names);
    }
}
