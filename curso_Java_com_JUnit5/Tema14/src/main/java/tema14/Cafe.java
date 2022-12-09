package tema14;

import java.util.concurrent.TimeUnit;

public class Cafe extends ProducaoBebidaQuente {
    @Override
    void pegarLiquidoBase() {
        System.out.println("Pega água, da torneira ou do filtro, num importa porque vai ferver.");
        System.out.println("3 chícaras d'água.");
    }

    @Override
    void misturarIngredientes() throws InterruptedException {
        System.out.println("6 colheres de sopa de café no coador. Nada de açúcar, se o cliente quiser, ele vai por.");
        esperaUmSegundo();
        System.out.println("Passa a água no coador");
        esperaUmSegundo();
        System.out.println("Espera terminar de coar para a garrafa térmica");
        esperaUmSegundo();
        esperaUmSegundo();
        System.out.println("tampa a garrafa");
    }

    @Override
    void servirBebida() {
        System.out.println("Ó o cafézin!! Ficou pronto, só vir aqui no balcão pegar. Tá dentro da garrafa térmica");
    }

    @Override
    void lavarUtensilios() {
        System.out.println("Lava o coador, a colher e guarda o recipiente de esquentar água.");
    }
}
