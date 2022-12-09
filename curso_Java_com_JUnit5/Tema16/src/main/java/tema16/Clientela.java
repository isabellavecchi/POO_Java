package tema16;

import java.util.ArrayList;
import java.util.Optional;

public class Clientela {
    private ArrayList<Cliente> clientela = new ArrayList<Cliente>();

    public void addCliente(Cliente cliente) {
        clientela.add(cliente);
    }

    public void rmCliente(Cliente cliente) {
        clientela.remove(cliente);
    }

    public ArrayList<Cliente> retornaClientela() {
        return this.clientela;
    }

    public Optional<Cliente> encontraClientePorId(int idCliente) {
        for (Cliente cliente : this.clientela) {
            if (cliente.getIdCliente() == idCliente) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public void addSaldoCliente(Cliente cliente, double quantia) {
        if (!this.clientela.contains(cliente)) {
            clientela.add(cliente);
        }
        for (Cliente iteradorCliente : this.clientela) {
            if (iteradorCliente.equals(cliente)) {
                iteradorCliente.addSaldo(quantia);
            }
        }
    }

    public void addSaldoCliente(int idCliente, double quantia) {
        boolean clienteEncontrado = false;
        for (Cliente iteradorCliente : this.clientela) {
            if (iteradorCliente.getIdCliente() == idCliente) {
                iteradorCliente.addSaldo(quantia);
                clienteEncontrado = true;
            }
        }
        if(!clienteEncontrado) {
            Cliente clienteAAdicionar = new Cliente(idCliente,quantia);

            clientela.add(clienteAAdicionar);
        }
    }
}
