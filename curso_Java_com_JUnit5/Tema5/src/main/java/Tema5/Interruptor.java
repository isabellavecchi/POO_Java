package Tema5;

public class Interruptor {
    protected Lampada lampada;

    public Interruptor(Lampada lampada) {
        this.lampada = lampada;
    }

    public void switchState() {
        if (this.lampada.getEstado()) {
            this.lampada.off();
        } else {
            this.lampada.on();
        }
    }

}