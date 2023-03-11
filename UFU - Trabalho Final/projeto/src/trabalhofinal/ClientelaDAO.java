package trabalhofinal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientelaDAO {
    
    private ConectaBD conectaBD;
    private static final String TABELA = "biblioteca.tb_cliente";
    private static final String SEQUENCE = "biblioteca.sq_cliente";

    public ClientelaDAO(ConectaBD conectaBD) {
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

    private Optional<Cliente> queryContato(String sql) {

        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosCliente = query.executeQuery(sql)) {


            if (dadosCliente.next()) {
                Cliente cliente = new Cliente(dadosCliente.getInt("id_cliente"), dadosCliente.getString("nome"),
                        dadosCliente.getString("endereco"), dadosCliente.getInt("idade"), dadosCliente.getBoolean("fg_em_dia"));
                cliente.printCliente();
                return Optional.of(cliente);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return Optional.empty();
    }

    private ArrayList<Cliente> queryClientela(String sql) {
        ArrayList<Cliente> clientela = new ArrayList<Cliente>();
        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosCliente = query.executeQuery(sql)) {

            while (dadosCliente.next()) {
                Cliente cliente = new Cliente(dadosCliente.getInt("id_cliente"), dadosCliente.getString("nome"),
                        dadosCliente.getString("endereco"), dadosCliente.getInt("idade"), dadosCliente.getBoolean("fg_em_dia"));
                clientela.add(cliente);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return clientela;
    }

    public Optional<Integer> addCliente(Cliente cliente) {
        String sql = "INSERT INTO " + TABELA +"(nome, endereco, idade)" +
                "VALUES (\'" + cliente.getNome() + "\', \'" +
                cliente.getEndereco() + "\', " + cliente.getIdade() + ");";

        ddl(sql);

        sql = "SELECT last_value FROM " + SEQUENCE + ";";
        Optional<Integer> id = queryInt(sql, "last_value");

        if (id.isPresent()) {
            cliente.setId(id.get());
            return id;
        }
        return Optional.empty();
    }

    public Optional<Cliente> buscaClientePorId(int idCliente) {
        return queryContato("SELECT * FROM " + TABELA + " WHERE id_cliente = " +
                idCliente + ";");
    }

    public void atualizaClientePorId(int idCliente, String nome, String endereco, int idade) {
        ddl("UPDATE " + TABELA + " SET nome = \'" + nome + "\', endereco = \'" + endereco + "\', idade = " + idade + " WHERE id_cliente = " +
                idCliente + ";");
    }

    public void rmContato(int idCliente) {
        ddl("DELETE FROM " + TABELA + " WHERE id_cliente = " + idCliente + ";");
    }

    public ArrayList<Cliente> listarClientes() {
        return queryClientela("SELECT * FROM " + TABELA + ";");
    }

    public ArrayList<Cliente> listarClientesOrdemAlfabetica() {
        return queryClientela("SELECT * FROM " + TABELA + " ORDER BY nome;");
    }

    public ArrayList<Cliente> listarClientesOrdemId () {
        return queryClientela("SELECT * FROM " + TABELA + " ORDER BY id_cliente;");
    }

    public ArrayList<Cliente> buscaClientePorNome (String nomeContato) {
        return queryClientela("SELECT * FROM " + TABELA + " WHERE LOWER(nome) LIKE LOWER('%" + nomeContato + "%');");
    }

    public Optional<Integer> getQtdCliente() {
        return queryInt("SELECT COUNT(1) FROM " + TABELA + ";", "count");
    }
}
