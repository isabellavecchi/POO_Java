public class Robot {
    private static String fuelSource;
    private String name;

    //constructor
    public Robot() {
        fuelSource = "electricity";
        randomName();
    }

    public void randomName() {
        //num aleatório de 1 a 3
        int randomNumber = (int)(Math.random()*3 + 1);

        if (randomNumber == 1) {
            name = "Bender";
        } else if (randomNumber == 2) {
            name = "Hal 9000";
        } else {
            name = "Gort";
        }
    }

    public String getFuelSource() {
        return fuelSource;
    }

    public void setFuelSource(String newFuelSource) {
        fuelSource = newFuelSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}