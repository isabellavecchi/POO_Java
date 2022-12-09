package tema14;

public class LeiteComChocolate extends ProducaoBebidaQuente {
    @Override
    void pegarLiquidoBase() {
    System.out.println("Pegou o leite: 1 caneca");
    }

    @Override
    void misturarIngredientes() {
        System.out.println("colocou nescau, porque é mais gostoso que toddy: 3 colheres. E misturou");
    }

    @Override
    void servirBebida() {
        System.out.println("Leitinho achocolatado chegando na sua mão");
    }

    @Override
    void lavarUtensilios() {
        System.out.println("Lava caneca, colher e panela");
    }
}
