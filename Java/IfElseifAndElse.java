public class IfElseifAndElse {
    public static void main(String[] args)
        {
        int z = 5;
        if(z > 10) {
            System.out.println('A');
        } else if(z >= 4)                   //uma linha n precisa de {}
            System.out.println('B');
         else if(z < 1) {
            System.out.println('C');
        } else {
            System.out.println('D');
        }

        if(z == 5)
        System.out.println('E');
    }
}