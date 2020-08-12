//Problem overloading constructors
public class RobotConfused {
    private static String fuelSource;
    private String name;

    public RobotConfused(String myFuel, String myName) {
        fuelSource = myFuel;
        name = myName;
    }

    public RobotConfused(String x, String y) {
        fuelSource = x;
        name = y;
    }
    //parametros iguais (String, String) -> nem compila.


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