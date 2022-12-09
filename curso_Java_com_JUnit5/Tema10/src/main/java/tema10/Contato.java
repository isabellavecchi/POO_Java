package tema10;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private static int cont = 0;

    public Contato() {
    }

    public Contato(String nome) {
        this.nome = nome;
    }

    public Contato(String nome, String telefoneOuEmail, boolean isTelefone) {
        this.nome = nome;
        if (isTelefone) {
            this.telefone = telefoneOuEmail;
        } else {
            this.email = telefoneOuEmail;
        }
    }

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public static int getCont() {
        return cont;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeQuoted() {
        if (this.nome != null) {
            return "'" + this.nome + "'";
        }
        return this.nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneQuoted() {
        if (this.telefone != null) {
            return "'" + this.telefone + "'";
        }
        return this.telefone;
    }

    public String getEmailQuoted() {
        if (this.email != null) {
            return "'" + this.email + "'";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void printContato() {
        System.out.println("ID: " + this.id + "\nNome: " + this.nome + "\ntelefone: "
                + this.telefone + "\nemail: " + this.email);
    }

}
