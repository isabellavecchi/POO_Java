package tema8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTeste {
    private Livro livro1;
    private Livro livro2;
    private Livro livro3;

    private Biblioteca biblioteca;
    private BibliotecaDAO bibliotecaDAO;

    @BeforeEach
    public void setBiblioteca() {
        livro1 = new Livro("Guia do Mochileiro das Galáxias", "Douglas Adams");
        livro2 = new Livro("Guerra dos Tronos", "George R. R. Martim");
        livro3 = new Livro("Hobbit", "Tolkien");

        biblioteca = new Biblioteca();
        bibliotecaDAO = new BibliotecaDAO(biblioteca);

        bibliotecaDAO.carregarBiblioteca();

        bibliotecaDAO.cadastrarLivro(livro1);
        bibliotecaDAO.cadastrarLivro(livro2);
        bibliotecaDAO.cadastrarLivro(livro3);
    }

    @Test
    public void testaQtTermosDoArrayIgualQtLinhasArquivo() {
        int tamanho = biblioteca.getSize();
        int nLinhas = bibliotecaDAO.contaLinhas();
        assertEquals(tamanho,nLinhas);
    }

    @Test
    public void testaCadastro() {
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        int tamanho = biblioteca.getSize();
        Livro livro4 = new Livro("Novo Livro");
        bibliotecaDAO.cadastrarLivro(livro4);
        assertEquals(tamanho + 1, biblioteca.getSize());
        testaQtTermosDoArrayIgualQtLinhasArquivo();
    }

    @Test
    public void testaExcluirLivro() {
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        int tamanho = biblioteca.getSize();
        bibliotecaDAO.excluirLivro(livro3);
        assertFalse(livro3.isFgAtivo());
        assertEquals(tamanho, biblioteca.getSize());
        testaQtTermosDoArrayIgualQtLinhasArquivo();
    }

    @Test
    public void testaExcluirLivroEmprestado() {
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        int tamanho = biblioteca.getSize();
        livro3.emprestar();
        bibliotecaDAO.excluirLivro(livro3);
        assertTrue(livro3.isFgAtivo());
        assertEquals(tamanho, biblioteca.getSize());
        testaQtTermosDoArrayIgualQtLinhasArquivo();
    }

    @Test
    public void testaListarLivros() {
        ArrayList<Livro> bibliotecaTeste = bibliotecaDAO.retornaBiblioteca();
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        assertEquals(bibliotecaTeste.size(), biblioteca.getSize());
    }

    @Test
    public void testaBuscarPorTitulo() {
        ArrayList<Livro> livrosBuscados = biblioteca.buscaLivroPorTitulo("mochileiro");
        assertTrue(livrosBuscados.contains(livro1));
    }

    @Test
    public void testaBuscarPorAutor() {
        ArrayList<Livro> livrosBuscados = biblioteca.buscaLivroPorAutor("tolkien");
        assertTrue(livrosBuscados.contains(livro3));
    }

    @Test
    public void testaEmprestarLivro () {
        assertFalse(livro1.isFgEmprestado());
        bibliotecaDAO.emprestarLivro(livro1);
        assertTrue(livro1.isFgEmprestado());
    }

    @Test
    public void testaDevolverLivro() {
        bibliotecaDAO.emprestarLivro(livro1);
        assertTrue(livro1.isFgEmprestado());
        bibliotecaDAO.devolverLivro(livro1);
        assertFalse(livro1.isFgEmprestado());
    }
}
