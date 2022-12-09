package tema13;

import org.junit.jupiter.api.Test;
import tema13.estrutura.Contato;
import tema13.estrutura.MensagemOlaCliente;
import tema13.interfaces.IContato;
import tema13.interfaces.IMensagem;

import static org.junit.jupiter.api.Assertions.*;

public class MensagemTeste {
    @Test
    public void testaPadrao() throws Exception {
        IContato contato = new Contato("nome", "email@mail.com");
        IMensagem mensagem = new MensagemOlaCliente(contato);
        assertTrue(mensagem.getEmailRemetente().equals("ola.cliente@ilegra.com"));
        assertTrue(mensagem.getMensagem().equals("Hello, " + contato.getNome() + "!"));
        assertTrue(mensagem.getAssunto().equals("Tema 13"));
    }

    @Test
    public void testaEmailDeRetorno() throws Exception {
        IContato contato1 = new Contato("nome", "email@mail.com");
        IMensagem mensagem1 = new MensagemOlaCliente(contato1);
        System.out.println(mensagem1.getEmailDestinatario());
        assertTrue(mensagem1.getEmailDestinatario().equals(contato1.getEmail()));

        IContato contato2 = new Contato("nome", "+5562993295944", "email@mail.com");
        IMensagem mensagem2 = new MensagemOlaCliente(contato2);
        assertEquals(mensagem2.getEmailDestinatario(), contato2.getTelefone().get() + "@tmomail.net");
    }
}
