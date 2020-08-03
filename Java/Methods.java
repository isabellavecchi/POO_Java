public class Methods {
    /*
    * Methods are blocks of code that can be called from another part
    * of program;
    *
    * Methods may have ~parameters~ that allow us to "pass" them data.
    *
    * Methods may return information to the part of the program that
    * called them.
    */
    
    /*
    * Tipos de método e seus retornos:
    * void() -> return is optional;
    * qualquer outro() -> return obrigatório do mesmo tipo;
    */

    //main method
        //o único que roda automático quando roda o programa
    public static void main(String[] args) {
        int sum = addTwoNums(5, 3);
        int avg = averageOfTwo(7, 5);
        printAverage(avg);
    }

    //int method
        //precisa de um retorno int
    public static int addTwoNums(int num1, int num2) {
        int total = num1 + num2;
        return total;
    }
    public static int averageOfTwo (int numA, int numB) {
        return addTwoNums(numA, numB) / 2;
    }

    //void method
    public static void printAverage(int num3) {
        System.out.println("Average is = " + num3);
    }
}