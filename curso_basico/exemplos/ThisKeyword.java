//Specifying a field

public class ThisKeyword {
    private int x = 10;
    private static int y = 0;
    
    public void printX() {
        int x = 5;
        System.out.println(x);
        System.out.println(this.x);
        //a variável local (ou parâmetro) tem preferência em relação ao campo.
    }

    public void setX(int x) {
        this.x = x;     //this keyword faz x se remeter ao campo ao invés do parâmetro
    }

    public void resetY() {
        int y = 0;
        this.y = y;     //this keyword faz x se remeter ao campo ao invés da variável
    }

    public static void main(String[] args) {
        ThisKeyword teste = new ThisKeyword();
        teste.printX();
    }
}