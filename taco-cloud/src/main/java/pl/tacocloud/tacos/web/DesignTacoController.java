package pl.tacocloud.tacos.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.tacocloud.tacos.Ingredient;
import pl.tacocloud.tacos.Ingredient.Type;
import pl.tacocloud.tacos.Order;
import pl.tacocloud.tacos.Taco;
import pl.tacocloud.tacos.data.IngredientRepository;
import pl.tacocloud.tacos.data.TacoRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order") // pozwala na pozostanie obiektu order w sesji i bycie dostępnym w wielu żądaniach
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    /*@ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
         new Ingredient("FLTO","pszenna", Type.WRAP),
         new Ingredient("COTO","kukurydziana", Type.WRAP),
         new Ingredient("GRBF","mielona wołowina", Type.PROTEIN),
         new Ingredient("CARN","kawałki mięsa", Type.PROTEIN),
         new Ingredient("TMTO","pomidory w kostke", Type.VEGGIES),
         new Ingredient("LETC","sałata", Type.VEGGIES),
         new Ingredient("CHED","cheddar", Type.CHEESE),
         new Ingredient("JACK","Monterey Jack", Type.CHEESE),
         new Ingredient("SLSA","pikantny sos pomidorowy", Type.SAUCE),
         new Ingredient("SRCR","śmietana", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }*/

    @ModelAttribute(name = "taco") //gwarantuje utworzenie obiektu Taco w modelu
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {
//        model.addAttribute("design",new Taco()); //kopiowanie atrybutów z modelu do serwletu odpowiedzi
//        return "design";
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(Locale.ROOT),
                    filterByType(ingredients,type));
        }
        model.addAttribute("design",new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        //ModelAttribute - wartosc ma pochodzić z modelu
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "redirect:/design";
        }
        Taco savedTaco = tacoRepository.save(design);
        order.addDesign(savedTaco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
