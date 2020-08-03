public class Operators
{
    public static void main(String[] args)
    {
        int x;
        x = 3;

        System.out.println(3 + 3);
        System.out.println(3 - 3);
        System.out.println(3 * 3);
        System.out.println(3 / 3);  //diferente para int e double

        x = 3 * 3;
        x = 3 * x;

        System.out.println(11 % 3);

        x++;    //x = x + 1;
        x--;    //x = x - 1;
        x *= 3; //x = x * 3;

        boolean z = true;
        System.out.println(!z);
    }
}