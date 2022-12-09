package tema17.recheios;

import tema17.IPizza;

public class RecheioDeQueijo extends DecoradorRecheio {
    private static final double PRECO_QUEIJO = 3D;

    public RecheioDeQueijo(IPizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + PRECO_QUEIJO;
    }
}
