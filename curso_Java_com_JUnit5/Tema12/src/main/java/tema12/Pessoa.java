package tema12;

import java.time.LocalDate;
import java.util.Date;

public class Pessoa implements IPessoa {
    private String nome;
    private Date dataDeNascimento;
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, Date dataDeNascimento, Endereco endereco) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    @Override
    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String printPessoa() {
        return "Nome: " + this.nome + "\nData de Nascimento: " + this.dataDeNascimento + "\nEndereço: " + this.endereco.printEndereco();
    }
}
