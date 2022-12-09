package tema16;

import tema16.command.Depositar;
import tema16.command.ITransacao;
import tema16.command.Sacar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class HistoricoTransacao {
    private Clientela clientela;
    private static ArrayList<ITransacao> historicoTransacao = new ArrayList<ITransacao>();

    public HistoricoTransacao(Clientela clientela) {
        this.clientela = clientela;
    }

    public static void addTransacao(ITransacao transacao) {
        HistoricoTransacao.historicoTransacao.add(transacao);
    }

    public void depositar(int idCliente, double quantia) {
        HistoricoTransacao.historicoTransacao.add(new Depositar(new NotaFiscal(this.clientela, idCliente, quantia)));
    }

    public void sacar(int idCliente, double quantia) {
        HistoricoTransacao.historicoTransacao.add(new Sacar(new NotaFiscal(this.clientela, idCliente, quantia)));
    }

    public static void rmTransacao(ITransacao transacao) {
        HistoricoTransacao.historicoTransacao.remove(transacao);
    }

    public static ArrayList<ITransacao> retornaHistorico() {
        return HistoricoTransacao.historicoTransacao;
    }

    public static void atualizaSaldoCliente(int idCliente) {
        Iterator<ITransacao> iterator = HistoricoTransacao.retornaHistorico().iterator();
        while(iterator.hasNext()) {
            ITransacao transacao = iterator.next();
            if(transacao.getNotaFiscal().getIdCliente() == idCliente) {
                transacao.execute();
                iterator.remove();
            }
        }
    }

    public ArrayList<ITransacao> atualizaSaldoClientes() {
        int tamanho = HistoricoTransacao.historicoTransacao.size();
        for (ITransacao transacao : HistoricoTransacao.retornaHistorico()) {
            transacao.execute();
        }
        HistoricoTransacao.historicoTransacao.clear();
        return HistoricoTransacao.retornaHistorico();
    }
}
