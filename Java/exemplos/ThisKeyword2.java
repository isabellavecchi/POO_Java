//Para chamar outro constructor da classe

public class ThisKeyword2 {
    public ThisKeyword2() { //constructor #1
        this(5);    //chama o #2 [PRIMEIRA LINHA!!!]
        //do something;
    }

    public ThisKeyword2(int x) { //constructor #2
        //do something
    }
}