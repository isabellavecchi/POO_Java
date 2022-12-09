/*
* Scope:
*       Onde no progragrama a variável pode ser acessada.
* Lifetime:
*       Quando uma variável ou objeto é criada e destruída na memória.
*
* Ambos são determinados por onde a variável é declarada e não inicializada.
*/


public class ScopeAndLifetime {
    private static int a = 2;   //class variable
    private double b = 3.0;     //instance variable; escopo: classe; Tempo de vida: da criação à destrução

    public static void main(String[] args) {
        int a = 3;

        if(true) {
            int b =4;
        }

        for (int c = 5; c < 8; c++) {
            int d = 6;
        }
        //a existe durante todo o programa;
        //b só dentro do if(){};
        //c só dentro dos 4 loops do for.

        anotherMethod();
        System.out.println(a);

        //'a' n existe dentro de anotherMethod.
    }

    public static void anotherMethod() {
        System.out.println("Scope Demo");
    }

    public void methodTwo(String name) {
        System.out.println(name);
        System.out.println(3.1415926);
    }
}