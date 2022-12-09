public class Slimer extends Ghost {
    private boolean isHungry;

    public Slimer(boolean isHungry, boolean isScary) {
        super("green", isScary);    //manda o isScary p/ a superclass
        this.isHungry = isHungry;
    }

    public static void main(String[] args) {
        Slimer coco = new Slimer(true, true);
        //Slimer net = new Slimer();            //n pode, n tem constructor vazio!!

        System.out.println(coco.getColor() + ", " + coco.getIsScary() + ", " + coco.isHungry);
        //System.out.println(net.getColor() + ", " + net.getIsScary() + ", " + net.isHungry);
    }
}