public class Airplane extends Vehicle {
    private int numOfEngines;
    
    public Airplane() {
        //super();      Java escreve. Mas e se não tiver um constructor sem param. na super?
        //consertamos assim:
        super("azul", 1234);
        numOfEngines = 1;
    }

    public static void main(String[] args) {
        Airplane aviao = new Airplane();
        System.out.println(aviao.color + ", " + aviao.serialNumber + ", " + aviao.numOfEngines);
    }
}