package tema10;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgendaContato {
    private AgendaContatoDAO agendaContatoDAO;

    public AgendaContato(AgendaContatoDAO agendaContatoDAO) {
        this.agendaContatoDAO = agendaContatoDAO;
    }

    public boolean validaStrings(String string) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validaContato(Optional<Contato> contatoOptional) {
        if (contatoOptional.isPresent()) {
            Contato contato = contatoOptional.get();

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
            Matcher matcher;
            boolean fgNome = true;
            if (contato.getNome() != null) {
                matcher = pattern.matcher(contato.getNome());
                fgNome = (!matcher.find());
            }
            boolean fgMail = true;
            if (contato.getEmail() != null) {
                pattern = Pattern.compile("[^a-zA-Z0-9 .@_-]");
                matcher = pattern.matcher(contato.getEmail());
                fgMail = (!matcher.find());
            }
            boolean fgTelefone = true;
            if (contato.getTelefone() != null) {
                pattern = Pattern.compile("[^a-zA-Z0-9 ()+-]");
                matcher = pattern.matcher(contato.getTelefone());
                fgTelefone = (!matcher.find());
            }
            return fgNome && fgMail && fgTelefone;
        }
        return false;
    }

    public Optional<Integer> addContato(Contato contato) {
        if (validaContato(Optional.of(contato))) {
            agendaContatoDAO.addContato(contato);
            return Optional.of(contato.getId());
        }
        return Optional.empty();
    }

    public void rmContato(int idContato) {
        Optional<Contato> contatoBuscado = buscaContatoPorId(Optional.of(idContato));
        if(contatoBuscado.isPresent()) {
            agendaContatoDAO.rmContato(idContato);
        }
    }

    public ArrayList<Contato> listarContatos () {
        return agendaContatoDAO.listarContatos();
    }

    public ArrayList<Contato> listarContatosOrdemAlfabetica () {
        return agendaContatoDAO.listarContatosOrdemAlfabetica();
    }

    public ArrayList<Contato> listarContatosOrdemId () {
        return agendaContatoDAO.listarContatosOrdemId();
    }

    public ArrayList<Contato> buscaContatoPorNome (String nomeContato) {
        return agendaContatoDAO.buscaContatoPorNome(nomeContato);
    }

    public Optional<Contato> buscaContatoPorId (Optional<Integer> idContato) {
        if (idContato.isPresent()) {
            Optional<Contato> contato = agendaContatoDAO.buscaContatoPorId(idContato.get());
            if (contato.isPresent()) {
                return contato;
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> getTamanho() {
        Optional<Integer> tamanho = agendaContatoDAO.getTamanho();
        if (tamanho.isPresent()) {
            return tamanho;
        }
        return Optional.empty();
    }
}
