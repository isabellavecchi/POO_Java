package Tema5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterruptorTest {
    @Test
    void ligaLampLED() {
        Lampada lamp1 = new LampadaLED(false);
        Interruptor inte1 = new Interruptor(lamp1);
        inte1.switchState();

        assertTrue(lamp1.getEstado());
    }

    @Test
    void desligaLampLED() {
        Lampada lamp2 = new LampadaLED(true);
        Interruptor inte2 = new Interruptor(lamp2);
        inte2.switchState();

        assertFalse(lamp2.getEstado());
    }

    @Test
    void ligaLampRGB() {
        Lampada lamp1 = new LampadaRGB(false);
        Interruptor inte1 = new Interruptor(lamp1);
        inte1.switchState();

        assertTrue(lamp1.getEstado());
    }

    @Test
    void desligaLampRGB() {
        Lampada lamp2 = new LampadaRGB(true);
        Interruptor inte2 = new Interruptor(lamp2);
        inte2.switchState();

        assertFalse(lamp2.getEstado());
    }

}