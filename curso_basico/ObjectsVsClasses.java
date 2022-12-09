public class ObjectsVsClasses {
    /*
    * Objects têm:
    * - States (fields in java);
    * - Behaviors (methods in java).
    *
    * Exemplos:
    * Dog Object
    *
    * States:
    * 1.color
    * 2.isAwake
    * 3.age
    *
    * Behaviors:
    * 1.bark
    * 2.run
    * 3.eat
    */

    /*
    * Class Vs Object (aka. Instance of a Class)
    *
    * A class is a *blueprint* (or template) for an object ("rascunho")
    *
    *   public class AudiA8 {
    *
    *   }
    */
    
    //Static methods: behaviors que podemos acessar sem precisar de criar um objeto
    public static void metodo() {

    }

    //non-static methods: behaviors que só podemos acessar através de um objeto
            //instance of class
    public void metodoNE() {

    }

    //Class variables(static fields)
        //change it anywhere -> change it everywhere!
    private static int wheelSize;
    private static String vehicleType;

    //Instance variables (Non-static fields)
        //only an object may have a value for instance variables
        //muda um obj sem mudar os outros
    private String color;
    private String licensePlate;

    /*
    *   NOTAS ADICIONAIS
    *   - A static method access only Class variables (static fields)
    *   - A non-static method access Class variables *OR* Instance variables
    *   - The 'private' keyword -> fields can't be *directly* accessed from
    * another Class.
    */
}