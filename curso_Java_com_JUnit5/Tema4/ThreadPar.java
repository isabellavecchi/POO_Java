import java.util.concurrent.ThreadLocalRandom;

public class ThreadPar implements Runnable {
    private int n = 0;
    
    private int geraRandom() {
        return ThreadLocalRandom.current().nextInt();
    }
    
    public void geraPar() {
       this.n = geraRandom() * 2;
    }

    
    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("\nThread 2:");
                geraPar();
                System.out.println("Par = " + n + "\n");
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Erro na Thread 2: " + ex);
            }
        }
    }
}