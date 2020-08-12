public class Vehicle {  //extends Object [java escreve]
    public String color;
    public int serialNumber;

    public Vehicle() {
        super();    // p/ chamar contructor da superclasse: utilize a PRIMEIRA linha.
        color = "green";
    }

    public Vehicle(String newColor) {
        this();     //p/ chamar outro constructor: utilize a PRIMEIRA linha.
        color = newColor;
    }

    public Vehicle(String newColor, int newSerialNumber) {
        //super() [java escreve]
        color = newColor;
        serialNumber = newSerialNumber;
    }


    public void move() {
        System.out.println("Vehicle moves.");
    }


    public static void main(String[] args) {
        Vehicle car = new Vehicle();
        Vehicle bike = new Vehicle("yellow");
        Vehicle motorcycle = new Vehicle("red", 48);

        System.out.println(car.color + ", " + car.serialNumber);
        System.out.println(bike.color + ", " + bike.serialNumber);
        System.out.println(motorcycle.color + ", " + motorcycle.serialNumber);
    }
}