public class OverloadingError {
    public static void main(String[] args) {
        OverloadingError anInstance = new OverloadingError();
        anInstance.solveForAnswer(true);
    }

    public static void solveForAnswer(boolean a) {
        //...
    }

    //private int solveForAnswer(boolean z) {       //Erro! n sabe diferenciar qual dos 2
    private int solveForAnswer(int z) {
        //...
    }
}