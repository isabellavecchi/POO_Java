public class Cat {
    public static void fall() {
        System.out.println("Gatos pousam de pé");
    }

    public void meow() {
        System.out.println("Cat says meow");
    }

    public static void petCat(Cat thisCat) {
        //tratando o problema do null:
        if(thisCat != null){
            System.out.println("Pets the cat.");
            thisCat.meow();
        } else {
            System.out.println("There is no cat to pet.");
        }
    }

    public static void main(String[] args) {

        //PARTE I
        Cat whiskers;           //n aponta

        Cat catzilla = null;    //aponta p/ nada

        Cat clawdia = new Cat();    //aponta p/ um obj recém criado (inst. da classe)

        //whiskers.fall();      //Erro: não posso chamar um método a partir
        //whiskers.meow();    //de uma variável n inicializada.

        catzilla.fall();        //Métodos estáticos podem ser INSTANCIADOS PELA CLASSE
        //catzilla.meow();  //Erro: variável null n chama método

        clawdia.fall();
        clawdia.meow();
        System.out.println();

        //PARTE II
        //Cat kitkat = new Cat();
        Cat kitkat = null;      //tratando o problema do null
        petCat(kitkat);         //dentro do método.
    }
}