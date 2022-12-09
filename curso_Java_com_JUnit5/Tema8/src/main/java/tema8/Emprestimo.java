package tema8;

import java.time.LocalDate;
import java.time.Period;

public class Emprestimo {
    private static int QT_DIAS_EMPRESTIMO = 7;
    private static int MULTAS_REAIS_POR_DIA_DE_ATRASO = 5;

    private int id;
    private Usuario usuario;
    private Livro livro;

    private LocalDate dataLocacao;
    private LocalDate dataADevolver;
    private LocalDate dataDevolucao;
    private int qtDiasAtraso;
    private boolean fgQuitado;

    private static int cont=0;

    public Emprestimo() {
        this.id = ++cont;
        qtDiasAtraso = 0;
    }

    public Emprestimo(int id, Usuario usuario, Livro livro, LocalDate dataLocacao, LocalDate dataADevolver, LocalDate dataDevolucao, int qtDiasAtraso, boolean fgQuitado) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataADevolver = dataADevolver;
        this.dataDevolucao = dataDevolucao;
        this.qtDiasAtraso = qtDiasAtraso;
        this.fgQuitado = fgQuitado;
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataLocacao) {
        this.id = ++cont;
        this.usuario = usuario;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataADevolver = dataLocacao.plusDays(7);
    }

    public static void setCont(int cont) {
        Emprestimo.cont = cont;
    }

    public static int getQtDiasEmprestimo() {
        return QT_DIAS_EMPRESTIMO;
    }

    public static int getMultasReaisPorDiaDeAtraso() {
        return MULTAS_REAIS_POR_DIA_DE_ATRASO;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataADevolver() {
        return dataADevolver;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public int getQtDiasAtraso() {
        return this.qtDiasAtraso;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
        this.dataADevolver = dataLocacao.plusDays(7);
    }

    public void setDataADevolver(LocalDate dataADevolver) {
        this.dataADevolver = dataADevolver;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isFgQuitado() {
        return fgQuitado;
    }

    public void setQtDiasAtraso (int qtDiasAtraso) {
        this.qtDiasAtraso = qtDiasAtraso;
        this.fgQuitado = false;
    }

    public void emprestou(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.usuario.pegouLivro();
        this.setDataLocacao(LocalDate.now());
        this.setDataADevolver(LocalDate.now().plusDays(7));
    }

    public void renovacao() {
        this.dataADevolver = LocalDate.now().plusDays(7);
    }

    public void devolveu() {
        this.livro.devolver();
        this.setDataDevolucao(LocalDate.now());
        if (this.getDataDevolucao().isAfter(this.getDataADevolver())) {
            Period intervaloDias = Period.between(this.getDataADevolver(), this.getDataDevolucao());
            this.setQtDiasAtraso(intervaloDias.getDays());
            this.usuario.devolveuLivro(this.getQtDiasAtraso());
            this.fgQuitado = false;
            System.out.println("Pague a multa de valor R$ " + this.getQtDiasAtraso() * MULTAS_REAIS_POR_DIA_DE_ATRASO + ",00.");
            System.out.println("Saldo devetor total = R$ " + this.getUsuario().getSaldoDevedor() + ",00.");
        } else {
            this.usuario.devolveuLivro(0);
            this.fgQuitado = true;
        }
    }

    public void quitou() {
        this.fgQuitado = true;
        this.usuario.pagouQtSaldo(this.getQtDiasAtraso()* getMultasReaisPorDiaDeAtraso());
    }
}
