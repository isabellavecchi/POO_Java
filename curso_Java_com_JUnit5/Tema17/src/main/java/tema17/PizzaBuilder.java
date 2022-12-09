package tema17;

import tema17.recheios.RecheioDeBacon;
import tema17.recheios.RecheioDeQueijo;

public class PizzaBuilder {
    private IPizza pizza;

    public PizzaBuilder() {
        this.pizza = new PizzaBase();
    }

    public static PizzaBuilder constructor() {
        return new PizzaBuilder();
    }

    public PizzaBuilder addQueijo() {
        this.pizza = new RecheioDeQueijo(pizza);
        return this;
    }

    public PizzaBuilder addBacon() {
        this.pizza = new RecheioDeBacon(this.pizza);
        return this;
    }

    public IPizza build() {
        return this.pizza;
    }
}
