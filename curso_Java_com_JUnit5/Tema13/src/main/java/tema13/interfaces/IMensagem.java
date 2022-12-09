package tema13.interfaces;

import java.util.Optional;

public interface IMensagem {
    public String getEmailRemetente();
    public void setMensagemPadrao();
    public IContato getiContato();
    public Optional<String> getTelefone();
    public String getEmailDestinatario();
    public String getMensagem();
    public String getAssunto();
}
