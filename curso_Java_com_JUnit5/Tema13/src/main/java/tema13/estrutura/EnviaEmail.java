package tema13.estrutura;


import tema13.interfaces.IMensagem;

public class EnviaEmail {
    private static String SERVER_MAIL = "servidor@ilegra.com";

    public EnviaEmail() {
    }

    public void enviaEmail(IMensagem iMensagem) {
        String emailRemetente;
        String emailDestinatario;
        if (iMensagem.getEmailRemetente().isEmpty()) {
            emailRemetente = EnviaEmail.getServerMail();
        } else {
            emailRemetente = iMensagem.getEmailRemetente();
        }
        emailRemetente = iMensagem.getEmailDestinatario();

        System.out.println(iMensagem.getAssunto() + ":\n" + iMensagem.getMensagem());
    }

    public static String getServerMail() {
        return SERVER_MAIL;
    }

    public static void setServerMail(String serverMail) {
        SERVER_MAIL = serverMail;
    }
}
