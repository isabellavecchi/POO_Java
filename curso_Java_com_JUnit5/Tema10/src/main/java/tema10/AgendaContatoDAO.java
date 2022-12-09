package tema10;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaContatoDAO {

    private ConectaBD conectaBD;

    public AgendaContatoDAO(ConectaBD conectaBD) {
        this.conectaBD = conectaBD;
    }

    private void ddl(String sql) {
        try (Connection conexao = conectaBD.criarConexao();
             Statement ddl = conexao.createStatement()) {

            ddl.executeUpdate(sql);


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
    }

    private Optional<Integer> queryInt(String sql, String campo) {
        if (!campo.matches("['(),\";]")) {
            try (Connection conexao = conectaBD.criarConexao();
                 Statement query = conexao.createStatement();
                 ResultSet intRet = query.executeQuery(sql)) {

                intRet.next();
                return Optional.of(intRet.getInt(campo));

            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
                System.exit(0);
            }
        }
        return Optional.empty();
    }

    private Optional<Contato> queryContato(String sql) {

        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosContato = query.executeQuery(sql)) {


            if (dadosContato.next()) {
                Contato contato = new Contato(dadosContato.getInt("id_contato"), dadosContato.getString("nome"),
                        dadosContato.getString("telefone"), dadosContato.getString("email"));
                contato.printContato();
                return Optional.of(contato);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return Optional.empty();
    }

    private ArrayList<Contato> queryAgenda(String sql) {
        ArrayList<Contato> agenda = new ArrayList<Contato>();
        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosContato = query.executeQuery(sql)) {

            while (dadosContato.next()) {
                Contato contato = new Contato(dadosContato.getInt("id_contato"), dadosContato.getString("nome"),
                        dadosContato.getString("telefone"), dadosContato.getString("email"));
                agenda.add(contato);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return agenda;
    }

    public Optional<Integer> addContato(Contato contato) {
        String sql = "INSERT INTO tema10.agtb_contato(nome, telefone, email)" +
                "VALUES (" + contato.getNomeQuoted() + ", " +
                contato.getTelefoneQuoted() + ", " + contato.getEmailQuoted() + ");";

        ddl(sql);

        sql = "SELECT last_value FROM tema10.agsq_contato;";
        Optional<Integer> id = queryInt(sql, "last_value");

        if (id.isPresent()) {
            contato.setId(id.get());
            return id;
        }
        return Optional.empty();
    }

    public Optional<Contato> buscaContatoPorId(int idContato) {
        return queryContato("SELECT * FROM tema10.agtb_contato WHERE id_contato = " +
                idContato + ";");
    }

    public void rmContato(int idContato) {
        ddl("DELETE FROM tema10.agtb_contato WHERE id_contato = " + idContato + ";");
    }

    public ArrayList<Contato> listarContatos() {
        return queryAgenda("SELECT * FROM tema10.agtb_contato;");
    }

    public ArrayList<Contato> listarContatosOrdemAlfabetica() {
        return queryAgenda("SELECT * FROM tema10.agtb_contato ORDER BY nome;");
    }

    public ArrayList<Contato> listarContatosOrdemId () {
        return queryAgenda("SELECT * FROM tema10.agtb_contato ORDER BY id_contato;");
    }

    public ArrayList<Contato> buscaContatoPorNome (String nomeContato) {
        return queryAgenda("SELECT * FROM tema10.agtb_contato WHERE LOWER(nome) LIKE LOWER('%" + nomeContato + "%');");
    }

    public Optional<Integer> getTamanho() {
        return queryInt("SELECT COUNT(1) FROM tema10.agtb_contato;", "count");
    }
}