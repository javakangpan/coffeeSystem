package demo.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ORDER")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Order extends BaseVO implements Serializable {
    private static final long serialVersionUID = -4259471150047396318L;
    private String customer;
    @ManyToMany
    @OrderBy("id")
    @JoinTable(name = "T_ORDER_COFFEE")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;

}
