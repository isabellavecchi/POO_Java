package tema8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UsuarioTeste {
    private Usuario usuario;

    @BeforeEach
    public void setUsuario() {
        usuario = new Usuario("Isabella");
    }

    @Test
    public void testaMax5Livros() {
        for (int i=0; i<5; i++) {
            usuario.pegouLivro();
        }
        assertEquals(usuario.getQtLivrosLocados(), 5);
        usuario.pegouLivro();
        assertEquals(usuario.getQtLivrosLocados(), 5);
    }

    @Test
    public void testaQtLivrosDesdeSempre() {
        for (int i=0; i<5; i++) {
            usuario.pegouLivro();
            usuario.devolveuLivro(0);
        }
        assertEquals(usuario.getQtLivrosLocados(), 0);
        assertEquals(usuario.getQtLivrosLocadosDesdeSempre(), 5);
    }
}
