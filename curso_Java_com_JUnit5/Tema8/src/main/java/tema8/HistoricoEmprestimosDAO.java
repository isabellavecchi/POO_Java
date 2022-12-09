package tema8;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class HistoricoEmprestimosDAO {
    private Biblioteca biblioteca;
    private BibliotecaDAO bibliotecaDAO;
    private Clientela clientela;
    private ClientelaDAO clientelaDAO;
    private HistoricoEmprestimos historicoEmprestimos;

    public HistoricoEmprestimosDAO(Biblioteca biblioteca, Clientela clientela, BibliotecaDAO bibliotecaDAO,
                                   ClientelaDAO clientelaDAO, HistoricoEmprestimos historicoEmprestimos) {
        this.biblioteca = biblioteca;
        this.bibliotecaDAO = bibliotecaDAO;
        this.clientela = clientela;
        this.clientelaDAO = clientelaDAO;
        this.historicoEmprestimos = historicoEmprestimos;
    }

    public void arquivarTodos() {
        try (FileWriter fwHistoricoEmprestimos = new FileWriter("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\historicoEmprestimo.txt");
             BufferedWriter escreverArquivo = new BufferedWriter(fwHistoricoEmprestimos)){
            for(Emprestimo emprestimoASerEscrito : historicoEmprestimos.retornaHistoricoEmprestimos()) {

                escreverArquivo.write(emprestimoASerEscrito.getId() + ";;" + emprestimoASerEscrito.getUsuario().geraString());
                escreverArquivo.write(";;" + emprestimoASerEscrito.getLivro().geraString());
                escreverArquivo.write(";;" + emprestimoASerEscrito.getDataLocacao());
                escreverArquivo.write(";;" + emprestimoASerEscrito.getDataADevolver());
                escreverArquivo.write(";;" + emprestimoASerEscrito.getDataDevolucao());
                escreverArquivo.write(";;" + emprestimoASerEscrito.getQtDiasAtraso());
                escreverArquivo.write(";;" + emprestimoASerEscrito.isFgQuitado());
                escreverArquivo.newLine();

            }

            escreverArquivo.close();
            clientelaDAO.arquivarUsuarios();
            bibliotecaDAO.arquivarLivros();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarTodos() {
        try (FileReader frHistoricoEmprestimo = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\historicoEmprestimo.txt");
             BufferedReader br = new BufferedReader(frHistoricoEmprestimo)){
            historicoEmprestimos.zeraHistoricoEmprestimos();

            String entrada = br.readLine();
            while (entrada != null) {

                String[] arrFields = entrada.split(";;");

                int id = Integer.parseInt(arrFields[0]);
                Usuario usuario = Usuario.carregaPorString(arrFields[1]);
                Livro livro = Livro.carregaPorString(arrFields[2]);
                LocalDate dataLocacao = LocalDate.parse(arrFields[3]);
                LocalDate dataADevolver = LocalDate.parse(arrFields[4]);
                LocalDate dataDevolucao;
                if (!arrFields[6].contentEquals("null")) {
                    dataDevolucao = null;
                } else {
                    dataDevolucao = LocalDate.parse(arrFields[5]);
                }
                int qtDiasAtraso = Integer.parseInt(arrFields[6]);
                boolean fgQuitado = Boolean.parseBoolean(arrFields[7]);

                Emprestimo emprestimoBaixando = new Emprestimo(id,usuario,livro,dataLocacao,dataADevolver,dataDevolucao,qtDiasAtraso,fgQuitado);
                historicoEmprestimos.addEmprestimo(emprestimoBaixando);

                entrada = br.readLine();
            }

            historicoEmprestimos.atualizaCont();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bibliotecaDAO.carregarBiblioteca();
        clientelaDAO.carregarClientela();
    }
    public int contaLinhas() {
        int cont = 0;
        try (FileReader frHistoricoEmprestimo = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\historicoEmprestimo.txt");
             BufferedReader br = new BufferedReader(frHistoricoEmprestimo)){
            historicoEmprestimos.zeraHistoricoEmprestimos();

            String entrada = null;
            while ((entrada = br.readLine()) != null) {
                cont++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }

    public void addEmprestimo (Emprestimo emprestimo) {
        historicoEmprestimos.addEmprestimo(emprestimo);
        arquivarTodos();
    }

    public void apagarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.apagarEmprestimo(emprestimo);
        arquivarTodos();
    }

    public Optional<Emprestimo> emprestar(Livro livro, Usuario usuario) {
        Optional<Emprestimo> emprestimo = historicoEmprestimos.emprestar(livro, usuario);
        arquivarTodos();
        return emprestimo;
    }

    public void renovar (Emprestimo emprestimoARenovar) {
        historicoEmprestimos.renovar(emprestimoARenovar);
        arquivarTodos();
    }

    public void devolver(Emprestimo emprestimoDevolvendo) {
        historicoEmprestimos.devolver(emprestimoDevolvendo);
        arquivarTodos();
    }

    public void devolverVariosLivros(Usuario usuarioDevolvendo, ArrayList<Livro> livrosADevolver) {
        historicoEmprestimos.devolverVariosLivros(usuarioDevolvendo, livrosADevolver);
        arquivarTodos();
    }

    public ArrayList<Emprestimo> relatorioLivrosEmprestados() {
        ArrayList<Emprestimo> livrosEmprestados = historicoEmprestimos.relatorioLivrosEmprestados();
        arquivarTodos();
        return livrosEmprestados;
    }

    public ArrayList<Emprestimo> relatorioEmprestimosAtrasados() {
        ArrayList<Emprestimo> historicoEmprestimosAtrasados = historicoEmprestimos.relatorioEmprestimosAtrasados();
        arquivarTodos();
        return historicoEmprestimosAtrasados;
    }
}
