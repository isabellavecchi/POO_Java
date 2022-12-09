package Tema5;

public class LampadaLED implements Lampada {
    private boolean estado = false;
    private String cor;

    public LampadaLED() {
        this.estado = false;
        this.cor = "branca";
    }

    public LampadaLED(boolean estado) {
        this.estado = estado;
        this.cor = "branca";
    }
    //adicionar exceção para números n booleanos

    @Override
    public void on() {
        this.estado = true;
    }

    @Override
    public void off() {
        this.estado = false;
    }

    @Override
    public boolean getEstado() {
        return this.estado;
    }

    @Override
    public void printEstado() {
        if (this.estado == false) {
            System.out.println("\nEstado = desligada\n");
        } else {
            System.out.println("\nEstado = ligada\n");
        }
    }
}