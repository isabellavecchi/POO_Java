import java.util.*;

public class Traversing {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();

        nums.add(3);
        nums.add(1);
        nums.add(8);

        //traversing
        for(int i = 0; i < nums.size(); i++) {      //o tamanho é calculado toda vez que o loop recomeça
            System.out.println(nums.get(i));
        }
            // Se i >= nums.size vai dar erro
        /*
            .size()     ->      retorna o tamanho de um ArrayList
            .length     ->      retorna o tamanho de um Array
            .length()   ->      retorna o tamanho de uma String
        */
        //nums.add(4, 2);      //erro: n existe index 3 para algo ser colocado no 4

        for(int tempValue : nums) {
            //usa int e não Integer - graças ao autoboxing
            System.out.println(tempValue);
                //se mudar o valor de tempValue, n muda o valor no ArrayList
        }
    }
}