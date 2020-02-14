package au.id.kang.cheese.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Product {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    private ProductType type;

    private Double price;

    private Double weight;

    private Color color;

    private String image;

}
