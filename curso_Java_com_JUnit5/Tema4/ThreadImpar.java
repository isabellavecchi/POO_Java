import java.util.concurrent.ThreadLocalRandom;

public class ThreadImpar implements Runnable {
    private int n = 0;
    
    private int geraRandom() {
        return ThreadLocalRandom.current().nextInt();
    }
    
    public void geraImpar() {
       n = geraRandom() * 2 + 1;
    }

    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Thread 1:");
                geraImpar();
                System.out.println("Impar = " + n);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Erro na Thread 1: " + ex);
            }
        }
    }
}