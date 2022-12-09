package tema13.estrutura.contatoDependencies;

import tema13.interfaces.Strategy;

import java.util.Optional;

public class SMS implements Strategy {
    private String telefone;
    boolean fgPresente;

    public SMS() {
        fgPresente = false;
    }

    public SMS(String telefone) {
        if (!telefone.matches("^[+]{1}[0-9]{12,13}$")) {
            fgPresente = false;
            throw new IllegalArgumentException("Formato de telefone inválido.");
        }
        if(telefone.length() == 13) {
            telefone = telefone.substring(0,5) + "9" + telefone.substring(5);
        }
        this.telefone = telefone;
        fgPresente = true;
    }

    public boolean isPresente() {
        if (this.telefone == null) {
            return false;
        } return true;
    }

    @Override
    public Optional<String> getContato() {
        if (this.telefone.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.telefone);
    }

    public Optional<String> getNumero() {
        if (this.telefone.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.telefone);
    }
}
