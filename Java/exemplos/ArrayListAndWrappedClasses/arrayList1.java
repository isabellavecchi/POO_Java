import java.util.*;

ArrayList<String> one = new ArrayList<String>();

ArrayList<int> inteiros = new ArrayList<int>();

List<String> two = new ArrayList<String>();


List<String> pets = new ArrayList<String>();
pets.add("dog");    //adds "dog" to the end

pets.add("cat");
    //pets{"dog","cat"}
pets.add(1,"owl");  //adds "owl" to index 1
    //pets{"dog","owl","cat"}

pets.set(0,"bat");  //changes index 0 to "bat"
    //pets{"bat","owl","cat"}

pets.remove(1);     //removes the obj at the given index    (sem deixar espaço vazio)
    //pets{"bat","cat"}

pets.get(1);    //returns the obj at index 1
    //"cat"
