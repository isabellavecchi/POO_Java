package tema13;

import tema13.estrutura.Contato;
import tema13.estrutura.EnviaEmail;
import tema13.estrutura.EnviaNotificacao;
import tema13.estrutura.MensagemOlaCliente;
import tema13.interfaces.IContato;
import tema13.interfaces.IMensagem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnviaNotificacaoTeste {
    Contato contatoEmail;
    Contato contatoSMS;
    MensagemOlaCliente mensagemEmail;
    MensagemOlaCliente mensagemSMS;
    EnviaEmail mockEnviaEmail;
    EnviaNotificacao notificadorEmail;
    EnviaNotificacao notificadorSMS;

    @BeforeEach
    public void setUp() throws Exception {
        mockEnviaEmail = Mockito.mock(EnviaEmail.class);

        contatoEmail = new Contato("contato email", "email@mail.com");
        mensagemEmail = new MensagemOlaCliente(contatoEmail);
        notificadorEmail = new EnviaNotificacao(mockEnviaEmail, mensagemEmail);

        contatoSMS = new Contato("contato sms", "+5562981169265", "mail@email.com");
        mensagemSMS = new MensagemOlaCliente(contatoSMS);
        notificadorSMS = new EnviaNotificacao(mockEnviaEmail, mensagemSMS);
    }

    @Test
    public void testaEnviaNotificacao() {
        doNothing().when(mockEnviaEmail).enviaEmail(mensagemEmail);
        doNothing().when(mockEnviaEmail).enviaEmail(mensagemSMS);

        notificadorEmail.enviaNotificacao();
        verify(mockEnviaEmail, times(1)).enviaEmail(mensagemEmail);
        assertTrue(mensagemEmail.getEmailDestinatario().equals(contatoEmail.getEmail()));

        notificadorSMS.enviaNotificacao();
        verify(mockEnviaEmail, times(1)).enviaEmail(mensagemSMS);
        assertTrue(mensagemSMS.getEmailDestinatario().equals(contatoSMS.getTelefone().get() + "@tmomail.net"));
    }
}