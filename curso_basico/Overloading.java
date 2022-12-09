/*
* - Overloaded methods must have the same name;
* - must have different numbers and/or types of parameters;
*       -> número, tipo ou a ordem dos tipos de parâmetros
*       (para os diferenciar um do outro)
* - constructors can also be overloaded.
*/

public class Overloading {
    public static void main(String[] args) {
        Overloading anInstance = new Overloading();

        //Exemplo 1
        anInstance.outputAnswer(3); //chama o 2o

        //Exemplo 2
        anInstance.calculateAnswer(3.3);    //chama o 2o

        //Exemplo 3
        anInstance.computeAnswer(3.3, 2);
    }


    //Exemplo 1
    public static void outputAnswer() {
        //implementation not shown
    }

    public static void outputAnswer(int x) {
        //implementation not shown
    }


    public static void outputAnswer(int x, int y) {
        //implementation not shown
    }


    //Exemplo 2
    public static int calculateAnswer(int x) {
        return x + 2;
    }
    
    public static int calculateAnswer(double x) {
        return x;
    }
    
    public static int calculateAnswer(String x) {
        return 3;
    }
    

    //Exemplo 3
    public void computeAnswer(int x, double y) {
        //implementation not shown
    }

    public void computeAnswer(double x, int y) {
        //implementation not shown
    }
}