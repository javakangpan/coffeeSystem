package demo.service;

import demo.model.Coffee;
import demo.model.Order;
import demo.model.OrderState;
import demo.repository.OrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@org.springframework.transaction.annotation.Transactional
@Slf4j
public class OrderService implements MeterBinder {

    @Autowired
    private OrderRepository orderRepository;

    private Counter orderCounter = null;

    public Order createOrder(String customer, Coffee...coffee) {
        Order order = Order.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffee)))
                .state(OrderState.INIT)
                .build();
        Order saved = orderRepository.save(order);
        log.info("New Order: {}", saved);
        orderCounter.increment();
        return saved;
    }

    /**
     * 通过 Micrometer 获取运行数据
     * http://localhost:8080/actuator/metrics
     * http://localhost:8080/actuator/metrics/order.count
     */
    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.orderCounter = meterRegistry.counter("order.count");
    }

    public List<Order> findAll() {
        return orderRepository.findAll(Sort.by("id"));
    }
}
