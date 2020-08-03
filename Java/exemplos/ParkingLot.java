public class ParkingLot {
    public static void main(String[] args) {
        AudiA8.rotateWheel(); //Static method can be called from a Class
        AudiA8 janesCar = new AudiA8(); //declare variable and create an
                                        //object of AudiA8 class

        janesCar.rotateWheel(); //static method can *ALSO* be called from
                            //an object (instance of a class)
    
        janesCar.turnOnEngine(); //non-static method can *ONLY* be called
                            //from an object (instance of a class)
    }
}