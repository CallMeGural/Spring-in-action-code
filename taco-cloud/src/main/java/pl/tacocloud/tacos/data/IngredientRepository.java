package pl.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tacocloud.tacos.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String> {
}
