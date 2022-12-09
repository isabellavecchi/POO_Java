public class Null {
    /*
    *   Important facts:
    *
    *   - null can be thought of as a reference that doesn't point at an object;
    *
    *   - variável inicializada como null != variável n inicializada;
    *
    *   - vc pode verificar se a var é nula através de linhas de código antes
    * de utilizá-la.
    */
    //variáveis locais
    int[] x;           //n aponta
    int[] y = null;    //aponta p/ nada
    int[] m = {2, 3, 5, 8};

    //x[1] = 9;     //Erro de compilação: n está apontando p/ nada
    //y[1] = 9;     //Erro de running: aponta para nenhum objeto que possa ser mudado
    m[1] = 9;
}