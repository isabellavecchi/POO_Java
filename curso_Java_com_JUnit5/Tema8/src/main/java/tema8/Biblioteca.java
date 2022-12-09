package tema8;

import java.util.ArrayList;
import java.util.Optional;

public class Biblioteca {
    private ArrayList<Livro> biblioteca;

    public Biblioteca() {
        biblioteca = new ArrayList<Livro>();
    }

    public void atualizaCont() {
        int proximoId=0;
        for (Livro livro : biblioteca) {
            if (livro.getId() > proximoId) {
                proximoId = livro.getId();
            }
        }
        Livro.setCont(proximoId);
    }

    public void zeraBiblioteca() {
        biblioteca.clear();
    }

    public void cadastrarLivro(Livro livro) {
        biblioteca.add(livro);
    }

    public void excluirLivro(Livro livro) {
        if(!livro.isFgEmprestado() && livro.isFgAtivo()) {
            livro.excluiLivro();
        }
    }

    public void inseriuLivroErrado(Livro livro) {
        if(!livro.isFgEmprestado() && livro.isFgAtivo()) {
            biblioteca.remove(livro);
        }
    }

    public void emprestarLivro(Livro livro) {
        livro.emprestar();
    }

    public void devolverLivro(Livro livro) {
        livro.devolver();
    }

    public ArrayList<Livro> retornaBiblioteca() {
        return biblioteca;
    }

    public Optional<Livro> buscaLivroPorId(int id) {
        for (Livro livro : biblioteca) {
            if (id == livro.getId()) {
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    public ArrayList<Livro> buscaLivroPorTitulo(String titulo) {
        ArrayList<Livro> setorTitulo = new ArrayList<Livro>();
        for (Livro livro : biblioteca) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                setorTitulo.add(livro);
            }
        }
        return setorTitulo;
    }

    public ArrayList<Livro> buscaLivroPorAutor(String autor) {
        ArrayList<Livro> setorAutor = new ArrayList<Livro>();
        for (Livro livro : biblioteca) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                setorAutor.add(livro);
            }
        }
        return setorAutor;
    }

    public ArrayList<Livro> listarLivrosDisponiveis() {
        ArrayList<Livro> livrosDisponiveis = new ArrayList<Livro>();
        for (Livro livro : biblioteca) {
            if ((!livro.isFgEmprestado()) && (livro.isFgAtivo())) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }

    public ArrayList<Livro> listarLivrosLocados() {
        ArrayList<Livro> livrosLocados = new ArrayList<Livro>();
        for (Livro livro : biblioteca) {
            if (livro.isFgEmprestado()) {
                livrosLocados.add(livro);
            }
        }
        return livrosLocados;
    }

    public int getSize() {
        return biblioteca.size();
    }
}
