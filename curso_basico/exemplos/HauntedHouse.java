public class HauntedHouse {
    public static void main(String[] args) {
        Monster dracula;    //declarando variável do tipo Monster
        dracula = new Monster();

        Monster wolfMan = new Monster();
        Monster elmo = new Monster();

        Monster.setLocation("Scary house");
        wolfMan.setLocation("Scary house");
        System.out.println(elmo.getLocation());

        dracula.setSpecies("vampire");
        elmo.setSpecies("Muppet");

        System.out.println(dracula.getSpecies());
        System.out.println(elmo.getSpecies());
        System.out.println(wolfMan.getSpecies());
    }
}