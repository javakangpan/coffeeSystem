package demo.service;

import demo.model.Coffee;
import demo.repository.CoffeeRepository;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Coffee")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

   public List<Coffee> findCoffeeByNames(List<String> names) {
       return coffeeRepository.findByNameInOrderById(names);
    }
    public Coffee saveCoffee(String name, Money price) {
      return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    /*
        after clearCache that has cache,why?
     */
    @Cacheable
    public Coffee findCoffeeByName(String name) {
       return coffeeRepository.findByName(name);
    }
    @CachePut
    public void updateCoffee(Coffee coffee) {
       coffeeRepository.updateCoffee(coffee.getName(),coffee.getPrice().getAmountMajorLong(),coffee.getId());
    }

    @CachePut
    public void updateCoffee(String name,long price,long id) {
        coffeeRepository.updateCoffee(name,price,id);
    }

    @Cacheable
    public List<Coffee> findAll() {
       return coffeeRepository.findAll(Sort.by("id"));
    }

    @CacheEvict
    public String clearCache() {
        return "clear";
    }

}
