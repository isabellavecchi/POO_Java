package tema12;

import java.util.Date;

public interface IPessoa {
    public void setNome(String nome);

    public void setDataDeNascimento(Date dataDeNascimento);

    public void setEndereco(Endereco endereco);

    public String printPessoa();
}
