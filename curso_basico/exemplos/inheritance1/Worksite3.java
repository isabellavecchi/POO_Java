public class Worksite3 {
    public static void main(String[] args) {
        Worker alex = new Worker();
        Tradesperson barbara = new Tradesperson();
        Carpenter carol = new Carpenter();

        //alex.doWoodwork();      //can't inherit from a subclass
        //barbara.doWoodwork();   //can't inherit from a subclass
        carol.doWoodwork();     //doWoodwork is part of Carpenter
    }
}