package au.id.kang.cheese.dto.product.mapper;

import au.id.kang.cheese.domain.Product;
import au.id.kang.cheese.domain.cheese.Cheese;
import au.id.kang.cheese.dto.product.ProductDTO;
import au.id.kang.cheese.dto.product.cheese.CheeseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.URI;

@Mapper
public interface CheeseMapper {

    @Mapping(target = "image", expression = "java(convertToUri(product))")
    public CheeseDTO toCheeseDTO(Cheese product);

    default URI convertToUri(Product product) {
        if (product.getImage() != null)
            return URI.create(product.getImage());
        return null;
    }

    default String convertFromUri(ProductDTO productDto) {
        if (productDto.getImage() != null) {
            return productDto.getImage().toString();
        }

        return null;
    }

    @Mapping(target = "image", expression = "java(convertFromUri(cheeseDTO))")
    public Cheese toCheese(CheeseDTO cheeseDTO);
}
