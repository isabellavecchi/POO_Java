package Tema5;

public class App {

    public static void main(String[] args) {
        Lampada lamp1 = new LampadaLED();
        Lampada lamp2 = new LampadaLED(true);
        Lampada lamp3 = new LampadaLED(false);
        Lampada lamp4 = new LampadaRGB("verde");
        Lampada lamp5 = new LampadaRGB(true, "vermelha");
        Lampada lamp6 = new LampadaRGB(false, "azul");

        System.out.println("interruptor 1 - Lâmpada desligada");
        Interruptor inte1 = new Interruptor(lamp1);
        lamp1.printEstado();
        inte1.switchState();
        lamp1.printEstado();

        System.out.println("interruptor 2 - Lâmpada ligada");
        Interruptor inte2 = new Interruptor(lamp2);
        lamp2.printEstado();

        System.out.println("interruptor 3 - Lâmpada desligada");
        Interruptor inte3 = new Interruptor(lamp3);
        inte3.lampada.printEstado();

        System.out.println("interruptor 4 - Lâmpada desligada");
        Interruptor inte4 = new Interruptor(lamp4);
        inte4.lampada.printEstado();

        System.out.println("interruptor 5 - Lâmpada ligada");
        Interruptor inte5 = new Interruptor(lamp5);
        inte5.lampada.printEstado();

        System.out.println("interruptor 6 - Lâmpada Desligada");
        Interruptor inte6 = new Interruptor(lamp6);
        inte6.lampada.printEstado();
        inte6.switchState();
        lamp6.printEstado();


    }
}
