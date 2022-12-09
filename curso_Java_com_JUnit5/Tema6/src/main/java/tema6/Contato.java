package tema6;

import com.sun.jdi.StringReference;

public class Contato {
    private int id;
    private String nome;
    private String contato;
    private static int cont = 0;

    public Contato() {
        this.id = ++cont;
    }

    public Contato(String nome) {
        this.id = ++cont;
        this.nome = nome;
    }

    public Contato(String nome, String contato) {
        this.id = ++cont;
        this.nome = nome;
        this.contato = contato;
    }

    public static int getCont() {
        return cont;
    }

    public int getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getContato() {
        return this.contato;
    }

    public void printContato() {
        System.out.println("ID: " + this.id + "\nNome: " + this.nome + "\nContato: " + this.contato + "\n");
    }

}
