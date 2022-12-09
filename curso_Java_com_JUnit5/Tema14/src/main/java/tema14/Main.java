package tema14;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducaoBebidaQuente cafe = new Cafe();
        cafe.preparar();

        System.out.println();

        ProducaoBebidaQuente leiteComChocolate = new LeiteComChocolate();
        leiteComChocolate.preparar();
    }
}
