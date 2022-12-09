package tema10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class AgendaContatoTest {
    private AgendaContato agendaTeste;
    private Contato ct1;
    private Contato ct3;
    private Contato ct2;
    private AgendaContatoDAO mockAgendaDAO;

    private ArrayList<Contato> contatos;

    @BeforeEach
    public void setUp() {
        mockAgendaDAO = Mockito.mock(AgendaContatoDAO.class);
        agendaTeste = new AgendaContato(mockAgendaDAO);
        ct1 = new Contato("Jamuare", "jamu@mail.com", false);
        ct2 = new Contato("Hamilton", "(19)996654723", "3-14NballF1@mail.com");
        ct3 = new Contato("Lucas", "+551399875463", true);
        contatos = new ArrayList<Contato>();
    }

    @Test
    public void testeAdd() {
        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                ct1.setId(1);
                contatos.add(ct1);
                return Optional.of(1);
            }
        }).when(mockAgendaDAO).addContato(ct1);

        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                return Optional.of(contatos.size());
            }
        }).when(mockAgendaDAO).getTamanho();


        Optional<Integer> tamanho = agendaTeste.getTamanho();
        assertTrue(tamanho.isPresent());
        assertTrue(agendaTeste.validaContato(Optional.of(ct1)));

        int qtContatos = tamanho.get();
        int valorEsperado = ++qtContatos;
        agendaTeste.addContato(ct1);
        tamanho = agendaTeste.getTamanho();
        assertTrue(tamanho.isPresent());
        assertEquals(valorEsperado,tamanho.get());
        assertTrue(contatos.contains(ct1));
    }

    @Test
    public void testeRmPorID() {
        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                ct1.setId(1);
                contatos.add(ct1);
                return Optional.of(1);
            }
        }).when(mockAgendaDAO).addContato(ct1);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock a) throws Throwable {
                contatos.remove(ct1);
                return null;
            }
        }).when(mockAgendaDAO).rmContato(1);

        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                return Optional.of(contatos.size());
            }
        }).when(mockAgendaDAO).getTamanho();

        when(mockAgendaDAO.buscaContatoPorId(1)).thenReturn(Optional.of(ct1));


        agendaTeste.addContato(ct1);
        assertTrue(contatos.contains(ct1));
        Optional<Integer> tamanho = agendaTeste.getTamanho();
        assertTrue(tamanho.isPresent());

        int qtContatos = tamanho.get();
        int valorEsperado = --qtContatos;
        agendaTeste.rmContato(ct1.getId());
        assertFalse(contatos.contains(ct1));

        qtContatos = agendaTeste.getTamanho().get();
        assertEquals(valorEsperado, qtContatos);

    }

    @Test
    public void testeListagens() {
        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                ct1.setId(1);
                contatos.add(ct1);
                return Optional.of(1);
            }
        }).when(mockAgendaDAO).addContato(ct1);

        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                ct2.setId(2);
                contatos.add(ct2);
                return Optional.of(2);
            }
        }).when(mockAgendaDAO).addContato(ct2);

        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                return Optional.of(contatos.size());
            }
        }).when(mockAgendaDAO).getTamanho();

        when(mockAgendaDAO.listarContatos()).thenReturn(contatos);

        agendaTeste.addContato(ct1);
        agendaTeste.addContato(ct2);

        assertNotNull(agendaTeste.listarContatos());
        assertEquals(2, contatos.size());
        assertEquals(contatos.size(), agendaTeste.getTamanho().get());
    }

    @Test
    public void testeEncontraNome() {
        Contato nomeTeste = new Contato("Nome de Teste", "contato", true);
        Contato nomeParecido = new Contato("nOme parecido");
        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                nomeTeste.setId(2);
                contatos.add(nomeTeste);
                return Optional.of(2);
            }
        }).when(mockAgendaDAO).addContato(nomeTeste);

        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                nomeParecido.setId(3);
                contatos.add(nomeParecido);
                return Optional.of(3);
            }
        }).when(mockAgendaDAO).addContato(nomeParecido);

        doAnswer(new Answer<ArrayList<Contato>>() {
            @Override
            public ArrayList<Contato> answer(InvocationOnMock a) throws Throwable {
                ArrayList<Contato> nomesParecidos = new ArrayList<Contato>();
                for (Contato contato : contatos) {
                    if (contato.getNome().toLowerCase().contains(("noME").toLowerCase())) {
                        nomesParecidos.add(contato);
                    }
                }
                return nomesParecidos;
            }
        }).when(mockAgendaDAO).buscaContatoPorNome("noME");

        agendaTeste.addContato(nomeTeste);
        agendaTeste.addContato(nomeParecido);
        assertTrue(agendaTeste.buscaContatoPorNome("noME").size() >= 2);
        assertEquals(0, agendaTeste.buscaContatoPorNome("inexistente").size());
    }

    @Test
    public void testeEncontraPorId() {
        doAnswer(new Answer<Optional<Integer>>() {
            @Override
            public Optional<Integer> answer(InvocationOnMock a) throws Throwable {
                ct1.setId(1);
                contatos.add(ct1);
                return Optional.of(1);
            }
        }).when(mockAgendaDAO).addContato(ct1);

        doAnswer(new Answer<Optional<Contato>>() {
            @Override
            public Optional<Contato> answer(InvocationOnMock a) throws Throwable {
                ArrayList<Contato> nomesParecidos = new ArrayList<Contato>();
                for (Contato contato : contatos) {
                    if (contato.getId() == 1) {
                        return Optional.of(contato);
                    }
                }
                return Optional.empty();
            }
        }).when(mockAgendaDAO).buscaContatoPorId(1);

        Optional<Integer> id = agendaTeste.addContato(ct1);
        Optional<Contato> contatoBuscado = agendaTeste.buscaContatoPorId(id);
        assertTrue(contatoBuscado.isPresent());
        assertTrue(contatoBuscado.get().equals(ct1));
    }


}
