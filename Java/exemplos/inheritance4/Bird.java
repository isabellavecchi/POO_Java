public class Bird extends Animal {
    private String featherColor = "red";
    //since numOfLegs is private:
        //1.CAN'T be inherited
        //2.CAN'T be accessed outside the Bird class

    public void reportStats() {
        System.out.println("featherColor: " + featherColor);
        //System.out.println("Legs: " + numOfLegs);   //ilegal
        //Corrigindo:
        System.out.println("Legs: " + getNumOfLegs());
    }

    public static void main(String[] args) {
        Bird twitter = new Bird();
        twitter.reportStats();
    }
}