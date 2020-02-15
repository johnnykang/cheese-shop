package au.id.kang.cheese.controller.product.cheese;

import au.id.kang.cheese.controller.product.CRUDController;
import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.domain.cheese.Cheese;
import au.id.kang.cheese.dto.product.cheese.CheeseDTO;
import au.id.kang.cheese.dto.product.mapper.CheeseMapper;
import au.id.kang.cheese.repository.cheese.CheeseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller("/product/cheese")
public class CheeseController implements CRUDController<CheeseDTO> {

    private final CheeseMapper mapper;
    @Inject
    ObjectMapper objectMapper;

    @Inject
    CheeseRepository cheeseRepository;

    public CheeseController() {
        mapper = Mappers.getMapper(CheeseMapper.class);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Put
    public CheeseDTO createProduct(@Body CheeseDTO cheeseDTO) {
        cheeseRepository.save(mapper.toCheese(cheeseDTO));
        return cheeseDTO;
    }

    @Override
    public List<CheeseDTO> listProducts() {
        final Iterable<Cheese> all = cheeseRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).map(cheese -> mapper.toCheeseDTO(cheese)).collect(Collectors.toList());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    public HttpResponse<CheeseDTO> getProduct(Long id) {
        final Optional<CheeseDTO> cheeseDTO = cheeseRepository.findById(id).map(cheese -> mapper.toCheeseDTO(cheese));

        if (cheeseDTO.isPresent()) {
            return HttpResponse.ok(cheeseDTO.get());
        }

        return HttpResponse.notFound();
    }

    @Delete("/{id}")
    public CheeseDTO deleteProduct(Long id){
        final Cheese entity = new Cheese();
        entity.setId(id);
        cheeseRepository.delete(entity);
        return mapper.toCheeseDTO(entity);
    }

    @Override
    public boolean canHandle(ProductType productType) {
        return productType == ProductType.CHEESE;
    }

    @Override
    @SneakyThrows
    public CheeseDTO transform(String json) {
        return objectMapper.readValue(json, CheeseDTO.class);
    }
}
