package tema8;

import java.io.*;
import java.util.ArrayList;

public class ClientelaDAO {
    private Clientela clientela;

    public ClientelaDAO(Clientela clientela) {
        this.clientela = clientela;
    }

    public void arquivarUsuarios() {
        try (FileWriter arquivoClientela = new FileWriter("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\clientela.txt");
             BufferedWriter escreverArquivo = new BufferedWriter(arquivoClientela)){

            for(Usuario usuarioASerEscrito : clientela.retornaClientela()) {

                escreverArquivo.write(usuarioASerEscrito.geraString());
                escreverArquivo.newLine();

            }
            escreverArquivo.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("deu erro arquivando");
        }
    }

    public void carregarClientela() {
        try (FileReader frClientela = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\clientela.txt");
             BufferedReader br = new BufferedReader(frClientela)){

            clientela.zeraClientela();

            String entrada = br.readLine();
            while (entrada != null) {
                Usuario usuarioBaixando = Usuario.carregaPorString(entrada);
                clientela.addUsuario(usuarioBaixando);

                entrada = br.readLine();
            }

            br.close();
            clientela.atualizaCont();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int contaLinhas() {
        int cont = 0;
        try (FileReader frClientela = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema8\\src\\main\\resources\\clientela.txt");
             BufferedReader br = new BufferedReader(frClientela)){

            String entrada = null;
            while ((entrada = br.readLine()) != null) {
                cont++;
            }
            br.close();
            clientela.atualizaCont();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cont;
    }

    public void addUsuario(Usuario usuario) {
        clientela.addUsuario(usuario);
        arquivarUsuarios();
    }

    public void removerUsuario(Usuario usuario) {
        clientela.removerUsuario(usuario);
        arquivarUsuarios();
    }

    public void pagouQtSaldo(Usuario usuario, int saldoDevedor) {
        clientela.pagouQtSaldo(usuario, saldoDevedor);
        arquivarUsuarios();
    }
}
