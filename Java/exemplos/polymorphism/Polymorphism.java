public class Polymorphism {
    public static void main(String[] args) {
        Animal joe = new Animal();
        Dog mrPickles = new Dog();
        //tá aqui ó:
        Animal spot = new Sheepdog();   //pode pq Sheepdog é subclass de Animal
        
            //spot é uma variável de referência do tipo Animal
            //que referencia um obj do tipo Sheepdog
            //é permitido pq Sh... é subclasse de Animal

        Animal lobo = new Animal();
        Animal sparky = new Dog();
        Dog Apollo = new Sheepdog();
        //Sheepdog zoe = new Dog();         //ilegal!:
                                                        //Dog é SUPERclasse de Sheepdog, e n SUBclasse
    }
}