package demo.service;

import com.google.gson.Gson;
import demo.model.Coffee;
import demo.repository.CoffeeRepository;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.List;

@Service
@CacheConfig(cacheNames = "Coffee")
@Transactional
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private JedisPool jedisPool;

    public List<Coffee> findCoffeeByNames(List<String> names) {
       return coffeeRepository.findByNameInOrderById(names);
    }
    public Coffee saveCoffee(String name, Money price) {
      return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    public List<Coffee> findCoffeeByName(String name) {
       return coffeeRepository.findByName(name);
    }

    public void updateCoffee(Coffee coffee) {
       coffeeRepository.updateCoffee(coffee.getName(),coffee.getPrice().getAmountMajorLong(),coffee.getId());
    }

    public void updateCoffee(String name,long price,long id) {
        coffeeRepository.updateCoffee(name,price,id);
    }

    @Cacheable
    public List<Coffee> findAll() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public Coffee findCoffeeInJedisById(long id) {
        Jedis jedis = jedisPool.getResource();
      /*  byte[] key = SerializationUtils.serialize(id);
        byte[] value = jedis.get(key);*/
        String key = String.valueOf(id);
        jedis.close();
        Gson gson = new Gson();
       /* SerializationUtils.deserialize(value);*/
        return gson.fromJson(jedis.get(key),Coffee.class);
    }

    @CacheEvict
    public void clearCache() {
    }

    public Coffee findById(long id) {
        Coffee coffee = findCoffeeInJedisById(id);
        return coffee == null
                ? coffeeRepository.getOne(id) : coffee;
    }

    public void insertAllIntoJedis() {
        clearCache();
        Jedis jedis = jedisPool.getResource();
        List<Coffee> coffees = findAll();
        coffees.forEach(coffee -> {
            Gson gson = new Gson();
            String key = String.valueOf(coffee.getId());
            String value = gson.toJson(coffee);
            /*byte[] value = SerializationUtils.serialize(coffee);
            byte[] key = SerializationUtils.serialize(coffee.getId());*/
            jedis.set(key,value);
        });
        jedis.close();
    }

}
