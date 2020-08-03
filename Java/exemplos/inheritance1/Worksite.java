public class Worksite {
    public static void main(String[] args) {
        Worker alex = new Worker();
        // ilegal: alex.earnMinimumWage(); //is private só pode ser chamado por um método do classe
        alex.doWork(); //is public
        //chama o método privado indiretamente
    }
}