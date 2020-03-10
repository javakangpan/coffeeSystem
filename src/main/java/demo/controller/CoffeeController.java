package demo.controller;

import demo.model.Coffee;
import demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    /**
     * upload file to update
     */
    @ResponseBody
    @PostMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
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
     **/
    @GetMapping(value = "/",params = "name" ,consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Coffee findByName(@RequestParam(name = "name") String name) {
        log.info("findByName:{}",name);
        return coffeeService.findCoffeeByName(name);
    }
    /*
    path has no name to request
     */
    @GetMapping(value = "/",params = "!name")
    @ResponseBody
    public List<Coffee> findAll() {
        log.info("findAll");
        return coffeeService.findAll();
    }

    /**
     * java.lang.NullPointerException: null
     * FAILED toString()
     */
    @PostMapping(value = "/update")
    public void update(@Validated @RequestBody Coffee coffee) {
        log.info("coffee:{}",coffee);
        coffeeService.updateCoffee(coffee);
    }
/*   if the method is void that has:
    {
        "timestamp": "2020-03-10T08:39:32.282+0000",
            "status": 404,
            "error": "Not Found",
            "message": "No message available",
            "path": "/coffee/espresso/2001/1"
    }*/
    @GetMapping(path = "/{name}/{price}/{id}")
    public String update(@PathVariable(name="name")@Validated String name,
                       @PathVariable(name="price")long price,
                       @PathVariable(name="id")@Validated long id) {
        coffeeService.updateCoffee(name,price,id);
        return "update";
    }

    @RequestMapping(value = "/clear",method = RequestMethod.GET)
    @ResponseBody
    public String clear() {
       return coffeeService.clearCache();
    }
}
