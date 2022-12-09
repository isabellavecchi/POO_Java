package tema16.command;

import tema16.NotaFiscal;

public interface ITransacao {
    public void execute();
    public NotaFiscal getNotaFiscal();
}
