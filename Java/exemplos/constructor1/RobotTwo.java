// OVERLOAD - CONSTRUCTOR

public class RobotTwo {
    private static String fuelSource;
    private String name;
    
    public RobotTwo() {
        this("WALL-E");
        //se for usar "this" p/ chamar um novo construtor, é necessário que seja a primeira coisa.
        //chama o próximo constructor e passa a variável newName = "WALL-E"
    }

    public RobotTwo(String newName) {
        fuelSource = "electricity";
        name = newName;
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