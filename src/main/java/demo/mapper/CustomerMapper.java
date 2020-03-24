package demo.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface CustomerMapper {
    @Select("select customer from t_order where id = #{id}")
    public String findCustomer(@Param("id") Long id);
}
