package pl.tacocloud.tacos.web;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.tacocloud.tacos.Ingredient;
import pl.tacocloud.tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElseThrow();
    }
}
