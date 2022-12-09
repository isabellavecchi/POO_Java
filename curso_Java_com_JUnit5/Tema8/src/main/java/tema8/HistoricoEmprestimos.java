package tema8;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class HistoricoEmprestimos {
    private ArrayList<Emprestimo> historicoEmprestimos;
    private Biblioteca biblioteca;
    private Clientela clientela;

    public HistoricoEmprestimos(Biblioteca biblioteca, Clientela clientela) {
        historicoEmprestimos = new ArrayList<Emprestimo>();
        this.biblioteca = biblioteca;
        this.clientela = clientela;
    }

    public ArrayList<Emprestimo> retornaHistoricoEmprestimos() {
        return historicoEmprestimos;
    }

    public void zeraHistoricoEmprestimos() {
        historicoEmprestimos.clear();
    }

    public void atualizaCont() {
        int proximoId=0;
        for (Emprestimo emprestimo : historicoEmprestimos) {
            if (emprestimo.getId() > proximoId) {
                proximoId = emprestimo.getId();
            }
        }
        Emprestimo.setCont(proximoId);
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.add(emprestimo);
    }

    public void apagarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.remove(emprestimo);
    }

    public int getSize() {
        return historicoEmprestimos.size();
    }

    public void atualizaHistoricoEUsuario(Usuario usuarioTestado) {
        for (Emprestimo emprestimo : historicoEmprestimos) {
            if (emprestimo.getUsuario() == usuarioTestado) {
                int intervaloDias;
                if (emprestimo.getDataDevolucao() != null ) {
                    if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataADevolver())) {
                        intervaloDias = Period.between(emprestimo.getDataADevolver(), emprestimo.getDataDevolucao()).getDays();
                        usuarioTestado.setFgEmDia(false);
                    } else {
                        intervaloDias = 0;
                    }
                }else if (emprestimo.getDataDevolucao() == null && LocalDate.now().isAfter(emprestimo.getDataADevolver())) {
                    intervaloDias = Period.between(emprestimo.getDataADevolver(), LocalDate.now()).getDays();
                    usuarioTestado.setFgEmDia(false);
                } else {
                    intervaloDias = 0;
                }
                emprestimo.setQtDiasAtraso(intervaloDias);
            }
        }
    }

    public void atualizaHistoricoEUsuario() {
        for (Emprestimo emprestimo : historicoEmprestimos) {
            int intervaloDias;
            if (emprestimo.getDataDevolucao() != null ) {
                if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataADevolver())) {
                    intervaloDias = Period.between(emprestimo.getDataADevolver(), emprestimo.getDataDevolucao()).getDays();
                    emprestimo.getUsuario().setFgEmDia(false);
                } else {
                    intervaloDias = 0;
                }
            }else if (emprestimo.getDataDevolucao() == null && LocalDate.now().isAfter(emprestimo.getDataADevolver())) {
                intervaloDias = Period.between(emprestimo.getDataADevolver(), LocalDate.now()).getDays();
                emprestimo.getUsuario().setFgEmDia(false);
            } else {
                intervaloDias = 0;
            }
            emprestimo.setQtDiasAtraso(intervaloDias);
        }
    }

    public Optional<Emprestimo> emprestar(Livro livro, Usuario usuario) {
        atualizaHistoricoEUsuario(usuario);
        if(usuario.isFgEmDia() && !livro.isFgEmprestado() && !usuario.estaNoLimiteDeLivros()) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.emprestou(livro, usuario);
            biblioteca.emprestarLivro(livro);
            historicoEmprestimos.add(emprestimo);
            return Optional.of(emprestimo);
        }
        return Optional.empty();
    }

    public Optional<Emprestimo> buscaEmprestimoPorUsuarioELivro(Livro livro, Usuario usuario) {
        ArrayList<Emprestimo> historicoRecente = historicoEmprestimos;
        Collections.reverse(historicoRecente);
        for (Emprestimo emprestimoMaisRecente : historicoRecente) {
            if (livro.equals(emprestimoMaisRecente.getLivro()) && usuario.equals(emprestimoMaisRecente.getUsuario())) {
                return Optional.of(emprestimoMaisRecente);
            }
        }
        return Optional.empty();
    }

    public void renovar (Emprestimo emprestimoARenovar) {
        Usuario usuarioRenovando = emprestimoARenovar.getUsuario();
        atualizaHistoricoEUsuario(usuarioRenovando);
        if (!usuarioRenovando.isFgEmDia()) {
            throw new IllegalArgumentException("Você não está em dia com a biblioteca. Esta ação não será possível.");
        } else {
            emprestimoARenovar.renovacao();
        }
    }

    public void devolver(Emprestimo emprestimoDevolvendo) {
        if (emprestimoDevolvendo.getDataDevolucao() == null) {
            emprestimoDevolvendo.devolveu();
        } else {
            throw new IllegalArgumentException("O livro " + emprestimoDevolvendo.getLivro().getTitulo() + " já foi devolvido pelo usuário " + emprestimoDevolvendo.getUsuario().getNome() + ".");
        }
    }

    public void devolverVariosLivros(Usuario usuarioDevolvendo, ArrayList<Livro> livrosADevolver) {
        for (Livro livroEmprestado : livrosADevolver) {
            Optional<Emprestimo> emprestimoDevolvendo = buscaEmprestimoPorUsuarioELivro(livroEmprestado, usuarioDevolvendo);
            if (emprestimoDevolvendo.isPresent()) {
                devolver(emprestimoDevolvendo.get());
            }
        }
    }

    public ArrayList<Emprestimo> relatorioLivrosEmprestados() {
        atualizaHistoricoEUsuario();
        ArrayList<Emprestimo> livrosEmprestados = new ArrayList<Emprestimo>();
        for (Emprestimo emprestimo : historicoEmprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                livrosEmprestados.add(emprestimo);
            }
        }
        return livrosEmprestados;
    }

    public ArrayList<Emprestimo> relatorioEmprestimosAtrasados() {
        ArrayList<Emprestimo> historicoEmprestimosAtrasados = new ArrayList<Emprestimo>();
        atualizaHistoricoEUsuario();
        for (Emprestimo emprestimo : historicoEmprestimos) {
            if (!emprestimo.isFgQuitado() && emprestimo.getQtDiasAtraso() != 0) {
                historicoEmprestimosAtrasados.add(emprestimo);
            }
        }
        return historicoEmprestimosAtrasados;
    }
}
