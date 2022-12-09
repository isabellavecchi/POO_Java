public class Highway {
    public static void main(String[] args) {
        Car fordMustang = new Car();
        SelfDrivingCar teslaX = new SelfDrivingCar();

        fordMustang.applyBrakes();
        teslaX.applyBrakes();
        System.out.println();

        teslaX.emergencyOverride();
        //o método checkSurroundings() é chamado dentro da classe SelfDrivingCar.
        //Já que existe uma versão overriden dele lá dentro.
    }
}