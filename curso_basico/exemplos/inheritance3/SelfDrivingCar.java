public class SelfDrivingCar extends Car {
    public void applyBrakes() {
        System.out.println("Computer appies brakes.");
        checkSurroundings();
    }

    public void checkSurroundings() {
        System.out.println("Computer checks surroundings.");
    }

    public void emergencyOverride() {
        System.out.println("Driver takes control.");
        super.applyBrakes();
    }
}