package Tema5;

public class LampadaRGB implements Lampada {
    private boolean estado = false;
    private String cor;

    public LampadaRGB() {
        this.estado = false;
        this.cor = "branca";
    }

    public LampadaRGB(String cor) {
        this.estado = false;
        this.cor = cor;
    }

    public LampadaRGB(boolean estado) {
        this.estado = estado;
        this.cor = "branca";
    }

    public LampadaRGB(boolean estado, String cor) {
        this.estado = estado;
        this.cor = cor;
    }

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
            System.out.println("\nEstado = desligada\nCor = desconhecida\n");
        } else {
            System.out.println("\nEstado = ligada\nCor = " + this.cor + "\n");
        }
    }
}
