package tema17.recheios;

import tema17.IPizza;

public class RecheioDeBacon extends DecoradorRecheio {
    private static final double PRECO_BACON = 3.50;

    public RecheioDeBacon(IPizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + PRECO_BACON;
    }
}
