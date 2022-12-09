public class Animal {
    private int numOfLegs = 4;
    //since numOfLegs is private:
        //1.CAN'T be inherited
        //2.CAN'T be accessed outside the Animal class
    
    //Para poder utilizar numOfLegs na classe Birds:
    public int getNumOfLegs() {
        return numOfLegs;
    }
    public void setNumOfLegs(int newNumOfLegs) {
        numOfLegs = newNumOfLegs;
    }
}