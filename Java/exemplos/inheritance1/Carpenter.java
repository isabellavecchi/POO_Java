public class Carpenter extends Tradesperson {
    public void doWoodwork() {
        System.out.println("Does woodwork.");
    }
    //Carpenter is a subclass of Tradesperson
    //inherits directly doSkilledWork()
    //inherits indirectly doWork()
    //adds doWoodwork()
}