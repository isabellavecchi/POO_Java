package trabalhofinal;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private static int QT_DIAS_EMPRESTIMO = 7;
    private static int MULTAS_REAIS_POR_DIA_DE_ATRASO = 2;

    private int id;
    private Cliente cliente;
    private Livro livro;

    private LocalDate dataLocacao;
    private LocalDate dataADevolver;
    private LocalDate dataDevolucao;
    private boolean fgQuitado;
    
    private int qtDiasAtraso;

    public Emprestimo() {
    }

    public Emprestimo(Cliente usuario, Livro livro) {
        this.cliente = usuario;
        this.livro = livro;
        this.fgQuitado = false;
        this.dataLocacao = LocalDate.now();
        this.dataADevolver = LocalDate.now().plusDays(QT_DIAS_EMPRESTIMO);
        this.qtDiasAtraso = 0;
    }

    public Emprestimo(int id, Cliente cliente, Livro livro, LocalDate dataLocacao, LocalDate dataADevolver, LocalDate dataDevolucao, boolean fgQuitado) {
        this.id = id;
        this.cliente = cliente;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataADevolver = dataADevolver;
        this.dataDevolucao = dataDevolucao;
        if(dataDevolucao != null) {
            if (dataDevolucao.isAfter(dataADevolver)) {
                this.qtDiasAtraso = (int) ChronoUnit.DAYS.between(dataADevolver,dataDevolucao);
            }else {
                this.qtDiasAtraso = 0;
            }
        }
        this.fgQuitado = fgQuitado;
    }

    public static int getQtDiasEmprestimo() {
        return QT_DIAS_EMPRESTIMO;
    }

    public static int getMultasReaisPorDiaDeAtraso() {
        return MULTAS_REAIS_POR_DIA_DE_ATRASO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void emprestou(Livro livro, Cliente usuario) {
        this.livro = livro;
        this.cliente = usuario;
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
            this.cliente.devolveuLivro(this.getQtDiasAtraso());
            this.fgQuitado = false;
            System.out.println("Pague a multa de valor R$ " + this.getQtDiasAtraso() * MULTAS_REAIS_POR_DIA_DE_ATRASO + ",00.");
        } else {
            this.cliente.devolveuLivro(0);
            this.fgQuitado = true;
        }
    }

    public void quitou() {
        this.fgQuitado = true;
    }
    
    public void printEmprestimo() {
        System.out.println("Emprestimo\nID:\t\t" + this.id);
        this.livro.printLivro();
        System.out.println("Emprestado para: " + this.getCliente().getNome());
        System.out.println("No dia " + this.dataLocacao);
        if (this.dataDevolucao != null) {
            System.out.println("Devolvido no dia " + this.dataDevolucao);
            String status = ((this.isFgQuitado())? "esta quitado":"nao esta quitado");
            System.out.println(status);
        } else {
            System.out.println("Par devolver no dia " + this.dataADevolver);
        }
    }
}
