import java.util.Scanner;
public class UserInput
{
    public static void main(String[] args)
    {
        //Criando uma instância da Classe Scanner:
        Scanner keyboard = new Scanner(System.in);
        //Como "Scanner" é da classe objeto, inicia c/ maiúscula

        System.out.println("Digite sua altura:");
        double x = keyboard.nextDouble();

        System.out.println("Digite sua idade:");
        int y = keyboard.nextInt();

        System.out.println("Você é mulher? (true/false");
        boolean isFemale = keyboard.nextBoolean();

                    //CUIDADO!!!!!
        System.out.println("Digite seu nome:");
        String name = keyboard.next();      //Pega apenas a primeira palavra!!!
        System.out.println("Digite seu nome competo:");
        String fullName = keyboard.nextLine();   //Pega a linha toda

        System.out.println(x);
        System.out.println(y);
        System.out.println(isFemale);
        System.out.println(name);
        System.out.println(fullName);
    }
}