package tema13.interfaces;

import java.util.Optional;

public interface IContato {
    public String getNome();

    public Optional<String> getTelefone();

    public String getEmail();

    public boolean temTelefone();
}
