package tema13.estrutura;

import tema13.interfaces.IContato;
import tema13.interfaces.IMensagem;

import java.util.Optional;

public class MensagemOlaCliente implements IMensagem {
    private String emailRemetente;
    private IContato iContato;
    private String assunto;
    private String mensagem;

    public MensagemOlaCliente(IContato iContato) {
        this.setiContato(iContato);
        this.setEmailRemetente("ola.cliente@ilegra.com");
        this.setAssunto("Tema 13");
        this.setMensagem("Hello, " + iContato.getNome() + "!");
    }
    public String getEmailRemetente() {
        return this.emailRemetente;
    }
    private void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    @Override
    public void setMensagemPadrao() {
        this.setAssunto("Tema 13");
        this.setMensagem("Hello, " + iContato.getNome() + "!");
    }

    @Override
    public IContato getiContato() {
        return iContato;
    }

    @Override
    public Optional<String> getTelefone() {
        return this.iContato.getTelefone();
    }

    @Override
    public String getEmailDestinatario() {
        if(this.iContato.temTelefone()) {
            return this.iContato.getTelefone().get() + "@tmomail.net";
        }
        return this.iContato.getEmail();
    }

    public void setiContato(IContato iContato) {
        this.iContato = iContato;
    }

    @Override
    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getAssunto() {
        return this.assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
