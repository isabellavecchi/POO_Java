public class MathClass {
    //All methods in the Math class are static.
    //You can't create or extend an instance of the Math class.
    public static void main(String[] args) {
        //Math.abs -> função modular, retorna o mesmo tipo
        System.out.println(Math.abs(-3.2));

        //Math.pow -> retorna double
        System.out.println(Math.pow(3, 2.0));

        //Math.sqrt -> retorns double
        System.out.println(Math.sqrt(9));

        //Math.PI -> a public variable (doube ≃ pi) ->  n é método()
        int raio = 3;
        double circunferencia = 2 * Math.PI * raio;
        System.out.println(Math.round(circunferencia));

        /* Outros Métodos importantes:
        *
        * Math.cos()
        * Math.sin()
        * Math.tan()
        * Math.log()
        * Math.max()     ->     return max of 2 values
        * Math.min()     ->     return min of 2 values
        * Math.round()   ->     returns a rounded number
        */
    }
}