package tema6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaContatoTest {
    private AgendaContato agendaTeste;
    private Contato ct1;
    private Contato ct3;
    private Contato ct2;

    @BeforeEach
    public void setUp() {
        agendaTeste = new AgendaContato();
        ct1 = new Contato("Jamuare", "jamu@mail.com");
        ct2 = new Contato("Hamilton", "3-14Nball@mail.com");
        ct3 = new Contato("Lucas", "+551399875463");
    }

    @Test
    public void testeAdd() {
        int qtContatos = agendaTeste.getTamanho();
        int valorEsperado = ++qtContatos;
        agendaTeste.addContato(ct1);
        qtContatos = agendaTeste.getTamanho();
        assertEquals(qtContatos,valorEsperado);
    }

    @Test
    public void testeRm() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        int qtContatos = agendaTeste.getTamanho();
        int valorEsperado = --qtContatos;
        agendaTeste.rmContato(ct2);
        qtContatos = agendaTeste.getTamanho();
        assertEquals(qtContatos,valorEsperado);
    }

    @Test
    public void testeRmPorID() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);
        int idCt2 = ct2.getId();
        int qtContatos = agendaTeste.getTamanho();
        int valorEsperado = --qtContatos;
        agendaTeste.rmContato(idCt2);
        qtContatos = agendaTeste.getTamanho();
        assertEquals(valorEsperado,qtContatos);
    }

    @Test
    public void testeListar() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);
        assertNotNull(agendaTeste.listarContatos());
        assertEquals(3, agendaTeste.getTamanho());
    }

    @Test
    public void testeEncontraNome() {
        Contato nomeTeste = new Contato("Nome de Teste", "contato");
        agendaTeste.addContato(nomeTeste);
        assertNotNull(agendaTeste.buscaNome("Nome de Teste"));
        assertEquals(0, agendaTeste.buscaNome("Nome inexistente").getTamanho());
    }

    @Test
    public void testeEncontraPorId() {
        Contato contatoIdTeste = new Contato("Nome de Teste", "contato");
        agendaTeste.addContato(contatoIdTeste);
        int id = contatoIdTeste.getId();
        Optional<Contato> contatoBuscado = agendaTeste.buscaId(id);
        assertEquals(contatoBuscado, Optional.of(contatoIdTeste));
    }

}
