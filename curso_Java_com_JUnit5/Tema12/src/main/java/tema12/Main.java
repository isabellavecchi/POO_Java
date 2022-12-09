package tema12;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args0) throws Exception {
        Pessoa pessoa = PessoaBuilder.construtor()
                .comNome("Isabella")
                .comDataDeNascimento(new SimpleDateFormat("dd/M/yyyy").parse("21/08/1995"))
                .comEndereco("Av. João XXIII", 768, "Uberlândia", "MG")
                .build();

        System.out.println(pessoa.printPessoa());
    }
}
