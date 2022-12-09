package tema16;

public class Cliente {
    private int idCliente;
    private double saldo;
    private double limite;

    public Cliente(int idCliente, double saldo) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.limite = (saldo>0)?0:saldo-1;
    }

    public Cliente(int idCliente, double saldo, double limite) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.limite = (limite <= 0)?limite:-limite;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void addSaldo(double quantia) {
        if (quantia < 0 && this.getSaldo() + quantia < this.getLimite()) {
            throw new IllegalArgumentException("Você não possui saldo suficiente para este saque.");
        }
        this.saldo += quantia;
    }
}
