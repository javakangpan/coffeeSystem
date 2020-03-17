package demo.controller;

import demo.model.Coffee;
import demo.model.Order;
import demo.model.requestModel.OrderRequest;
import demo.service.CoffeeService;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @ResponseBody
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody OrderRequest orderRequest) {
        List<Coffee> coffeeList = coffeeService.findCoffeeByNames(orderRequest.getItems());
        if(!coffeeList.isEmpty()){
            Coffee[] coffees = coffeeList.toArray(new Coffee[]{});
            return orderService.createOrder(orderRequest.getCustomer(),coffees);
        }
        return null;
    }
    @RequestMapping("/")
    @ResponseBody
    public List<Order> findAll() {
        return orderService.findAll();
    }

}
