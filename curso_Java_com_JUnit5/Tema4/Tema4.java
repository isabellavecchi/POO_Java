import java.util.concurrent.ThreadLocalRandom;

public class Tema4 {
    public static void main (String[] args) {
            ThreadImpar imp = new ThreadImpar();
            ThreadPar par = new ThreadPar();

            Thread thread1 = new Thread(imp);
            Thread thread2 = new Thread(par);

            thread1.start();
            thread2.start();
    }
}