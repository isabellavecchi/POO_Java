package tema7;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Conversor {
    private Map <String,Integer> dicionario = new HashMap<String,Integer>();

    public Conversor() {
        dicionario.put("I",  1);
        dicionario.put("V",  5);
        dicionario.put("X", 10);
        dicionario.put("IV", 4);
        dicionario.put("IX", 9);
    }

    public int getInt(String romano) {
        return dicionario.get(romano);
    }


    public int converteParaRom (String numRomano) {
        if (numRomano.contains("XXXX") || numRomano.contains("VV") || numRomano.contains("IIII") || numRomano.contains("VX")) {
            throw new IllegalArgumentException("Número romano digitado com formato inválido");
        }

        if (!numRomano.matches("[XIV]+")) {
            throw new IllegalArgumentException("Números romanos podem conter apenas as letras X, I e V.");
        }

        int tamString = numRomano.length();
        int resultado = 0;
        char ch;

        int proxNum;
        int numAtual;

        ch = numRomano.charAt(tamString-1);
        resultado = getInt(Character.toString(ch));

        for (int i = tamString - 1; i > 0; i--) {
            ch = numRomano.charAt(i);
            numAtual = getInt(Character.toString(ch));
            ch = numRomano.charAt(i-1);
            proxNum = getInt(Character.toString(ch));
            resultado = (proxNum >= numAtual) ? (resultado + proxNum) : (resultado - proxNum);
        }

        if (resultado > 20) {
            throw new IllegalArgumentException("Valor numérico fora do limite permitido [1, 20]");
        }
        return resultado;
    }

    public String converteParaRom (int numero) {
        if (numero < 0 || numero > 20) {
            throw new IllegalArgumentException("Valor numérico fora do limite permitido [1, 20]");
        }
        String convertido = "";
        while (numero > 0) {

            //Pegando o maior valor em algarismo romano possível, sendo que ele deve ser menor que o valor atual do número
            String maiorAlgarismoRomanoPossivel = "I";
            for (String key : dicionario.keySet()) {
                if ( ( dicionario.get(key).compareTo(dicionario.get(maiorAlgarismoRomanoPossivel)) > 0 ) && ( dicionario.get(key).compareTo(numero) <= 0) ) {
                    maiorAlgarismoRomanoPossivel = key;
                }
            }

            convertido += maiorAlgarismoRomanoPossivel;
            numero = numero - dicionario.get(maiorAlgarismoRomanoPossivel);
        }
        return convertido;
    }
}
