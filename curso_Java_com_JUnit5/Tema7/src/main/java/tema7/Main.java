package tema7;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int op = 1;
        String romano;
        int numerico;

        Conversor conversor = new Conversor();

        while (op != 0) {
            System.out.println("Digite 1 ou 2 para converter valores");
            System.out.println("\n1) romano -> inteiro\n2)inteiro -> romano");

            Scanner teclado = new Scanner(System.in);
            op = teclado.nextInt();
            teclado.nextLine();

            if (op == 1) {
                System.out.println("Digite um número romano <= 20 para ser convertido:");
                romano = teclado.nextLine();
                try{
                    numerico = conversor.converteParaRom(romano);
                    System.out.println("Número convertido para inteiro:\n" + numerico);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (op == 2) {
                System.out.println("Digite um número inteiro pertencente ao intervalo [1,20] para ser convertido:");
                numerico = teclado.nextInt();
                try {
                    romano = conversor.converteParaRom(numerico);
                    System.out.println("Número convertido para algarismo romano:\n" + romano);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Digite (0) para sair ou (1) para converter outro valor");
            op = teclado.nextInt();
            teclado.nextLine();
        }
    }
}
