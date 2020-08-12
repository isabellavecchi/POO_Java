import java.util.*;

public class WrappedClasses {
    public static void main(String[] args) {
        List<Double> nums = new ArrayList<Double>();
        /*
        Criando uma lista de obj double
        Double com D maiúsculo é uma Wrapped class do tipo double
        */
        nums.add(new Double(3.2));
        //criamos um novo obj do tipo double
        //e passamos a ele o valor primitivo 3.2 para o constructor na Double class
        
        double x = nums.get(0).doubleValue();       //without autoboxing
            //doubleValue é um método da classe double, retorna o valor primitivo (3.2)
        
        //AutoBoxing -> converte automaticamente (primitive -> object) type
        nums.add(5.6);
        double y = nums.get(1);

        double z = nums.get(0);

        System.out.println(z);
    }    
}