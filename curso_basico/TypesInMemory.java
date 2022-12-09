public class TypesInMemory {
    public static void main(String[] args) {
        //primitive         Armazenados na stack
        int a = 3;
        int b = a;
        b = 100;    //b != a

        //reference         Ponteiro armazenado na stack
                            //que aponta p/ -> informação no Heap
        int[] c = {1, 2, 3, 4};
        int[] d = c;    //d e c apontam para o mesmo local
        d[1] = 99;          //c[1] = 99 também
        d = new int[5];         //d aponta agora para outro local; d != c

        int[] e = {5, 6, 7, 8};
        int[] f = {5, 6, 7, 8};     //apontam para objetos diferentes
        f[1] = 98;      //e[1] != 98


        String g = "Hello";
        String h = g;           //apontam para o mesmo objeto
        h = "Good bye!";    //h passa a apontar p/ outro objeto. Strings são imutáveis
    }
}