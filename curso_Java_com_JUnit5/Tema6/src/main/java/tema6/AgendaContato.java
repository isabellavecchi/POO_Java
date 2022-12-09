package tema6;

import java.util.ArrayList;
import java.util.Optional;

public class AgendaContato {
    private ArrayList<Contato> agenda = new ArrayList<Contato>();

    public void addContato(Contato contato) {
        agenda.add(contato);
    }

    public ArrayList<Contato> listarContatos () {
        for (Contato contato : agenda) {
            contato.printContato();
        }
        return agenda;
    }

    public AgendaContato buscaNome (String nome) {
        AgendaContato agendaBuscada = new AgendaContato();
        for(Contato nomeBuscado : agenda) {
            if (nomeBuscado.getNome().equals(nome)) {
                nomeBuscado.printContato();
                agendaBuscada.addContato(nomeBuscado);
            }
        }
        return agendaBuscada;
    }

    public Optional<Contato> buscaId (int id) {
        for(Contato contato : agenda) {
            if (contato.getId() == id) {
                return Optional.of(contato);
            }
        }
        return Optional.empty();
    }

    public void rmContato(Contato contato) {
        agenda.remove(contato);
    }

    public void rmContato(int id) {
        for(Contato contatoARemover : agenda) {
            if (contatoARemover.getId() == id) {
                System.out.println("Contato removido:");
                contatoARemover.printContato();
                agenda.remove(contatoARemover);
            }
        }
    }

    public Contato retornaIesimo(int i) {
        return agenda.get(i);
    }

    public int getTamanho() {
        return agenda.size();
    }
}
