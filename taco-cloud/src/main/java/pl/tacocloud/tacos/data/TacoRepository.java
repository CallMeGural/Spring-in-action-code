package pl.tacocloud.tacos.data;

import pl.tacocloud.tacos.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
