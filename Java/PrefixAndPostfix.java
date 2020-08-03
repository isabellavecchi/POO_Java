public class PrefixAndPostfix {
    public static void main(String[] args)
    {
        //EX.1:
        int y = 0;
        int z = 0;

        if(y++ == ++z) {
        System.out.println('A');
        } else if (y == z) {
        System.out.println('B');
        } else {
        System.out.println('C');
        }
        System.out.println(++y);
        System.out.println(y++);
        System.out.println(y);

        //EX.2:
        int a = 0;
        int b = 1;
//          1     1   ->  0     0   ->      0   1
        if(++a == b-- && --a == b++) {
            System.out.println("a is " + a);
            System.out.println("b is " + b);        //só funciona com " "
        }

        //Ex.3:
        int c = 0;
        int d = 0;
//          1      0 -> false, n executa o restante 1   -1
        if(++c == d-- && --c == d++)
        {} else {
            System.out.println("c is " + c);
            System.out.println("d is " + d);
        }
    }
}