package tema16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tema16.command.ITransacao;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AplicacaoTest {
    Clientela clientela;
    HistoricoTransacao historicoTransacao;
    Aplicacao aplicacao;
    Cliente limiteMenos100;
    Cliente cliente1;

    @BeforeEach
    public void setUp() {
        clientela = new Clientela();
        historicoTransacao = new HistoricoTransacao(clientela);

        aplicacao = new Aplicacao(clientela, historicoTransacao);

        limiteMenos100 = new Cliente(1, 0, -100);
        cliente1 = new Cliente(2, 30);

        clientela.addCliente(limiteMenos100);
        clientela.addCliente(cliente1);
    }

    @Test
    public void testaSacou() {
        double esperado = cliente1.getSaldo() - 10;
        aplicacao.sacar(cliente1.getIdCliente(), 10);
        Optional<Double> real = aplicacao.consultaSaldoCliente(cliente1.getIdCliente());
        assertTrue(real.isPresent());
        assertEquals(esperado,real.get());
    }

    @Test
    public void testaDepositou() {
        double esperado = cliente1.getSaldo() + 10;
        aplicacao.depositar(cliente1.getIdCliente(), 10);
        Optional<Double> real = aplicacao.consultaSaldoCliente(cliente1.getIdCliente());
        assertTrue(real.isPresent());
        assertEquals(esperado,real.get());
    }

    @Test
    public void testaSaqueSemLimite() {
        double saqueImpossivel = limiteMenos100.getSaldo() + (-limiteMenos100.getLimite()) + 0.01;
        aplicacao.sacar(limiteMenos100.getIdCliente(), saqueImpossivel);
        assertThrows(IllegalArgumentException.class, () -> aplicacao.consultaSaldoCliente(limiteMenos100.getIdCliente()));
    }

    @Test
    public void testaConsultaDeSaldo() {
        Cliente cliente = new Cliente(3, 0);
        clientela.addCliente(cliente);

        aplicacao.depositar(3, 10);
        aplicacao.depositar(3, 10);
        aplicacao.depositar(3, 10);
        aplicacao.depositar(3, 10);
        assertEquals(40, aplicacao.consultaSaldoCliente(3).get());
        assertEquals(40, cliente.getSaldo());

        aplicacao.sacar(3, 10);
        assertEquals(40, cliente.getSaldo());
        assertEquals(30, aplicacao.consultaSaldoCliente(3).get());
        assertEquals(30, cliente.getSaldo());
    }
}
