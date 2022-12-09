package tema16;

import org.junit.jupiter.api.Test;
import tema17.IPizza;
import tema17.PizzaBase;
import tema17.PizzaBuilder;
import tema17.recheios.RecheioDeQueijo;

import static org.junit.jupiter.api.Assertions.*;

public class testaPizzaBuilder {
    @Test
    public void testaConstruirPizzaBase() {
        IPizza pizzaBase = PizzaBuilder.constructor().build();
        assertEquals(30, pizzaBase.getPrice());
    }

    @Test
    public void testaConstruirPizzaComQueijo() {
        IPizza pizzaQueijo = PizzaBuilder.constructor()
                .addQueijo()
                .build();
        assertEquals(30+3D, pizzaQueijo.getPrice());
    }

    @Test
    public void testaConstruirPizzaComBacon() {
        IPizza pizzaBacon = PizzaBuilder.constructor()
                .addBacon()
                .build();
        assertEquals(30+3.50, pizzaBacon.getPrice());
    }

    @Test
    public void testaExcecaoRecheioSemPizza() {
        PizzaBase pizzaBaseVazia = null;
        assertThrows(NullPointerException.class, () -> new RecheioDeQueijo(pizzaBaseVazia));
    }

    @Test
    public void testaRepetirRecheio() {
        IPizza pizzaRecheioDobrado = PizzaBuilder.constructor()
                .addQueijo()
                .addQueijo()
                .build();
        assertEquals(30+2*3D, pizzaRecheioDobrado.getPrice());
    }
}
