package tema11;

import java.util.Random;

public class D6 implements Dado {
    @Override
    public boolean estaViciado() {
        return false;
    }

    @Override
    public int getNumeroLados() {
        return 6;
    }

    @Override
    public int rolaDados(int quantidade) {
        Random random = new Random();
        int resultado = 0;
        for (int i=0; i<quantidade; i++) {
            int dadoRolado = random.nextInt(getNumeroLados()-1) + 1;
            resultado += dadoRolado;
        }
        return resultado;
    }
}
