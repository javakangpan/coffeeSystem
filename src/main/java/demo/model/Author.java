package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 作者信息
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Author implements Serializable {
    /*
    Alt + Enter -> serialVersionUID
     */
    private static final long serialVersionUID = -1423411920500246312L;
    @Value("${author.name}")
    private String name;
    @Value("${author.sex}")
    private String sex;
    @Value("${author.age}")
    private int age;
    @Value("${author.phoneNumber}")
    private String phoneNumber;
}
