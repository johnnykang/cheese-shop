package au.id.kang.cheese.dto.product;

import au.id.kang.cheese.domain.Color;
import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.dto.ServiceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.http.hateoas.Link;
import lombok.Data;

import java.net.URI;

@Data
public class ProductDTO extends ServiceDTO {

    private Long id;

    private String name;

    private Color color;

    private ProductType productType;

    private Integer price;

    private URI image;

    @JsonIgnore
    public Link getLink() {
        return Link.of(URI.create(String.format("/product/%s/%d", getProductType().toString().toLowerCase(), getId())));
    }

}
