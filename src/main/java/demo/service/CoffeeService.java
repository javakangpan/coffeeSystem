package demo.service;

import com.google.gson.Gson;
import demo.model.Coffee;
import demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.List;

/**
 * Spring Boot 缓存抽象
 * redis 缓存
 * 序列化 反序列化
 */
@Service
@CacheConfig(cacheNames = "Coffee")
@Transactional
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private RedisTemplate redisTemplate;

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

    /**
     * @Cacheable(value="", key="", condition="条件满足缓存")
     */
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

    /**
     * beforeInvocation=true 方法执行前清空缓存
     * allEntries=true 清除缓存所有元素
     */
    @CacheEvict
    public void clearCache() {
    }

    public Coffee findById(long id) {
        Coffee coffee = findCoffeeInJedisById(id);
        redisTemplate.opsForValue().set(id,coffee);
        log.info("coffee:{}",redisTemplate.opsForValue().get(id));
        return coffee == null
                ? coffeeRepository.getOne(id) : coffee;
    }

    public long getCoffeeCount() {
        return coffeeRepository.count();
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
