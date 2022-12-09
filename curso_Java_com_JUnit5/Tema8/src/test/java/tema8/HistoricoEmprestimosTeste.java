package tema8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoEmprestimosTeste {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Clientela clientela;
    private ClientelaDAO clientelaDAO;

    private Livro livro1;
    private Livro livro2;
    private Livro livro3;
    private Livro livro4;
    private Livro livro5;
    private Livro livro6;
    private Livro livro7;
    private Biblioteca biblioteca;
    private BibliotecaDAO bibliotecaDAO;

    private Optional<Emprestimo> emprestimo1;
    private Optional<Emprestimo> emprestimo2;
    private Optional<Emprestimo> emprestimo3;
    private Optional<Emprestimo> emprestimo4;
    private HistoricoEmprestimos historicoEmprestimos;
    private HistoricoEmprestimosDAO historicoEmprestimosDAO;

    @BeforeEach
    public void setUp() {
        clientela = new Clientela();
        clientelaDAO = new ClientelaDAO(clientela);
        biblioteca = new Biblioteca();
        bibliotecaDAO = new BibliotecaDAO(biblioteca);
        historicoEmprestimos = new HistoricoEmprestimos(biblioteca,clientela);
        historicoEmprestimosDAO = new HistoricoEmprestimosDAO(biblioteca, clientela, bibliotecaDAO, clientelaDAO, historicoEmprestimos);
        historicoEmprestimosDAO.carregarTodos();

        usuario1 = new Usuario("Maria");
        usuario2 = new Usuario("José");
        usuario3 = new Usuario("Ariel");
        clientelaDAO.addUsuario(usuario1);
        clientelaDAO.addUsuario(usuario2);
        clientelaDAO.addUsuario(usuario3);

        livro1 = new Livro("Guia do Mochileiro das Galáxias", "Douglas Adams");
        livro2 = new Livro("Guerra dos Tronos", "George R. R. Martim");
        livro3 = new Livro("Hobbit", "Tolkien");
        livro4 = new Livro("As Intermitências da Morte", "José Saramago");
        livro5 = new Livro("Enchiridion", "Epiteto");
        livro6 = new Livro("Ética", "Espinoza");
        livro7 = new Livro("Harry Potter e a Pedra Filosofal", "J. K. K. Roulling");
        bibliotecaDAO.cadastrarLivro(livro1);
        bibliotecaDAO.cadastrarLivro(livro2);
        bibliotecaDAO.cadastrarLivro(livro3);
        bibliotecaDAO.cadastrarLivro(livro4);
        bibliotecaDAO.cadastrarLivro(livro5);
        bibliotecaDAO.cadastrarLivro(livro6);
        bibliotecaDAO.cadastrarLivro(livro7);
    }

    @Test
    public void testaQtTermosDoArrayIgualQtLinhasArquivo() {
        int tamanho = historicoEmprestimos.getSize();
        int nLinhas = historicoEmprestimosDAO.contaLinhas();
        assertEquals(tamanho,nLinhas);
    }

    @Test
    public void testaEmprestarLivro() {
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        int tamanho = historicoEmprestimos.getSize();
        historicoEmprestimosDAO.emprestar(livro1, usuario1);
        assertEquals(tamanho+1, historicoEmprestimos.getSize());
        testaQtTermosDoArrayIgualQtLinhasArquivo();
    }

    @Test
    public void testaMaxDe5() {
        assertEquals(0,usuario2.getQtLivrosLocados());
        historicoEmprestimosDAO.emprestar(livro1, usuario2);
        historicoEmprestimosDAO.emprestar(livro2, usuario2);
        historicoEmprestimosDAO.emprestar(livro3, usuario2);
        historicoEmprestimosDAO.emprestar(livro4, usuario2);
        historicoEmprestimosDAO.emprestar(livro5, usuario2);
        assertEquals(5,usuario2.getQtLivrosLocados());
        assertTrue(historicoEmprestimosDAO.emprestar(livro6, usuario2).isEmpty());
        assertEquals(5,usuario2.getQtLivrosLocados());
        assertFalse(livro6.isFgEmprestado());
    }

    @Test
    public void testaPrazoDeEntrega7Dias() {
        int dias = 8;
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro7, usuario3);
        assertTrue(emprestimo1.isPresent());
        emprestimo1.get().setDataLocacao(LocalDate.now().minusDays(dias));
        assertTrue(usuario3.isFgEmDia());
        historicoEmprestimos.atualizaHistoricoEUsuario(usuario3);
        historicoEmprestimosDAO.devolver(emprestimo1.get());
        assertFalse(usuario3.isFgEmDia());
        assertFalse(livro2.isFgEmprestado());
        assertTrue(historicoEmprestimosDAO.emprestar(livro2, usuario3).isEmpty());
        assertFalse(livro2.isFgEmprestado());
    }

    @Test
    public void testaDevolverLivro() {
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro4, usuario1);
        assertTrue(emprestimo1.isPresent());
        assertTrue(livro4.isFgEmprestado());
        historicoEmprestimosDAO.devolver(emprestimo1.get());
        assertFalse(livro4.isFgEmprestado());
    }

    @Test
    public void testaDevolverVariosLivros() {
        historicoEmprestimosDAO.emprestar(livro1, usuario2);
        historicoEmprestimosDAO.emprestar(livro2, usuario2);
        historicoEmprestimosDAO.emprestar(livro3, usuario2);
        assertTrue(livro1.isFgEmprestado() && livro2.isFgEmprestado() && livro3.isFgEmprestado());

        ArrayList<Livro> livrosEmprestadosAoUsuario2 = new ArrayList<Livro>();
        livrosEmprestadosAoUsuario2.add(livro1);
        livrosEmprestadosAoUsuario2.add(livro2);
        livrosEmprestadosAoUsuario2.add(livro3);

        historicoEmprestimosDAO.devolverVariosLivros(usuario2, livrosEmprestadosAoUsuario2);
        assertFalse(livro1.isFgEmprestado() && livro2.isFgEmprestado() && livro3.isFgEmprestado());
    }

    @Test
    public void testaRenovar() {
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro7, usuario3);
        assertTrue(emprestimo1.isPresent());
        int dias = 6;
        emprestimo1.get().setDataLocacao(LocalDate.now().minusDays(dias));
        assertTrue(emprestimo1.get().getDataADevolver().equals(LocalDate.now().plusDays(7 - dias)));
        historicoEmprestimosDAO.renovar(emprestimo1.get());
        assertTrue(emprestimo1.get().getDataADevolver().equals(LocalDate.now().plusDays(7)));
    }

    @Test
    public void testaRenovarAtrasado() {
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro7, usuario3);
        assertTrue(emprestimo1.isPresent());
        int dias = 9;
        emprestimo1.get().setDataLocacao(LocalDate.now().minusDays(dias));
        assertTrue(emprestimo1.get().getDataADevolver().equals(LocalDate.now().plusDays(7-dias)));
        assertThrows(IllegalArgumentException.class, () -> historicoEmprestimosDAO.renovar(emprestimo1.get()));
    }

    @Test
    public void testaMultaAtraso() {
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro7, usuario3);
        assertTrue(emprestimo1.isPresent());
        int dias = 9;
        emprestimo1.get().setDataLocacao(LocalDate.now().minusDays(dias));
        int saldoDevedor = emprestimo1.get().getUsuario().getSaldoDevedor();
        historicoEmprestimosDAO.devolver(emprestimo1.get());
        assertEquals(2 * Emprestimo.getMultasReaisPorDiaDeAtraso(), emprestimo1.get().getUsuario().getSaldoDevedor() - saldoDevedor);
    }

    @Test
    public void testaRelatorioLivrosEmprestadosXUsuario() {
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro1, usuario1);
        emprestimo2 = historicoEmprestimosDAO.emprestar(livro2, usuario2);
        emprestimo3 = historicoEmprestimosDAO.emprestar(livro3, usuario2);
        emprestimo4 = historicoEmprestimosDAO.emprestar(livro4, usuario3);
        historicoEmprestimosDAO.devolver(emprestimo2.get());

        ArrayList<Emprestimo> relatorio = historicoEmprestimosDAO.relatorioLivrosEmprestados();
        assertTrue(relatorio.contains(emprestimo1.get()));
        assertFalse(relatorio.contains(emprestimo2.get()));
        assertTrue(relatorio.contains(emprestimo3.get()));
        assertTrue(relatorio.contains(emprestimo4.get()));
    }

    @Test
    public void testaTop10() {
        historicoEmprestimosDAO.emprestar(livro5, usuario1);
        historicoEmprestimosDAO.emprestar(livro6, usuario1);
        historicoEmprestimosDAO.emprestar(livro1, usuario2);
        historicoEmprestimosDAO.emprestar(livro2, usuario2);
        historicoEmprestimosDAO.emprestar(livro3, usuario2);
        historicoEmprestimosDAO.emprestar(livro4, usuario2);
        historicoEmprestimosDAO.emprestar(livro7, usuario3);
        for (int i=0; i<7; i++) {
            Livro livro = new Livro("teste");
            biblioteca.cadastrarLivro(livro);
            Usuario usuario = new Usuario(Integer.toString(i));
            clientela.addUsuario(usuario);
            historicoEmprestimosDAO.emprestar(livro , usuario);
        }

        ArrayList<Usuario> top10 = clientela.topUsuarios();
        for (int i = 0; i < top10.size() - 1; i++) {
            assertTrue(top10.get(i).getQtLivrosLocadosDesdeSempre() >= top10.get(i+1).getQtLivrosLocadosDesdeSempre());
        }
    }

    @Test
    public void testaRelatorioAtraso() {
        int dias = 9;
        emprestimo1 = historicoEmprestimosDAO.emprestar(livro7, usuario3);
        assertTrue(emprestimo1.isPresent());
        emprestimo1.get().setDataLocacao(LocalDate.now().minusDays(dias));

        emprestimo2 = historicoEmprestimosDAO.emprestar(livro6, usuario2);
        assertTrue(emprestimo2.isPresent());
        emprestimo2.get().setDataLocacao(LocalDate.now().minusDays(dias));

        emprestimo3 = historicoEmprestimosDAO.emprestar(livro4, usuario1);
        assertTrue(emprestimo3.isPresent());

        emprestimo4 = historicoEmprestimosDAO.emprestar(livro5, usuario1);
        assertTrue(emprestimo4.isPresent());
        emprestimo4.get().setDataLocacao(LocalDate.now().minusDays(dias));

        ArrayList<Emprestimo> emprestimosAtrasados = historicoEmprestimosDAO.relatorioEmprestimosAtrasados();
        assertTrue(emprestimosAtrasados.contains(emprestimo1.get()));
        assertTrue(emprestimosAtrasados.contains(emprestimo2.get()));
        assertFalse(emprestimosAtrasados.contains(emprestimo3.get()));
        assertTrue(emprestimosAtrasados.contains(emprestimo4.get()));

        assertEquals(dias-7, emprestimo1.get().getQtDiasAtraso());
    }
}
