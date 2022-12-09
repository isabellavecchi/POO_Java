package tema16;

import tema16.command.Depositar;
import tema16.command.ITransacao;

import java.util.ArrayList;
import java.util.Optional;

public class Aplicacao {
    private HistoricoTransacao historicoTransacao;
    private Clientela clientela;

    public Aplicacao(Clientela clientela, HistoricoTransacao historicoTransacao) {
        this.clientela = clientela;
        this.historicoTransacao = historicoTransacao;
    }

    public void depositar(int idCliente, double quantia) {
        historicoTransacao.depositar(idCliente, quantia);
    }

    public void sacar(int idCliente, double quantia) {
        historicoTransacao.sacar(idCliente, quantia);
    }

    public Optional<Double> consultaSaldoCliente(int idCliente) {
        HistoricoTransacao.atualizaSaldoCliente(idCliente);
        Optional<Cliente> clienteConsultado = this.clientela.encontraClientePorId(idCliente);
        if(clienteConsultado.isPresent()) {
            System.out.printf("Id Cliente: %d \nSaldo = %2.2f\n", clienteConsultado.get().getIdCliente(), clienteConsultado.get().getSaldo());
            return Optional.of(clienteConsultado.get().getSaldo());
        }
        System.out.println("Cliente não encontrado");
        return Optional.empty();
    }

    public ArrayList<Cliente> consultaSaldoXCliente() {
        if(historicoTransacao.atualizaSaldoClientes().isEmpty()) {
            System.out.println("Cliente X Saldo");
            for (Cliente cliente : this.clientela.retornaClientela()) {
                System.out.printf("Id Cliente: %d \tSaldo = %2.2f\n", cliente.getIdCliente(), cliente.getSaldo());
            }
        }
        return this.clientela.retornaClientela();
    }
}
