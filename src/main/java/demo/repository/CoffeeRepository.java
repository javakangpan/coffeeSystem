package demo.repository;

import demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee,Long> {
    List<Coffee> findByNameInOrderById(List<String> names);
    List<Coffee> findByName(String name);
    @Query(value = "update t_coffee set name=:name,price=:price where id=:id",nativeQuery = true)
    @Modifying
    @Transactional
    void updateCoffee(@Param("name")String name,@Param("price")long price,@Param("id")long id);
/*    @Query(value = "update Coffee set name=:#{#coffee.name},price=:#{#coffee.price} where id=:#{#coffee.id}")
    void updateCoffee(@Param("coffee") Coffee coffee);*/
}
