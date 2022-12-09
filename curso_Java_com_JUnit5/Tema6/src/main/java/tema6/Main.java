package tema6;

import java.util.Scanner;

public class Main {
    private static int op=1;

    public static void main(String[] args) {

        //Colocando dados prévios na agenda
        Contato ct1 = new Contato("Isabella Vecchi Ferreira", "(34)993362378");
        Contato ct2 = new Contato("Letícia Medeiros", "ilelegra@mail.com");
        Contato ct3 = new Contato("Tomaz Lago", "tommy@mail.com");

        AgendaContato agenda = new AgendaContato();
        agenda.addContato(ct1);
        agenda.addContato(ct2);
        agenda.addContato(ct3);

        Scanner teclado = new Scanner(System.in);

        while(op > 0 && op < 6) {

            System.out.println("----- AGENDA DE CONTATOS -----");
            System.out.println("------  Seja Bem Vinde  ------");
            System.out.println("Digite:\n\t1) para adicionar contato");
            System.out.println("\t2) para remover contato\n\t3) para listar contatos\n\t4) para buscar contato por nome");
            System.out.println("\t5) para buscar contato por id\n\n\tDigite outro número qualquer para sair.");

            op = teclado.nextInt();
            int id;
            String nome;
            String contato;

            teclado.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Digite os dados do contato a ser adicionado:");
                    System.out.println("Nome: ");
                    nome = teclado.nextLine();
                    System.out.println("Contato: ");
                    contato = teclado.nextLine();

                    Contato add = new Contato(nome, contato);
                    agenda.addContato(add);
                    break;

                case 2:
                    System.out.println("Digite o ID do contato a ser removide:");
                    id = teclado.nextInt();
                    teclado.nextLine();
                    agenda.rmContato(id);
                    break;

                case 3:
                    agenda.listarContatos();
                    break;

                case 4:
                    System.out.println("Digite o nome do contato a ser buscado:");
                    nome = teclado.nextLine();
                    agenda.buscaNome(nome);
                    break;

                case 5:
                    System.out.println("Digite o ID do contato a ser buscado:");
                    id = teclado.nextInt();
                    teclado.nextLine();
                    agenda.buscaId(id);
                    break;
            }
            System.out.println("Pressione [ENTER] para continuar");
            teclado.nextLine();
        }
    }
}
