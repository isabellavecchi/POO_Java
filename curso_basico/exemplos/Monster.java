public class Monster {
    private static String location; //class variable (aka static field);
                                    //belongs to a Class;
                                    //Will be identical across the entire Class;
                                    //can be accessed by static & non-static methods.
    
    private String species; //instance variable (aka non-static field);
                            //belongs to an instance of a Class;
                            //can be differente for different instaces;
                            //ONLY can be accessed by non-static methods.
    
    public static String getLocation() {
        return location;
    }

    public static void setLocation(String newLocation) {
        location = newLocation;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String newSpecies) {
        species = newSpecies;
    }

    public void sampleMethod1(String location) {    //parâmetro.nome == campo.nome -> ok

    }

    public static void sampleMethod2() {   //varLocal.nome == campo.nome -> ok
        String location;
    }
}

//OBS.: utlizar variáveis e métodos estáticos é melhor para o desempenho