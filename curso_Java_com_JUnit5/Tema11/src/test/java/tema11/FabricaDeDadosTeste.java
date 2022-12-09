package tema11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeDadosTeste {
    private FabricaDeDados fabricaDeDados = new FabricaDeDados();

    public void testaVariasVezesLimiteDeValor(Dado dado) {
        Random random1 = new Random();
        int quantidade = random1.nextInt(100-1) + 1;
        for (int i=0; i<quantidade; i++) {
            assertTrue(dado.rolaDados(quantidade) <= dado.getNumeroLados() * quantidade && dado.rolaDados(quantidade) > 0);
        }
    }

    public void testaVariasVezesLimiteDeValorParaD100(D100 d100) {
        Random random1 = new Random();
        int quantidade = random1.nextInt(100-1) + 1;
        for (int i=0; i<quantidade; i++) {
            assertTrue(d100.rolaDados(quantidade) <= 100 && d100.rolaDados(quantidade) >= 0);
        }
    }

    @Test
    public void testaD4() {
        Dado d4 = fabricaDeDados.criaDado("d4");
        assertFalse(d4.estaViciado());
        assertEquals(4, d4.getNumeroLados());
        testaVariasVezesLimiteDeValor(d4);
    }

    @Test
    public void testaD6() {
        Dado d6 = fabricaDeDados.criaDado("d6");
        assertFalse(d6.estaViciado());
        assertEquals(6, d6.getNumeroLados());
        testaVariasVezesLimiteDeValor(d6);
    }

    @Test
    public void testaD8() {
        Dado d8 = fabricaDeDados.criaDado("d8");
        assertFalse(d8.estaViciado());
        assertEquals(8, d8.getNumeroLados());
        testaVariasVezesLimiteDeValor(d8);
    }

    @Test
    public void testaD10() {
        Dado d10 = fabricaDeDados.criaDado("d10");
        assertFalse(d10.estaViciado());
        assertEquals(10, d10.getNumeroLados());
        testaVariasVezesLimiteDeValor(d10);
    }

    @Test
    public void testaD12() {
        Dado d12 = fabricaDeDados.criaDado("d12");
        assertFalse(d12.estaViciado());
        assertEquals(12, d12.getNumeroLados());
        testaVariasVezesLimiteDeValor(d12);
    }

    @Test
    public void testaD20() {
        Dado d20 = fabricaDeDados.criaDado("d20");
        assertFalse(d20.estaViciado());
        assertEquals(20, d20.getNumeroLados());
        testaVariasVezesLimiteDeValor(d20);
    }

    @Test
    public void testaD100() {
        Dado d100 = fabricaDeDados.criaDado("d100");
        assertFalse(d100.estaViciado());
        assertEquals(100, d100.getNumeroLados());
        testaVariasVezesLimiteDeValor(d100);
    }

    @Test
    public void testaValorInvalido() {
        assertThrows(RuntimeException.class, () -> fabricaDeDados.criaDado("invalido"));
    }
}
