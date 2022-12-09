package tema12;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaBuilderTeste {
    @Test
    public void testaConstruirPessoaCompleta() throws Exception {
        Pessoa pessoa = PessoaBuilder.construtor()
                .comNome("Isabella")
                .comDataDeNascimento(new SimpleDateFormat("dd/M/yyyy").parse("21/08/1995"))
                .comEndereco("Av. João XXIII", 768, "Uberlândia", "MG")
                .build();

        assertNotNull(pessoa);
        assertTrue(pessoa.getNome().equals("Isabella"));
        assertTrue(pessoa.getDataDeNascimento().equals(new SimpleDateFormat("dd/M/yyyy").parse("21/08/1995")));
        assertTrue(pessoa.getEndereco().getRua().equals("Av. João XXIII"));
        assertEquals(768, pessoa.getEndereco().getNumero());
        assertTrue(pessoa.getEndereco().getCidade().equals("Uberlândia"));
        assertTrue(pessoa.getEndereco().getEstado().equals("MG"));
    }

    @Test
    public void testaConstruirPessoaIncompleta() throws Exception {
        Pessoa pessoa1 = PessoaBuilder.construtor()
                .comNome("Isabella")
                .build();

        Pessoa pessoa2 = PessoaBuilder.construtor()
                .comDataDeNascimento(new SimpleDateFormat("dd/M/yyyy").parse("21/08/1995"))
                .build();

        Pessoa pessoa3 = PessoaBuilder.construtor()
                .comEndereco("Av. João XXIII", 768, "Uberlândia", "MG")
                .build();

        assertNotNull(pessoa1);
        assertNotNull(pessoa2);
        assertNotNull(pessoa3);

        assertTrue(pessoa1.getNome().equals("Isabella"));
        assertNull(pessoa1.getDataDeNascimento());
        assertNull(pessoa1.getEndereco());

        assertNull(pessoa2.getNome());
        assertTrue(pessoa2.getDataDeNascimento().equals(new SimpleDateFormat("dd/M/yyyy").parse("21/08/1995")));
        assertNull(pessoa2.getEndereco());


        assertNull(pessoa3.getNome());
        assertNull(pessoa3.getDataDeNascimento());
        assertTrue(pessoa3.getEndereco().getRua().equals("Av. João XXIII"));
        assertEquals(768, pessoa3.getEndereco().getNumero());
        assertTrue(pessoa3.getEndereco().getCidade().equals("Uberlândia"));
        assertTrue(pessoa3.getEndereco().getEstado().equals("MG"));
    }
}
