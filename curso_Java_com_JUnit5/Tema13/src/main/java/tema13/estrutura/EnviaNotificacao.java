package tema13.estrutura;

import tema13.interfaces.IMensagem;

public class EnviaNotificacao {
    private EnviaEmail enviadorDeEmail;
    private IMensagem iMensagem;

    public EnviaNotificacao(EnviaEmail enviadorDeEmail, IMensagem iMensagem) {
        this.enviadorDeEmail = enviadorDeEmail;
        this.iMensagem = iMensagem;
    }

    public void enviaNotificacao() {
        if (this.iMensagem.getEmailRemetente().isEmpty()) {
            this.enviadorDeEmail.enviaEmail(iMensagem);
        } else {
            this.enviadorDeEmail.enviaEmail(iMensagem);
        }
    }
}
