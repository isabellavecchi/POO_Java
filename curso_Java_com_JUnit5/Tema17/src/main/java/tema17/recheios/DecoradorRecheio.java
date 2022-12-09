package tema17.recheios;

import tema17.IPizza;

public abstract class DecoradorRecheio implements IPizza {
    private IPizza pizza;

    public DecoradorRecheio(IPizza pizza) {
        if(pizza != null) {
            this.pizza = pizza;
        } else {
            throw new IllegalArgumentException("É preciso de uma base para adicionar ingredientes.");
        }
    }

    @Override
    public double getPrice() {
        return this.pizza.getPrice();
    }
}
