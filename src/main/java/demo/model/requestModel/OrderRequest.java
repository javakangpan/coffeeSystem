package demo.model.requestModel;

import lombok.*;

import java.util.List;

@ToString
@Setter
@Getter
public class OrderRequest {
    private String customer;
    private List<String> items;
}
