package tema14;

import java.util.concurrent.TimeUnit;

public abstract class ProducaoBebidaQuente {

    public void esperaUmSegundo() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    abstract void pegarLiquidoBase();
    public void encherRecipiente() throws InterruptedException {
        System.out.println("Colocando o líquido dentro do recipiente");
        System.out.println("Bluuuup bluup blup blup blup");
        esperaUmSegundo();
        System.out.println("bluuuup blup");
        esperaUmSegundo();
        System.out.println("blip");
    }
    public void ligarOAquecedor() {
        System.out.println("Alguns graus Célcios a mais são bem vindos");
    }
    public void aquecerABebida() throws InterruptedException {
        esperaUmSegundo();
        System.out.println("esquentando");
        esperaUmSegundo();
        System.out.println("shhhhhhhhhh");
    }
    abstract void misturarIngredientes() throws InterruptedException;
    abstract void servirBebida();
    abstract void lavarUtensilios();

    public final void preparar() throws InterruptedException {
        pegarLiquidoBase();
        encherRecipiente();
        ligarOAquecedor();
        aquecerABebida();
        misturarIngredientes();
        servirBebida();
        lavarUtensilios();
    }
}
