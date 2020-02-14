package au.id.kang.cheese.controller.product;

import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.dto.product.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.Link;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/product")
public class ProductController {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    List<CRUDController> productControllers;

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/list")
    public Optional<List<ProductDTO>> listProducts(@QueryValue("productType") ProductType type) {
        return productControllers.stream().filter(iProductController -> iProductController.canHandle(type)).findFirst().map(crudController -> crudController.listProducts());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public HttpResponse<ProductDTO> getProduct(Long id, @QueryValue("productType") ProductType productType) {
        final Optional<CRUDController> first = productControllers.stream().filter(iProductController -> iProductController.canHandle(productType)).findFirst();
        if (first.isPresent()) {
            return first.get().getProduct(id);
        }
        return HttpResponse.notFound();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Delete("/{id}")
    public HttpResponse<ProductDTO> deleteProduct(Long id, @QueryValue("productType") ProductType productType) {
        final Optional<CRUDController> first = productControllers.stream().filter(iProductController -> iProductController.canHandle(productType)).findFirst();
        if (first.isPresent()) {
            return HttpResponse.ok(first.get().deleteProduct(id));
        }
        return HttpResponse.notFound();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put
    public ProductDTO createProduct(@Body ProductDTO productDTO, @Body String rawBody) {
        final Optional<ProductDTO> productDTO1 = productControllers.stream().filter(iProductController -> iProductController.canHandle(productDTO.getProductType()))
                .findFirst().map(iProductController -> iProductController.createProduct(iProductController.transform(rawBody)));

        return productDTO1.orElseThrow(() -> new RuntimeException("unable to create the new product"));
    }

    private Link getLink(ProductDTO productDTO) {
        return Link.of(URI.create(String.format("/product/%d", productDTO.getId())));
    }
}
