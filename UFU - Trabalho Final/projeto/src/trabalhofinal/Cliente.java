package trabalhofinal;

public class Cliente {
    private int id = 0;
    private String nome;
    private String endereco;
    private int idade;
    private boolean fgEmDia;

    public Cliente() {
        this.fgEmDia = true;
    }

    public Cliente(String nome) {
        this.nome = nome;
        this.fgEmDia = true;
    }

    public Cliente(String nome, String endereco, int idade) {
        this.id = 0;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.fgEmDia = true;
    }

    public Cliente(String nome, String endereco, int idade, boolean fgEmDia) {
        this.id = 0;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.fgEmDia = fgEmDia;
    }

    public Cliente(int id, String nome, String endereco, int idade, boolean fgEmDia) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.fgEmDia = fgEmDia;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setFgEmDia(boolean fgEmDia) {
        this.fgEmDia = fgEmDia;
    }

    public boolean isFgEmDia() {
        return this.fgEmDia;
    }

    public void devolveuLivro(int qtDiasAtraso) {
        if (qtDiasAtraso != 0) {
            this.fgEmDia = false;
        }
    }

    public void quitouAtraso() {
        this.fgEmDia = true;
    }

    public Cliente retornaUsuario(){
        return this;
    }
    
    public void printCliente() {
        System.out.println("Cliente\nId:\t\t" + this.id);
        System.out.println("Nome:\t" + this.nome);
        System.out.println("Endereco:\t" + this.endereco);
        System.out.println("Idade:\t" + this.idade);
        if(this.fgEmDia) {
            System.out.println("Cliente em dia");
        } else{
            System.out.println("Cliente caloteiro");
        }
    }
}
