package au.id.kang.cheese.domain.cheese;

import au.id.kang.cheese.domain.Product;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Cheese extends Product {

    private Double weight;

    private String cheeseType;

}
