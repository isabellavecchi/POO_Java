public class Tradesperson extends Worker {
    public void doWork() {
        System.out.println("Does skilled work.");
        earnUnionWage();
        //overriden: behavior more appropriate for a tradesperson
    }
    private void earnUnionWage() {
        System.out.println("Earns union wage.");
        //we can't override earnWage(), so we create a new one.
    }
}
