package tema13;

import org.junit.jupiter.api.Test;
import tema13.estrutura.Contato;

import static org.junit.jupiter.api.Assertions.*;

public class ContatoTeste {
    @Test
    public void testaAddContatoSemEmail() {
        assertThrows(NullPointerException.class, () -> new Contato("nome","+5534993362378", null));
    }

    @Test
    public void testaEmailDesformatado() {
        assertThrows(IllegalArgumentException.class, () -> new Contato("nome","+5534993362378", "emailsemarroba.com"));
        assertThrows(IllegalArgumentException.class, () -> new Contato("nome", "email com espaço@oi.com"));
        assertThrows(IllegalArgumentException.class, () -> new Contato("nome", "emailsemmail@.com"));
    }

    @Test
    public void testaNumeroDesformatado() {
        assertThrows(IllegalArgumentException.class, () -> new Contato("nome","34993362378", "email@mail.com"));
    }

    @Test
    public void testaNumeroOitoDigitos() throws Exception {
        Contato contato = new Contato("nome", "+553485847972", "email@mail.com");
        assertTrue(contato.getTelefone().isPresent());
        assertTrue(contato.getTelefone().get().equals("+5534985847972"));
    }
}
