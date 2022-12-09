package tema13.estrutura;

import tema13.estrutura.contatoDependencies.Email;
import tema13.estrutura.contatoDependencies.SMS;
import tema13.interfaces.IContato;

import java.util.Optional;

public class Contato implements IContato {
    private String nome;
    private Optional<SMS> telefone;
    private Email email;

    public Contato(String nome, String email) throws Exception {
        this.setNome(nome);
        this.setEmail(email);
    }

    public Contato(String nome, String telefone, String email) throws Exception {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
    }

    public void setNome(String nome) {
        if (nome.isEmpty()) {
            throw new NullPointerException("O nome não pode ser nulo.");
        }
        this.nome = nome;
    }

    public void setTelefone(String telefone) throws Exception {
        this.telefone = Optional.of(new SMS(telefone));
    }

    public void setEmail(String email) throws Exception {
        this.email = new Email(email);
    }

    @Override
    public String getEmail() {
        return this.email.getContato().get();
    }

    @Override
    public boolean temTelefone() {
        if (this.telefone != null) {
            return this.telefone.get().isPresente();
        }
        return false;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public Optional<String> getTelefone() {
        if(this.telefone.isPresent()) {
            return this.telefone.get().getContato();
        }
        return Optional.empty();
    }
}
