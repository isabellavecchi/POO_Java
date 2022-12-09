package tema10;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static int op=1;

    public static void main(String[] args) {
        ConectaBD conectaBD = new ConectaBD();

        Contato ct1 = new Contato("Leticia Medeiros", "ilelegra@mail.com", false);
        Contato ct2 = new Contato("Isabella Vecchi Ferreira", "(34)993362378", true);
        Contato ct3 = new Contato("Tomaz Lago", "(53)998876655", "tommy@mail.com");


        AgendaContato agenda = new AgendaContato(new AgendaContatoDAO(conectaBD));

        agenda.addContato(ct1);
        agenda.addContato(ct2);
        agenda.addContato(ct3);

        agenda.rmContato(ct1.getId());

        System.out.println("\n\nBusca por nome\n");
        ArrayList<Contato> listaContatos = agenda.buscaContatoPorNome("isa");
        for (Contato contato : listaContatos) {
            contato.printContato();
            System.out.println();
        }

        System.out.println("\n\nOrdem Alfabética\n");
        listaContatos = agenda.listarContatosOrdemAlfabetica();
        for (Contato contato : listaContatos) {
            contato.printContato();
            System.out.println();
        }

        Optional<Contato> contatoBuscado = agenda.buscaContatoPorId(Optional.of(2));
    }
}
