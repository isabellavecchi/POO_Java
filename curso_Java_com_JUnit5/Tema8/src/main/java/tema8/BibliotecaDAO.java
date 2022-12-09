package tema8;

import java.io.*;
import java.util.ArrayList;

public class BibliotecaDAO {
    private static Biblioteca biblioteca;

    public BibliotecaDAO(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void arquivarLivros() {
        try (FileWriter fwLivros = new FileWriter("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\biblioteca.txt");
             BufferedWriter escreverArquivo = new BufferedWriter(fwLivros)){

            for(Livro livroASerEscrito : biblioteca.retornaBiblioteca()) {
                escreverArquivo.write(livroASerEscrito.geraString());
                escreverArquivo.newLine();
            }

            escreverArquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarBiblioteca() {
        try (FileReader frBiblioteca = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\biblioteca.txt");
             BufferedReader br = new BufferedReader(frBiblioteca)){
            biblioteca.zeraBiblioteca();

            String entrada = null;
            while ((entrada = br.readLine()) != null) {
                Livro livroBaixando = Livro.carregaPorString(entrada);
                biblioteca.cadastrarLivro(livroBaixando);
            }

            br.close();
            biblioteca.atualizaCont();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int contaLinhas() {
        int cont = 0;
        try (FileReader frBiblioteca = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\biblioteca.txt");
            BufferedReader br = new BufferedReader(frBiblioteca)){


            String entrada = null;
            while ((entrada = br.readLine()) != null) {
                cont++;
            }
            br.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }

    public void cadastrarLivro(Livro livro) {
        biblioteca.cadastrarLivro(livro);
        arquivarLivros();
    }

    public void excluirLivro(Livro livro) {
        biblioteca.excluirLivro(livro);
        arquivarLivros();
    }

    public void inseriuLivroErrado(Livro livro) {
        biblioteca.inseriuLivroErrado(livro);
        arquivarLivros();
    }

    public void emprestarLivro(Livro livro) {
        livro.emprestar();
        arquivarLivros();
    }

    public void devolverLivro(Livro livro) {
        biblioteca.devolverLivro(livro);
        arquivarLivros();
    }

    public ArrayList<Livro> retornaBiblioteca() {
        carregarBiblioteca();
        return biblioteca.retornaBiblioteca();
    }

    public ArrayList<Livro> buscaLivroPorTitulo(String titulo) {
        carregarBiblioteca();
        return biblioteca.buscaLivroPorTitulo(titulo);
    }

    public ArrayList<Livro> buscaLivroPorAutor(String autor) {
        carregarBiblioteca();
        return biblioteca.buscaLivroPorAutor(autor);
    }


}
