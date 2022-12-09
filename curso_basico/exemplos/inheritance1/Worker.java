public class Worker {
    public void doWork() {
        System.out.println("Does unskilled work.");
        earnMinimumWage();
        //public:
        //will be inherited to subclasses
        //can be directly called from other classes
    }

    private void earnMinimumWage() {
        System.out.println("Earns minimum wage.");
        //private:
        //will NOT be inherited to subclasses
        //CAN'T be directly called from other classes
    }
}
//de maneita implícita, Worker é uma subclasse da classe objeto de java

//Carpenter is a Tradesperson, that is a Worker, that is an Object
//de baixo para cima