public class Factory {
    public static void main(String[] args) {
        RobotTwo one = new RobotTwo("R2D2");
        RobotTwo two = new RobotTwo();

        System.out.println(one.getName());
        System.out.println(two.getName());
    }    
}