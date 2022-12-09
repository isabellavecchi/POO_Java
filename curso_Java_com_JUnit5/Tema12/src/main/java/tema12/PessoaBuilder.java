package tema12;

import java.util.Date;

public class PessoaBuilder {
    private Pessoa pessoa;

    public PessoaBuilder() {
        this.pessoa = new Pessoa();
    }

    public static PessoaBuilder construtor() {
        return new PessoaBuilder();
    }

    public PessoaBuilder comNome(String nome) {
        this.pessoa.setNome(nome);
        return this;
    }

    public PessoaBuilder comDataDeNascimento(Date nascimento) {
        this.pessoa.setDataDeNascimento(nascimento);
        return this;
    }

    public PessoaBuilder comEndereco(String rua, int numero, String cidade, String estado) {
        Endereco endereco = new Endereco(rua, numero, cidade, estado);
        this.pessoa.setEndereco(endereco);
        return this;
    }

    public PessoaBuilder comEndereco(String rua, String cidade, String estado) {
        Endereco endereco = new Endereco(rua, cidade, estado);
        this.pessoa.setEndereco(endereco);
        return this;
    }

    public PessoaBuilder comEndereco(String cidade, String estado) {
        Endereco endereco = new Endereco(cidade, estado);
        this.pessoa.setEndereco(endereco);
        return this;
    }

    public PessoaBuilder comEndereco(String estado) {
        Endereco endereco = new Endereco(estado);
        this.pessoa.setEndereco(endereco);
        return this;
    }

    public Pessoa build() {
        return this.pessoa;
    }
}
