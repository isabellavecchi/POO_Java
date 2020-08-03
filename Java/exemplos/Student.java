public class Student {
    //variáveis da classe
    private static String mascot;   //object fields default to null
    private static int studentID;   //int fields default to 0

    private static double classCode = 123.4;    //legal

    private static double classCode2;
    //classCode2 = 567.8;     //ilegal    ->      fora de um método

    //Public methods
    public static String getMascot() {  //getter (accessor)
        return mascot;  //retorna o apontador para o localde memória da stg 'mascot'
    }

    public static void setMascot(String newMascot){     //setter (mutator)
        mascot = newMascot;
    }
}

/*
*   Private -> só podemos acessar através dos métodos get & set
*   Static -> belongs to its class itself
*   Public -> pode ser acessado fora da classe
*   method static -> [acessado pela Classe ou pelo obj da classe]
*                 -> só acessa static fields
*   method non-static -> acessa static & non-static fields
*                     -> só pode ser chamado por uma instância da classe (obj)
*/