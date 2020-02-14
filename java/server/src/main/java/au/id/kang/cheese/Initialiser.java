package au.id.kang.cheese;


import au.id.kang.cheese.domain.Color;
import au.id.kang.cheese.domain.ProductType;
import au.id.kang.cheese.domain.cheese.Cheese;
import au.id.kang.cheese.repository.cheese.CheeseRepository;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;

import javax.inject.Inject;

@Context
public class Initialiser implements ApplicationEventListener<StartupEvent> {


    @Inject
    CheeseRepository cheeseRepository;

    @Override
    public void onApplicationEvent(StartupEvent event) {


        Cheese entity = new Cheese();
        entity.setName("Gouda");
        entity.setPrice(100d);
        entity.setColor(Color.YELLOW);
        entity.setCheeseType("Gouda");
        entity.setType(ProductType.CHEESE);
        entity.setImage("https://cheese.com/media/img/cheese/GOUDA_SqYJjRh.jpg");
        cheeseRepository.save(entity);

        entity = new Cheese();
        entity.setName("Cheese 2");
        entity.setPrice(200d);
        entity.setColor(Color.YELLOW);
        entity.setCheeseType("bar");
        cheeseRepository.save(entity);


        System.out.println();
    }
}
