package demo.model.requestModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class CoffeeRequest {
    @NotNull
    private Money price;
    @NotEmpty
    private String name;
}
