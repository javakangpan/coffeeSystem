package demo.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.joda.money.Money;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "T_COFFEE")
@SuperBuilder/*use the parent property*/
public class Coffee extends BaseVO implements Serializable {
    private static final long serialVersionUID = 1881031061136727409L;
    @NotBlank
    private String name;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount"
    ,parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
/*    @Max(value = 1000,message = "price is hight")
    @Min(value = 10,message = "price is low")*/
    private Money price;
}
