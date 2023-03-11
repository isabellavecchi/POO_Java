package trabalhofinal;

import java.util.ArrayList;
import java.util.Optional;

public class Biblioteca {
    private BibliotecaDAO bibliotecaDAO;

    public Biblioteca(BibliotecaDAO bibliotecaDAO) {
        this.bibliotecaDAO = bibliotecaDAO;
    }

    public void cadastrarLivro(Livro livro) {
        bibliotecaDAO.addLivro(livro);
    }

    public void excluirLivro(Livro livro) {
        if(!livro.isFgEmprestado()) {
            if(livro.getId() == 0) {
                System.out.println("Impossivel deletar livro sem saber seu id");
            } else {
                bibliotecaDAO.rmLivro(livro.getId());
            }
        }
    }

    public void emprestarLivro(int idLivro) {
        bibliotecaDAO.emprestarLivro(idLivro);
    }

    public void emprestarLivros(ArrayList<Livro> livros) {
        String idLivros = "0";
        for (Livro livro : livros) {
            idLivros += ", " + livro.getId();
            livro.emprestar();
        }
        bibliotecaDAO.emprestarLivros(idLivros);
    }

    public void devolverLivro(ArrayList<Livro> livros) {
        String idLivros = "";
        if(!livros.isEmpty()) {
            idLivros = String.valueOf(livros.get(0).getId());
            for (Livro livro : livros) {
                idLivros += ", " + livro.getId();
                livro.devolver();
            }
        }
        bibliotecaDAO.devolverLivros(idLivros);
    }

    public Optional<Livro> buscaLivroPorId(int id) {
        return bibliotecaDAO.buscaLivroPorId(id);
    }
    
    public void atualizaLivroPorId(int idLivro, String titulo, String autor) {
        bibliotecaDAO.atualizaLivroPorId(idLivro, titulo, autor);
    }

    public ArrayList<Livro> buscaLivroDispPorTitulo(String titulo) {
        return bibliotecaDAO.buscaLivroDispPorTitulo(titulo);
    }

    public ArrayList<Livro> buscaLivroDispPorAutor(String autor) {
        return bibliotecaDAO.buscaLivroDispPorAutor(autor);
    }

    public ArrayList<Livro> buscaLivroDispPorTituloEAutor(String titulo, String autor) {
        return bibliotecaDAO.buscaLivroDispPorTituloEAutor(titulo, autor);
    }

    public ArrayList<Livro> listarLivrosDisponiveis() {
        return bibliotecaDAO.listarLivrosDisponiveis();
    }

    public ArrayList<Livro> listarLivrosLocados() {
        return bibliotecaDAO.listarLivrosLocados();
    }

    public int getSize() {
        Optional<Integer> qtdLivros = bibliotecaDAO.getQtdLivro();
        if(qtdLivros.isPresent()) {
            return qtdLivros.get();
        }
        return 0;
    }
}
