package demo.controller;

import demo.mapper.CustomerMapper;
import demo.model.Coffee;
import demo.model.Order;
import demo.model.requestModel.OrderRequest;
import demo.service.CoffeeService;
import demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "订单相关接口")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CustomerMapper customerMapper;

    @ResponseBody
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建客户订单",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE,httpMethod = "GET")
    public Order create(@RequestBody OrderRequest orderRequest) {
        List<Coffee> coffeeList = coffeeService.findCoffeeByNames(orderRequest.getItems());
        if(!coffeeList.isEmpty()){
            Coffee[] coffees = coffeeList.toArray(new Coffee[]{});
            return orderService.createOrder(orderRequest.getCustomer(),coffees);
        }
        return null;
    }
    /*@RequestMapping(value = "/",method = RequestMethod.GET)*/
    @GetMapping("/")
    @ResponseBody
    @ApiOperation("查询所有的订单")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("查询客户名")
    public String getCustomer(@PathVariable(name = "id") Long id){
       return customerMapper.findCustomer(id);
    }


}
