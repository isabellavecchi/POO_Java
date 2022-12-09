package tema16.command;

import tema16.NotaFiscal;

public class Depositar implements ITransacao {
    private NotaFiscal notaFiscal;

    public Depositar(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @Override
    public void execute() {
        this.notaFiscal.depositar();
    }

    @Override
    public NotaFiscal getNotaFiscal() {
        return this.notaFiscal;
    }
}
