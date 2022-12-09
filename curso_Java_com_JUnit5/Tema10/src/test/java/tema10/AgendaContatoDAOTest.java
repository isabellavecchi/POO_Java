package tema10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaContatoDAOTest {
    private ConectaBD conectaBD;
    private AgendaContato agendaTeste;
    private Contato ct1;
    private Contato ct3;
    private Contato ct2;
    private AgendaContatoDAO agendaContatoDAO;

    @BeforeEach
    public void setUp() {
        conectaBD = new ConectaBD();
        agendaContatoDAO = new AgendaContatoDAO(conectaBD);
        agendaTeste = new AgendaContato(agendaContatoDAO);
        ct1 = new Contato("Jamuare", "jamu@mail.com", false);
        ct2 = new Contato("Hamilton", "(19)996654723", "3-14NballF1@mail.com");
        ct3 = new Contato("Lucas", "+551399875463", true);
    }

    @Test
    public void testeAdd() {
        Optional<Integer> tamanho = agendaTeste.getTamanho();
        if (tamanho.isPresent()) {
            int qtContatos = tamanho.get();

            int valorEsperado = ++qtContatos;
            agendaTeste.addContato(ct1); 
            tamanho = agendaTeste.getTamanho();
            if (tamanho.isPresent()) {
                assertEquals(tamanho.get(), valorEsperado);
            }
        } else {
            assertEquals(0, 1);
        }
    }

    @Test
    public void testeRmPorID() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);
        Optional<Integer> tamanho = agendaTeste.getTamanho();
        if (tamanho.isPresent()) {
            int qtContatos = tamanho.get();
            int valorEsperado = --qtContatos;
            agendaTeste.rmContato(ct2.getId());
            qtContatos = agendaTeste.getTamanho().get();
            assertEquals(valorEsperado, qtContatos);

            Optional<Contato> contatoNulo = agendaTeste.buscaContatoPorId(Optional.of(ct2.getId()));
            assertTrue(contatoNulo.isEmpty());
        } else {
            assertEquals(0, 1);
        }
    }

    @Test
    public void testeListar() {

        Optional<Integer> tamanho = agendaTeste.getTamanho();
        assertTrue(tamanho.isPresent());
        int qtContatos = tamanho.get();

        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);

        assertNotNull(agendaTeste.listarContatos());

        tamanho = agendaTeste.getTamanho();
        assertTrue(tamanho.isPresent());
        assertEquals(qtContatos + 3, tamanho.get());
    }

    @Test
    public void testeListarOrdemAlfabetica() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);
        ArrayList<Contato> agendaOrdenada = agendaTeste.listarContatosOrdemAlfabetica();
        for (int i=0; i<agendaOrdenada.size()-1; i++) {
            agendaOrdenada.get(i).printContato();
            agendaOrdenada.get(i+1).printContato();
            assertTrue(agendaOrdenada.get(i).getNome().toLowerCase().compareTo(agendaOrdenada.get(i+1).getNome().toLowerCase()) <= 0);
            System.out.println();
        }
    }

    @Test
    public void testeListarOrdemId() {
        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);
        agendaTeste.addContato(ct3);
        ArrayList<Contato> agendaOrdenada = agendaTeste.listarContatosOrdemId();
        for (int i=0; i<agendaOrdenada.size()-1; i++) {
            assertTrue(agendaOrdenada.get(i).getId() < agendaOrdenada.get(i+1).getId());
        }
    }

    @Test
    public void testeEncontraNome() {
        Contato nomeTeste = new Contato("Nome de Teste", "contato", true);
        Contato nomeParecido = new Contato("nOme parecido");
        agendaTeste.addContato(nomeTeste);
        agendaTeste.addContato(nomeParecido);
        ArrayList<Contato> nomesParecidos = agendaContatoDAO.buscaContatoPorNome("noME");
            for (Contato contato : nomesParecidos) {
                assertTrue(contato.getNome().toLowerCase().contains(("noME").toLowerCase()));
            }
        assertEquals(0, agendaTeste.buscaContatoPorNome("inexistente").size());
    }

    @Test
    public void testeEncontraPorId() {
        Contato contatoIdTeste = new Contato("Nome de Teste", "contato", false);
        agendaTeste.addContato(contatoIdTeste);
        Contato contatoBuscado = agendaTeste.buscaContatoPorId(Optional.of(contatoIdTeste.getId())).get();
        assertEquals(contatoBuscado.getId(),contatoIdTeste.getId());
        assertEquals(contatoBuscado.getNome(),contatoIdTeste.getNome());
        assertEquals(contatoBuscado.getEmail(),contatoIdTeste.getEmail());
        assertEquals(contatoBuscado.getTelefone(),contatoIdTeste.getTelefone());
    }


}
