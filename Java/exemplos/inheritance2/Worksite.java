public class Worksite {
    public static void main(String[] args) {
        Worker albert = new Worker();
        Tradesperson bob = new Tradesperson();
        Carpenter cynthia = new Carpenter();

        albert.doWork();
        bob.doWork();
        cynthia.doWork();
    }
}