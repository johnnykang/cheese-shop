package au.id.kang.cheese.dto.product.cheese;

import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.dto.product.ProductDTO;
import lombok.Data;

@Data
public class CheeseDTO extends ProductDTO {

    private Double weight;

    private String cheeseType;

    @Override
    public ProductType getProductType() {
        return ProductType.CHEESE;
    }
}
