package au.id.kang.cheese.controller.product;

import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.dto.product.ProductCollectionDTO;
import au.id.kang.cheese.dto.product.ProductDTO;
import com.google.common.truth.Truth;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ProductControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void listProducts() {
        final HttpResponse<ProductCollectionDTO> exchange = client.toBlocking().exchange(HttpRequest.GET("/product/list?productType=CHEESE"), ProductCollectionDTO.class);
        final ProductCollectionDTO body = exchange.body();

        assertThat(body).isNotNull();
        assertThat(body.getProducts()).hasSize(5);
    }

    @Test
    void getProduct() {
        final HttpResponse<ProductDTO> exchange = client.toBlocking().exchange(HttpRequest.GET("/product/1?productType=CHEESE"), ProductDTO.class);
        final ProductDTO body = exchange.body();

        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(1);

        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/product/999?productType=CHEESE"));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    void createAndDeleteProduct() {
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setName("test product");
        productDTO.setPrice(1000);
        productDTO.setProductType(ProductType.CHEESE);
        final HttpResponse<ProductDTO> afterCreate = client.toBlocking().exchange(HttpRequest.PUT("/product", productDTO), ProductDTO.class);

        final Long newProductId = afterCreate.body().getId();
        Truth.assertThat(newProductId).isNotNull();
        Truth.assertThat(afterCreate.body().getName()).isEqualTo("test product");

        HttpResponse<ProductDTO> exchange = client.toBlocking().exchange(HttpRequest.GET(String.format("/product/%d?productType=CHEESE", newProductId)), ProductDTO.class);
        Truth.assertThat(exchange.getStatus()).isEqualTo(HttpStatus.OK);

        exchange = client.toBlocking().exchange(HttpRequest.DELETE(String.format("/product/%d?productType=CHEESE", newProductId)), ProductDTO.class);
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET(String.format("/product/%d?productType=CHEESE", newProductId)));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    void patchProduct() {
        HttpResponse<ProductDTO> exchange = client.toBlocking().exchange(HttpRequest.GET("/product/1?productType=CHEESE"), ProductDTO.class);
        ProductDTO body = exchange.body();

        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(1);
        assertThat(body.getName()).isEqualTo("Gouda");

        body.setName("name changed");
        exchange = client.toBlocking().exchange(HttpRequest.PATCH("/product/1", body), ProductDTO.class);
        assertThat(exchange.getStatus()).isEqualTo(HttpStatus.OK);

        exchange = client.toBlocking().exchange(HttpRequest.GET("/product/1?productType=CHEESE"), ProductDTO.class);
        body = exchange.body();

        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(1);
        assertThat(body.getName()).isEqualTo("name changed");

    }
}
