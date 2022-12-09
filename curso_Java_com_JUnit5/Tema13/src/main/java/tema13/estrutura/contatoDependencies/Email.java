package tema13.estrutura.contatoDependencies;

import tema13.interfaces.Strategy;

import java.util.Optional;

public class Email implements Strategy {
    private String email;

    public Email(String email) {
        if(email.isEmpty()) {
            throw new NullPointerException("Email não pode ser nulo.");
        }

        if (!email.matches("^[a-zA-Z0-9_-]+[@]{1}[a-zA-Z0-9_-]+[.]{1}[a-zA-Z]+")) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
        this.email = email;
    }

    @Override
    public Optional<String> getContato() {
        return Optional.ofNullable(this.email);
    }
}
