public class Worker {
    public void doWork() {
        System.out.println("Does unskilled work.");
        earnWage();
        //doWork is PUBLIC
        //so it can be overriden in a subclass.-.
    }

    private void earnWage() {
        System.out.println("Earns minimum wage.");
        //Private
        //will not be inherited, and can't be overriden
    }
}
