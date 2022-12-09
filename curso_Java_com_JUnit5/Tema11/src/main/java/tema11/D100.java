package tema11;

import java.util.Random;

public class D100 implements Dado {
    private D10 d10;

    public D100(D10 d10) {
        this.d10 = d10;
    }

    @Override
    public boolean estaViciado() {
        return false;
    }

    @Override
    public int getNumeroLados() {
        return 10*10;
    }

    @Override
    public int rolaDados(int quantidade) {
        if(quantidade == 0) {
            return 0;
        }
        Random random1 = new Random();
        Random random2 = new Random();
        int resultado = 1;
        for (int i=0; i<quantidade; i++) {
            int d100 = random1.nextInt(10-1) + 1;
            int d10 = random2.nextInt(10-1) + 1;
            resultado += d100*10 + d10;
        }
        return resultado/quantidade;
    }
}
