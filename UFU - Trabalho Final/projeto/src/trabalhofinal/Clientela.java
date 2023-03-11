package trabalhofinal;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clientela {
    private ClientelaDAO clientelaDAO;

    public Clientela(ClientelaDAO clientelaDAO) {
        this.clientelaDAO = clientelaDAO;
    }

    public boolean validaStrings(String string) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher matcher = pattern.matcher(string);
        return !matcher.find();
    }

    public boolean validaCliente(Optional<Cliente> clienteOptional) {
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            
            boolean fgNome = true;
            if (cliente.getNome() != null) {
                fgNome = validaStrings(cliente.getNome());
            }
            boolean fgEndereco = true;
            if (cliente.getNome() != null) {
                fgNome = validaStrings(cliente.getNome());
            }
            return fgNome && fgEndereco;
        }
        return false;
    }

    public Optional<Integer> addCliente(Cliente cliente) {
        if (validaCliente(Optional.of(cliente))) {
            clientelaDAO.addCliente(cliente);
            return Optional.of(cliente.getId());
        }
        return Optional.empty();
    }

    public void rmCliente(int idCliente) {
        Optional<Cliente> clienteBuscado = buscaClientePorId(idCliente);
        if(clienteBuscado.isPresent()) {
            clientelaDAO.rmContato(idCliente);
        }
    }

    public ArrayList<Cliente> listarClientes () {
        return clientelaDAO.listarClientes();
    }

    public ArrayList<Cliente> listarClientesOrdemAlfabetica () {
        return clientelaDAO.listarClientesOrdemAlfabetica();
    }

    public ArrayList<Cliente> listarClientesOrdemId () {
        return clientelaDAO.listarClientesOrdemId();
    }

    public ArrayList<Cliente> buscaClientePorNome (String nomeContato) {
        return clientelaDAO.buscaClientePorNome(nomeContato);
    }

    public Optional<Cliente> buscaClientePorId (int idContato) {
        return clientelaDAO.buscaClientePorId(idContato);
    }

    public void atualizaClientePorId(int idCliente, String nome, String endereco, int idade) {
        clientelaDAO.atualizaClientePorId(idCliente, nome, endereco, idade);
    }

    public Optional<Integer> getQtdCliente() {
        return clientelaDAO.getQtdCliente();
    }
}
