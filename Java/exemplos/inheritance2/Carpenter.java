public class Carpenter extends Tradesperson {
    public void doWork() {
        System.out.println("Does woodwork.");
        earnCarpentersUnionWage();
        //se não tivesse feito o override, teria herdado o doWork() de Tradesperson
    }
    private void earnCarpentersUnionWage() {
        System.out.println("Earns carpenter's union wage.");
         //we can't override earnUnionWage(), so we create a new one.
    }
}