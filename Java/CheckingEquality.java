import java.util.ArrayList;
import java.lang.*;
import java.util.Arrays;

public class CheckingEquality {
    public static void main(String[] args){
        /*
        * Primitive
        * a == b;       //a é igual a b?
        *
        * Reference 
        * objectA == objectB;   //Os obj A e B apontam para o mesmo endereço?
        * a.equals(b)       //compara valores ou endereços, depende da classe do obj.
        */

        //Primitive
        int a = 3;
        int b = 3;
        double c = 3.0;
        double d = 3.0;
        boolean e = false;

        System.out.println(a == b);
        System.out.println(c == 3.0);
        System.out.println(a == d);     //converte 'a' para a = 3.0
        //System.out.println(c == e);     //error
        System.out.println();

        //Reference
            //stringList
        ArrayList<String> abc;
        abc = new ArrayList<String>();
        abc.add("Hello");
        abc.add("Hi");
        abc.add("Hola");

        ArrayList<String> def = abc;    //def aponta para o msm obj de abc
        ArrayList<String> ghi;
        ghi = new ArrayList<String>();
        ghi.add("Hello");
        ghi.add("Hi");
        ghi.add("Hola");                //ghi aponta p/ OUTRO obj identico ao anterior

        System.out.println(abc == def);     //true
        System.out.println(abc == ghi);     //false

        System.out.println(abc.equals(def));    //true   
        System.out.println(abc.equals(ghi));    //true
        System.out.println();

        //array
        int[] j = {3, 1, 2, 6};
        int[] k = j;
        int[] l = {3, 1, 2, 6};

        System.out.println(j == k);     //true
        System.out.println(j == l);     //false

        //para vetores 'equals' = '=='
        System.out.println(j.equals(k));    //true
        System.out.println(j.equals(l));    //false

        //import java.util.Arrays;
        System.out.println(Arrays.equals(j, k));    //true
        System.out.println(Arrays.equals(j, l));    //true
        System.out.println();
        

        //Strings and Heap
            //programa -> stack -> heap
        String m = "coffee";
        String n = "coffee";    //m e n apontam para o obj "coffee" em heap
        String o = new String("coffee");
        String p = new String("coffee");    //p != o != (a,b)

        System.out.println(m == n);     //true
        System.out.println(m == o);     //false

        System.out.println(m.equals(n));    //true
        System.out.println(m.equals(o));    //true
        System.out.println(!m.equals(o));       //jeito de falar que é !=
    }
}