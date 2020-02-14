package au.id.kang.cheese.repository.cheese;

import au.id.kang.cheese.domain.cheese.Cheese;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface CheeseRepository extends CrudRepository<Cheese, Long> {
}
