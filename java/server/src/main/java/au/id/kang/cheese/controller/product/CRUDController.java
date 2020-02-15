package au.id.kang.cheese.controller.product;

import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.dto.product.ProductDTO;
import io.micronaut.http.HttpResponse;

import java.util.List;

public interface CRUDController<T extends ProductDTO> {

    boolean canHandle(ProductType productType);

    T transform(String json);

    T createProduct(T dto);

    T deleteProduct(Long id);

    HttpResponse<T> getProduct(Long id);

    List<T> listProducts();

    T patchProduct(T transform);
}
