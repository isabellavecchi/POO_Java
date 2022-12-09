package tema8;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private String nome;
    private String endereco;
    private int idade;

    private int qtLivrosLocados;
    private boolean fgEmDia;
    private int saldoDevedor;
    private int qtLivrosLocadosDesdeSempre;

    private static int cont=0;

    public Usuario() {
        this.id = ++cont;
        this.qtLivrosLocados =0;
        this.fgEmDia = true;
        saldoDevedor=0;
        this.qtLivrosLocadosDesdeSempre=0;
    }

    public Usuario(String nome) {
        this.id = ++cont;
        this.nome = nome;
        this.qtLivrosLocados =0;
        this.fgEmDia = true;
        saldoDevedor=0;
        this.qtLivrosLocadosDesdeSempre=0;
    }

    public Usuario(String nome, String endereco, int idade) {
        this.id = ++cont;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.qtLivrosLocados =0;
        this.fgEmDia = true;
        saldoDevedor=0;
        this.qtLivrosLocadosDesdeSempre=0;
    }

    public Usuario(int id, String nome, String endereco, int idade, int qtLivrosLocados, boolean fgEmDia, int saldoDevedor, int qtLivrosLocadosDesdeSempre) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.qtLivrosLocados = qtLivrosLocados;
        this.fgEmDia = fgEmDia;
        this.saldoDevedor = saldoDevedor;
        this.qtLivrosLocadosDesdeSempre = qtLivrosLocadosDesdeSempre;
    }

    public static void setCont(int cont) {
        Usuario.cont = cont;
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

    public int getQtLivrosLocados() {
        return this.qtLivrosLocados;
    }

    public int getQtLivrosLocadosDesdeSempre() {
        return this.qtLivrosLocadosDesdeSempre;
    }

    private void setQtLivrosLocadosDesdeSempre(int qtLivrosLocadosDesdeSempre) {
        this.qtLivrosLocadosDesdeSempre = qtLivrosLocadosDesdeSempre;
    }

    public void setFgEmDia(boolean fgEmDia) {
        this.fgEmDia = fgEmDia;
    }

    public boolean isFgEmDia() {
        return this.fgEmDia;
    }

    public int getSaldoDevedor() {
        return this.saldoDevedor;
    }

    public void setSaldo (int saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public void pegouLivro() {
        if (!this.estaNoLimiteDeLivros()) {
            this.qtLivrosLocados += 1;
            this.qtLivrosLocadosDesdeSempre += 1;
        }
    }

    public void devolveuLivro(int qtDiasAtraso) {
        this.qtLivrosLocados -= 1;
        if (qtDiasAtraso != 0) {
            this.saldoDevedor += qtDiasAtraso * Emprestimo.getMultasReaisPorDiaDeAtraso();
            this.fgEmDia = false;
        }
    }

    public boolean estaNoLimiteDeLivros() {
        if (this.qtLivrosLocados >= 5) {
            return true;
        }
        return false;
    }

    public void pagouQtSaldo(int saldoDevedor) {
        this.saldoDevedor -= saldoDevedor;
    }

    public void addSaldoDevedor (int qtDiasAtraso) {
        this.saldoDevedor += qtDiasAtraso * Emprestimo.getMultasReaisPorDiaDeAtraso();
        if (qtDiasAtraso != 0) {
            this.fgEmDia = false;
        }
    }

    public void quitouAtraso() {
        this.fgEmDia = true;
        this.saldoDevedor = 0;
    }

    public Usuario retornaUsuario(){
        return this;
    }

    public String geraString() {
        String string = this.getId() + ";" + this.getNome() + ";" + this.getEndereco() + ";" + this.getIdade();
        string += ";" + this.getQtLivrosLocados() + ";" + this.isFgEmDia() + ";" + this.getSaldoDevedor() + ";";
        string += this.getQtLivrosLocadosDesdeSempre();
        return string;
    }

    public static Usuario carregaPorString(String entrada) {
        String[] arrFields = entrada.split(";");
        int id = Integer.parseInt(arrFields[0]);
        String nome = arrFields[1];
        String endereco = arrFields[2];
        int idade = Integer.parseInt(arrFields[3]);
        int qtLivrosLocados = Integer.parseInt(arrFields[4]);
        boolean fgEmDia = Boolean.parseBoolean(arrFields[5]);
        int saldoDevedor = Integer.parseInt(arrFields[6]);
        int qtLivrosLocadosDesdeSempre = Integer.parseInt(arrFields[7]);
        Usuario usuarioBaixado = new Usuario(id, nome, endereco, idade, qtLivrosLocados, fgEmDia, saldoDevedor, qtLivrosLocadosDesdeSempre);
        return usuarioBaixado;
    }

    @Override
    public int compareTo(Usuario o) {
        return Integer.toString(o.getQtLivrosLocadosDesdeSempre()).compareTo(Integer.toString(this.getQtLivrosLocadosDesdeSempre()));
    }
}
