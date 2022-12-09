public class Worksite2 {
    public static void main(String[] args) {
        Worker alex = new Worker();
        Tradesperson barbara = new Tradesperson();
        Carpenter carol = new Carpenter();

        alex.doWork(); //doWork() is part of Worker
        barbara.doWork(); //doWork is inherited
        carol.doWork(); //doWork is inherited
    }
}