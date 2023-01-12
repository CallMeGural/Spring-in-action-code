package pl.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.tacocloud.tacos.Ingredient;
import pl.tacocloud.tacos.Ingredient.Type;
import pl.tacocloud.tacos.User;
import pl.tacocloud.tacos.data.IngredientRepository;
import pl.tacocloud.tacos.data.UserRepository;

@SpringBootApplication
public class TacoCloudApplication implements CommandLineRunner {

    private final IngredientRepository repo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TacoCloudApplication(IngredientRepository repo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
                userRepository.save(new User("filip", passwordEncoder.encode("123"), "Filip","Czecha","Kraków","mp","30-042","123123123"));
            }
}
