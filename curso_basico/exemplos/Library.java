public class Library {
    public static void main(String[] args) {
        Book mythicalManMonth = new Book();

        //2 maneiras LEGAIS para chamar um STATIC METHOD
        Book.getPublisher();
        mythicalManMonth.getPublisher();

        //CHAMANDO um NON-STATIC METHOD
        //Book.getTitle();      //Error: não pode chamar um Método NE pela classe
        mythicalManMonth.getTitle();    //legal

        //CHAMANDO um MÉTODO NE através de um MÉTODO E
        //sayHelloWorld();  //Error
                            //n pode chamar diretamente
        Library alexandria = new Library();
        alexandria.sayHelloWorld();     //legal
    }

    public void sayHelloWorld() {
        System.out.println("Hello World");
    }
}