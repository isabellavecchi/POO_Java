package tema13;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import tema13.estrutura.Contato;
import tema13.estrutura.EnviaEmail;
import tema13.estrutura.MensagemOlaCliente;
import tema13.interfaces.IContato;
import tema13.interfaces.IMensagem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnviaEmailTeste {
    IContato contato;
    IMensagem mensagem;
    EnviaEmail mockEnviaEmail;

    @BeforeEach
    public void setUp() throws Exception {
        contato = new Contato("nome", "email@mail.com");
        mensagem = new MensagemOlaCliente(contato);
        mockEnviaEmail = Mockito.mock(EnviaEmail.class);
    }

    @Test
    public void testaEnviaEmail() {
        mockEnviaEmail.enviaEmail(mensagem);
        verify(mockEnviaEmail, times(1)).enviaEmail(mensagem);
    }
}
