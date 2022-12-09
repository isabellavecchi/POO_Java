package tema8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ClientelaTeste {

    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Clientela clientela;
    private ClientelaDAO clientelaDAO;

    @BeforeEach
    public void setUsuario() {
        usuario1 = new Usuario("Isabella");
        usuario2 = new Usuario("Tomaz");
        usuario3 = new Usuario("Paola");

        clientela = new Clientela();
        clientelaDAO = new ClientelaDAO(clientela);
        clientelaDAO.carregarClientela();

        clientelaDAO.addUsuario(usuario1);
        clientelaDAO.addUsuario(usuario2);
        clientelaDAO.addUsuario(usuario3);
    }

    @Test
    public void testaQtTermosDoArrayIgualQtLinhasArquivo() {
        int tamanho = clientela.getSize();
        int nLinhas = clientelaDAO.contaLinhas();
        assertEquals(tamanho,nLinhas);
    }

    @Test
    public void testaAddUsuario() {
        testaQtTermosDoArrayIgualQtLinhasArquivo();
        int tamanho = clientela.getSize();
        Usuario usuario4 = new Usuario("Cleiberson");
        clientelaDAO.addUsuario(usuario4);
        assertEquals(tamanho+1, clientela.getSize());
        testaQtTermosDoArrayIgualQtLinhasArquivo();
    }

    @Test
    public void testaOrdenarTop10() {
        usuario1.pegouLivro();
        usuario1.pegouLivro();
        usuario1.pegouLivro();
        usuario1.pegouLivro();
        usuario2.pegouLivro();
        usuario2.pegouLivro();
        usuario3.pegouLivro();
        usuario3.pegouLivro();
        usuario3.pegouLivro();
        usuario3.pegouLivro();
        usuario3.pegouLivro();
        usuario3.devolveuLivro(0);
        usuario3.pegouLivro();
        usuario3.devolveuLivro(0);
        usuario3.pegouLivro();
        usuario3.devolveuLivro(0);
        usuario3.pegouLivro();

        ArrayList<Usuario> top3 = clientela.topUsuarios();
        for (int i = 0; i < top3.size() - 1; i++) {
            assertTrue(top3.get(i).getQtLivrosLocadosDesdeSempre() >= top3.get(i+1).getQtLivrosLocadosDesdeSempre());
        }
    }
}
