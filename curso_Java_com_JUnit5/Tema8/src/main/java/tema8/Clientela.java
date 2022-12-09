package tema8;

import java.io.*;
import java.util.*;

public class Clientela implements Comparator<Usuario> {
    private ArrayList<Usuario> clientela;

    public Clientela() {
        clientela = new ArrayList<Usuario>();
    }

    public ArrayList<Usuario> retornaClientela() {
        return clientela;
    }

    public void zeraClientela() {
        clientela.clear();
    }

    public int getSize() {
        return clientela.size();
    }

    public void atualizaCont() {
        int proximoId=0;
        for (Usuario usuario : clientela) {
            if (usuario.getId() > proximoId) {
                proximoId = usuario.getId();
            }
        }
        Usuario.setCont(proximoId);
    }

    public void addUsuario(Usuario usuario) {
        clientela.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        clientela.remove(usuario);
    }

    public Optional<Usuario> buscaUsuarioPorID(int id) {
        for (Usuario usuario : clientela) {
            if ( usuario.getId() == id ) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public void pagouQtSaldo(Usuario usuario, int saldoDevedor) {
        usuario.pagouQtSaldo(saldoDevedor);
        if (usuario.getSaldoDevedor() <= 0) {
            usuario.quitouAtraso();
        }
    }

    @Override
    public int compare(Usuario o1, Usuario o2) {
        return o1.compareTo(o2);
    }

    public ArrayList<Usuario> topUsuarios() {
        ArrayList<Usuario> usuariosDescQtLocacoes = clientela;
        Collections.sort(usuariosDescQtLocacoes);
        return usuariosDescQtLocacoes;
    }
}
