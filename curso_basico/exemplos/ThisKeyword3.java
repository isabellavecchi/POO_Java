//Pass a pointer (reference) back to the current object

public class ThisKeyword3 {
    private SomeClass anotherObject = new SomeClass();    //classe aleatória    //anotherObject -> SomeClass    #2nd

    public static void main(String[] args) {
        ThisKeyword3 anObject = new ThisKeyword3();                             //anObject -> ThisKeyword3  #1st
        anObject.methodTwo();
    }

    public void methodTwo() {
        anotherObject.someMethod(this);               //anotherObject -> anObject; anObject.var -> anotherObject    #3rd
    }

            //anotherObject (e)  
}