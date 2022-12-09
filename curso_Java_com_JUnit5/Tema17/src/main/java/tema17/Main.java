package tema17;

import tema17.recheios.RecheioDeBacon;
import tema17.recheios.RecheioDeQueijo;

public class Main {
    public static void main(String[] args) {
        IPizza pizzaDeBaconComDobroDeQueijo = PizzaBuilder.constructor()
                .addBacon()
                .addQueijo()
                .addQueijo()
                .build();
        System.out.printf("Preço da pizza de bacon com dobro de queijo: %.2f", pizzaDeBaconComDobroDeQueijo.getPrice());
    }
}
