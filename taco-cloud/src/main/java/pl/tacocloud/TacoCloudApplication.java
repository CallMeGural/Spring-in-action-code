package pl.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.tacocloud.tacos.Ingredient;
import pl.tacocloud.tacos.Ingredient.Type;
import pl.tacocloud.tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication implements CommandLineRunner {

    private final IngredientRepository repo;

    public TacoCloudApplication(IngredientRepository repo) {
        this.repo = repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

            @Override
            public void run(String... args) {
                repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
            }
}
