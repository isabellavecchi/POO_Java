public class Strings {
    public static void main(String[] args) {
        //Maneiras de declarar
        String a = "Hello";
        String b = "Hello";     //a == b (endereço)

        String c = new String("Hello"); //cria um outro obj em Heap
        String d = new String("Hello");

        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println(a.equals(b));
        System.out.println(c.equals(d));

        System.out.println(a.equals("Hello"));

        a = "Hola";  //cria um novo ob "Hola" em Heap

        String e = "hi";
        String f = e;
        String g = "howdy";
        String h = "HI";

        System.out.println(e.equals(f));
        System.out.println(e.equals(h));

        System.out.println(e.equalsIgnoreCase(h));
        System.out.println(g.length());
        System.out.println(g.substring(2));     //começa no índice 2 -> final
        System.out.println(g.substring(1,4));   //de um índice a (outro - 1)
        System.out.println(h.compareTo(e));     // 0 -> são iguais; o outro valor é bom para organizar em ordem alfabética
                                                //ignoreCase junto seria interessante
        
        int i = h.length();
        System.out.println(e.equals("hi"));
    }
}