package tema13;

import tema13.estrutura.*;
import tema13.interfaces.*;

public class Main {

    public static void main(String[] args) {

        try {
            IContato iContato = new Contato("Isabella", "+553493362378", "vecchisabella@gmail.com");
            MensagemOlaCliente mensagemOlaCliente = new MensagemOlaCliente(iContato);
            EnviaEmail enviadorDeEmail = new EnviaEmail();
            EnviaNotificacao notificacao = new EnviaNotificacao(enviadorDeEmail, mensagemOlaCliente);

            notificacao.enviaNotificacao();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
