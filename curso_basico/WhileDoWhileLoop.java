import java.util.Scanner;

public class WhileDoWhileLoop {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);

        /*while (true) {
            System.out.println("repeat forever");
        }*/
        
        int i = 4;
        while (i > 0) {
            System.out.println(i);
            i--;
        }

        int userGuess = 0;
        while (userGuess !=  23) {
            System.out.println("Guess what number I'm thinking of: ");
            userGuess = kbd.nextInt();
        }
        System.out.println("You got it right!");

        i = -2;
        do {                        //Executa pelo menos 1 vez
            System.out.println(i);
            i--;
        } while (i > 0);        //não esquecer do ';' no final!!!   OBS.
    }
}