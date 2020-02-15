package au.id.kang.cheese.dto.product;

import au.id.kang.cheese.dto.ServiceCollectionDTO;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ProductCollectionDTO extends ServiceCollectionDTO {

    private List<ProductDTO> products = Collections.emptyList();

    public ProductCollectionDTO() {
    }

    public ProductCollectionDTO(List<ProductDTO> products) {
        this.products = products;
    }
}
