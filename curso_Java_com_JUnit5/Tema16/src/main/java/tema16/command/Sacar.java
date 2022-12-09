package tema16.command;

import tema16.NotaFiscal;

public class  Sacar implements ITransacao {
    private NotaFiscal notaFiscal;

    public Sacar(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @Override
    public void execute() {
        this.getNotaFiscal().sacar();
    }

    @Override
    public NotaFiscal getNotaFiscal() {
        return this.notaFiscal;
    }
}
