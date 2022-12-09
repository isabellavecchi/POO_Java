package tema16;

import java.util.Optional;

public class NotaFiscal {
    private Clientela clientela;
    private int idCliente;
    private double quantia;

    public NotaFiscal(Clientela clientela, int idCliente, double quantia) {
        this.clientela = clientela;
        this.setIdCliente(idCliente);
        this.setQuantia(quantia);
    }

    public void depositar() {
        if(this.getQuantia() < 0) {
            this.setQuantia(-this.getQuantia());
        }
        this.clientela.addSaldoCliente(this.getIdCliente(), this.getQuantia());
    }

    public void sacar() {
        if(this.getQuantia() > 0) {
            this.setQuantia(-this.getQuantia());
        }
        this.clientela.addSaldoCliente(this.getIdCliente(), this.getQuantia());
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }
}
