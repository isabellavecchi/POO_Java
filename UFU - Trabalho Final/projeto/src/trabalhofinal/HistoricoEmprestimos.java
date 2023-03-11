package trabalhofinal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class HistoricoEmprestimos {
    private HistoricoEmprestimosDAO historicoEmprestimosDAO;
    private Clientela clientela;
    private Biblioteca biblioteca;

    public HistoricoEmprestimos(HistoricoEmprestimosDAO historicoEmprestimosDAO, Clientela clientela, Biblioteca biblioteca) {
        this.historicoEmprestimosDAO = historicoEmprestimosDAO;
        this.clientela = clientela;
        this.biblioteca = biblioteca;
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimosDAO.addEmprestimo(emprestimo);
        biblioteca.emprestarLivro(emprestimo.getLivro().getId());
    }

    public void addEmprestimo(int idCliente, int idLivro) {
        Optional<Livro> livro = biblioteca.buscaLivroPorId(idLivro);
        if(livro.isPresent()) {
            historicoEmprestimosDAO.addEmprestimo(idCliente, idLivro);
            biblioteca.emprestarLivro(idLivro);
        }
    }

    public void apagarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimosDAO.rmEmprestimo(emprestimo.getId());
    }

    public void apagarEmprestimoPorID(int id) {
        historicoEmprestimosDAO.rmEmprestimo(id);
    }

    public Optional<Integer> getQtdCliente() {
        return historicoEmprestimosDAO.getQtdCliente();
    }

    /*public void atualizaHistoricoEUsuario() {
        for (Emprestimo emprestimo : historicoEmprestimos) {
            int intervaloDias;
            if (emprestimo.getDataDevolucao() != null ) {
                if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataADevolver())) {
                    intervaloDias = Period.between(emprestimo.getDataADevolver(), emprestimo.getDataDevolucao()).getDays();
                    emprestimo.getCliente().setFgEmDia(false);
                } else {
                    intervaloDias = 0;
                }
            }else if (emprestimo.getDataDevolucao() == null && LocalDate.now().isAfter(emprestimo.getDataADevolver())) {
                intervaloDias = Period.between(emprestimo.getDataADevolver(), LocalDate.now()).getDays();
                emprestimo.getCliente().setFgEmDia(false);
            } else {
                intervaloDias = 0;
            }
            emprestimo.setQtDiasAtraso(intervaloDias);
        }
    }*/

    public void emprestar(Cliente cliente, ArrayList<Livro> livros) {
        //atualizaHistoricoEUsuario(cliente);
        if(cliente.isFgEmDia()) {
            for (Livro livro : livros) {
                if (!livro.isFgEmprestado()) {
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.emprestou(livro, cliente);
                } else {
                    livros.remove(livro);
                }
            }
            historicoEmprestimosDAO.emprestar(cliente, livros);
            biblioteca.emprestarLivros(livros);
        }
    }

    public Optional<Emprestimo> buscaEmprestimoPorId(int idEmprestimo) {
        return historicoEmprestimosDAO.buscaEmprestimoPorId(idEmprestimo);
    }

    public Optional<Emprestimo> buscaEmprestimoPorIdLivro (int idLivro) {
        return historicoEmprestimosDAO.buscaEmprestimoPorIdLivro(idLivro);
    }

    public ArrayList<Emprestimo> buscaEmprestimoPorCliente (Cliente cliente) {
        return historicoEmprestimosDAO.buscaEmprestimoPorCliente(cliente);
    }

    public ArrayList<Emprestimo> buscaEmprestimoPorLivro (String titulo) {
        return historicoEmprestimosDAO.buscaEmprestimoPorLivro(titulo);
    }

    public void devolver(ArrayList<Livro> livros) {
        String idLivros = "0";
        for(Livro livro : livros) {
            Optional<Emprestimo> oEmprestimo = buscaEmprestimoPorIdLivro(livro.getId());
            Emprestimo emprestimo = ((oEmprestimo.isPresent())? oEmprestimo.get():null);
            if (emprestimo.getDataDevolucao() == null) {
                idLivros += ", " + livro.getId();
                emprestimo.devolveu();
            } else {
                throw new IllegalArgumentException("O livro " + livro.getTitulo() + " já foi devolvido pelo usuário " + emprestimo.getCliente().getNome() + ".");
            }
        }
        historicoEmprestimosDAO.devolver(idLivros);
    }
}
