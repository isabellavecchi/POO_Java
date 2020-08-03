public class CastingAPrimitiveAndOverflow
{
    public static void main(String[] args)
    {
       /*
        *CASTING A PRIMITIVE AND OVERFLOW
        *
        *   casting -> converte de um tipo para outro
        *
        *   widening -> converte para um tipo com > precisão/espaço
        *
        *   narrowing -> para um tipo com < precisão/espaço
        *
        */
        
        double a = 3.0;

        double b = 4;   //widening

        double c = (double)5;   //casting

        //int d = c;        //error
        int d = (int)c;     //narrowing
        int e = (int)3.95;  //everything after the '.' will be truncated (garbage)

        //OVERFLOW
        int x = 10;     //[-2,147,483,648; 2,147,483,647]
        byte y;         //[-128, 127]
        //y = x;              //error even thow it could be
        y = (byte)x;
        y = (byte)128;              //OVERFLOW  ~de volta para o começo~
        System.out.println(y);

            //double and float won't wrap around, vai falar que é infinito, esses malandrinhoo
    }
}