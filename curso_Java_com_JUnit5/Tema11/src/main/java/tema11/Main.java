package tema11;

public class Main {
    public static void main(String[] args) {
        FabricaDeDados fabricaDeDados = new FabricaDeDados();

        Dado d4 = fabricaDeDados.criaDado("d4");
        System.out.println(d4.getNumeroLados());
        System.out.println(d4.rolaDados(3));

        Dado d6 = fabricaDeDados.criaDado("D6");
        System.out.println(d6.getNumeroLados());
        System.out.println(d6.rolaDados(1));

        Dado d8 = fabricaDeDados.criaDado("d8");
        System.out.println(d8.getNumeroLados());
        System.out.println(d8.rolaDados(4));

        Dado d100 = fabricaDeDados.criaDado("D100");
        System.out.println(d100.getNumeroLados());
        System.out.println(d100.rolaDados(12));
    }
}
