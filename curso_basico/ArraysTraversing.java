public class ArraysTraversing {
    public static void main(String[] args) {
        int[] x = {2, 1, 5, 8};
        String response = "x = [";

        for(int i = 0; i < x.length; i++) {
            System.out.println(x[i] + " ");
            if(i != (x.length - 1))
                response += x[i] + ", ";
            else
                response += x[i];
        }
        response += "].";
        System.out.println(response);

        for(int t : x) {        //'t' deve ser do mesmo de 'x'
            System.out.println(t);  //t = x[i] do ex. anterior
            /* Sempre percorre o vetor inteiro;
            Não dá para alterar valores no array utilizando t. */
        }

        //Arrays de 2 dimensões
        response = "arr = \n";
        int[][] arr = {{2, 3, 1},
                        {8, 5, 6}};
        for(int l = 0; l < arr.length; l++) {
            response += "[";
            for(int c = 0; c < arr[l].length; c++) {
                if(c != 2) {
                    response += arr[l][c] + ", ";
                }
                else {
                    response += arr[l][c] +"]\n";
                }
            }
        }
        System.out.println(response);

        //OBS IIMMMMPPPPPPPP!!!!!!!******$#$&*&$#$@#$#$@%#$@#$@#$$%
        //print() != println
        //println(...) = print("\n...")

        int[][] array = {{4, 2},
                         {6, 3}};
        for(int[] row : array) {
            for(int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }
    }
}