public class Arrays {
    //arrays are object types
    public static void main(String[] args) {
        //Maneiras de declarar:
        int[] x = new int[4];   // x = [0, 0, 0, 0] se fosse float 0.0; boolean, false; others: null
        int[] y = {3, 5, 7, 2};

        x[2] = 12;  // x = [0, 0, 12, 0];
        //y[4] = 21;  //error, além do alcance
        System.out.println(y[1]);

        //arrays de 2 dimensões:
        int[][] a = new int[3][4];  //int[l][c] na matriz
        int[][] b = {{3, 4, 5},
                    {5, 4, 6}};
    }
}