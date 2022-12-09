package tema7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConversorTest {
    private Conversor conversor = new Conversor();

    @Test
    public void validaDicionario() {
        assertEquals(1,  conversor.getInt("I"));
        assertEquals(5,  conversor.getInt("V"));
        assertEquals(10, conversor.getInt("X"));
    }

    @Test
    public void validaSoma() {
        int convertido = conversor.converteParaRom("XII");
        assertEquals(12,convertido);
    }

    @Test
    public void validaSubtracao() {
        int convertido = conversor.converteParaRom("XIX");
        assertEquals(19,convertido);
    }

    @Test
    public void validaNumeroRomano() {
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom("XXXX"));
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom("VV"));
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom("XXXX"));
    }

    @Test
    public void validaPosicaoDoV() {
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom("VX"));
    }

    @Test
    public void validaLimite1a20() {
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom("XXI"));
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom(-3));
        assertThrows(IllegalArgumentException.class, () -> conversor.converteParaRom(21));
    }

    @Test
    public void testaConverterIntSimples() {
        String romano = conversor.converteParaRom(18);
        int inteiro = conversor.converteParaRom(romano);
        assertEquals("XVIII", conversor.converteParaRom(18));
        assertEquals(inteiro, conversor.converteParaRom(romano));
    }

    @Test
    public void testaConverterIntComSubtracao() {
        String romano = conversor.converteParaRom(19);
        int inteiro = conversor.converteParaRom(romano);
        assertEquals("XIX", conversor.converteParaRom(19));
        assertEquals(inteiro, conversor.converteParaRom(romano));


        romano = conversor.converteParaRom(4);
        inteiro = conversor.converteParaRom(romano);
        assertEquals("IV", conversor.converteParaRom(4));
        assertEquals(inteiro, conversor.converteParaRom(romano));
    }
}
