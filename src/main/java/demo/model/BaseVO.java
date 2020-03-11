package demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
/*
hibernate lazy (getOne) proxyObject cannot serializable
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class BaseVO  implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
