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
        entity.setName("Brie");
        entity.setPrice(300d);
        entity.setColor(Color.RED);
        entity.setCheeseType("Brie");
        entity.setImage("https://cheese.com/media/img/cheese/Brie_PDCo3RG.jpg");
        cheeseRepository.save(entity);

        entity = new Cheese();
        entity.setName("American Cheese");
        entity.setPrice(500d);
        entity.setColor(Color.RED);
        entity.setCheeseType("Unknown");
        entity.setImage("https://cheese.com/media/img/cheese/503-american_cheese_slice.jpg");
        cheeseRepository.save(entity);

        entity = new Cheese();
        entity.setName("Pecorino Romano");
        entity.setPrice(600d);
        entity.setColor(Color.RED);
        entity.setCheeseType("Pecorino");
        entity.setImage("https://cheese.com/media/img/cheese/Pecorino_romano_cheese.jpg");
        cheeseRepository.save(entity);

        entity = new Cheese();
        entity.setName("Cheddar");
        entity.setPrice(300d);
        entity.setColor(Color.RED);
        entity.setCheeseType("Cheddar");
        entity.setImage("https://cheese.com/media/img/cheese/wiki/cheddar.jpg");
        cheeseRepository.save(entity);


        System.out.println();
    }
}
