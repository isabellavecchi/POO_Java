package trabalhofinal;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoEmprestimosDAO {
    private BibliotecaDAO bibliotecaDAO;
    private ClientelaDAO clientelaDAO;
    
    private ConectaBD conectaBD;
    private static final String TABELA = "biblioteca.tb_emprestimo";
    private static final String SEQUENCE = "biblioteca.sq_emprestimo";

    public HistoricoEmprestimosDAO(BibliotecaDAO bibliotecaDAO, ClientelaDAO clientelaDAO, ConectaBD conectaBD) {
        this.bibliotecaDAO = bibliotecaDAO;
        this.clientelaDAO = clientelaDAO;
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

    private Optional<Emprestimo> queryEmprestimo(String sql) {

        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosEmprestimo = query.executeQuery(sql)) {


            if (dadosEmprestimo.next()) {
                int id = dadosEmprestimo.getInt("id_emprestimo");
                Optional<Cliente> oCliente = clientelaDAO.buscaClientePorId(dadosEmprestimo.getInt("id_cliente"));
                Cliente cliente = null;
                if(oCliente.isPresent()) {
                    cliente = oCliente.get();
                }
                Optional<Livro> oLivro = bibliotecaDAO.buscaLivroPorId(dadosEmprestimo.getInt("id_livro"));
                Livro livro = null;
                if(oLivro.isPresent()) {
                    livro = oLivro.get();
                }
                LocalDate dataLocacao = dadosEmprestimo.getObject("data_locacao", LocalDate.class);
                LocalDate dataADevolver = dadosEmprestimo.getObject("data_a_devolver", LocalDate.class);
                LocalDate dataDevolucao = dadosEmprestimo.getObject("data_devolucao", LocalDate.class);
                boolean fgQuitado = dadosEmprestimo.getBoolean("fg_quitado");
                Emprestimo emprestimo = new Emprestimo(id, cliente, livro, dataLocacao, dataADevolver, dataDevolucao, fgQuitado);
                emprestimo.printEmprestimo();
                return Optional.of(emprestimo);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return Optional.empty();
    }

    private ArrayList<Emprestimo> queryHistoricoEmprestimo(String sql) {
        ArrayList<Emprestimo> historicoEmprestimos = new ArrayList<Emprestimo>();
        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosEmprestimo = query.executeQuery(sql)) {

            while (dadosEmprestimo.next()) {
                int id = dadosEmprestimo.getInt("id_emprestimo");
                Optional<Cliente> oCliente = clientelaDAO.buscaClientePorId(dadosEmprestimo.getInt("id_cliente"));
                Cliente cliente = null;
                if(oCliente.isPresent()) {
                    cliente = oCliente.get();
                }
                Optional<Livro> oLivro = bibliotecaDAO.buscaLivroPorId(dadosEmprestimo.getInt("id_livro"));
                Livro livro = null;
                if(oLivro.isPresent()) {
                    livro = oLivro.get();
                }
                LocalDate dataLocacao = dadosEmprestimo.getObject("data_locacao", LocalDate.class);
                LocalDate dataADevolver = dadosEmprestimo.getObject("data_a_devolver", LocalDate.class);
                LocalDate dataDevolucao = dadosEmprestimo.getObject("data_devolucao", LocalDate.class);
                boolean fgQuitado = dadosEmprestimo.getBoolean("fg_quitado");
                Emprestimo emprestimo = new Emprestimo(id, cliente, livro, dataLocacao, dataADevolver, dataDevolucao, fgQuitado);
                historicoEmprestimos.add(emprestimo);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return historicoEmprestimos;
    }

    public Optional<Integer> addEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO " + TABELA +"(id_cliente, id_livro)" +
                "VALUES (" + emprestimo.getCliente().getId() + ", " + emprestimo.getLivro().getId() + ");";

        ddl(sql);

        sql = "SELECT last_value FROM " + SEQUENCE + ";";
        Optional<Integer> id = queryInt(sql, "last_value");

        if (id.isPresent()) {
            emprestimo.setId(id.get());
            return id;
        }
        return Optional.empty();
    }

    public void addEmprestimo(int idCliente, int idLivro) {
        String sql = "INSERT INTO " + TABELA +"(id_cliente, id_livro)" +
                "VALUES (" + idCliente + ", " + idLivro + ");";

        ddl(sql);
    }

    public Optional<Emprestimo> buscaEmprestimoPorId(int idEmprestimo) {
        return queryEmprestimo("SELECT * FROM " + TABELA + " WHERE id_emprestimo = " +
                idEmprestimo + ";");
    }

    public Optional<Emprestimo> buscaEmprestimoPorIdLivro (int idLivro) {
        return queryEmprestimo("SELECT * FROM " + TABELA + " WHERE id_livro = " + idLivro + ";");
    }

    public ArrayList<Emprestimo> buscaEmprestimoPorCliente (Cliente cliente) {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + " WHERE id_cliente = " + cliente.getId() + ";");
    }

    public ArrayList<Emprestimo> buscaEmprestimoPorLivro (String titulo) {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + " WHERE id_livro = "
                + "(SELECT id_livro FROM " + bibliotecaDAO.getTABELA() + " WHERE titulo = " + titulo + ");");
    }

    public ArrayList<Emprestimo> buscaEmprestimosQuitados () {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + " WHERE fg_quitado = TRUE;");
    }

    public ArrayList<Emprestimo> buscaEmprestimosNaoQuitados () {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + " WHERE fg_quitado = FALSE;");
    }

    public void rmEmprestimo(int idEmprestimo) {
        ddl("DELETE FROM " + TABELA + " WHERE id_emprestimo = " + idEmprestimo + ";");
    }

    public ArrayList<Emprestimo> listarEmprestimos() {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + ";");
    }

    public ArrayList<Emprestimo> listarEmprestimosOrdemId () {
        return queryHistoricoEmprestimo("SELECT * FROM " + TABELA + " ORDER BY id_emprestimo;");
    }

    public Optional<Integer> getQtdCliente() {
        return queryInt("SELECT COUNT(1) FROM " + TABELA + ";", "count");
    }

    /*public void atualizaHistoricoEUsuario(){
    }*/
    
    public void emprestar(Cliente usuario, ArrayList<Livro> livros) {
        for(Livro livro : livros) {
            addEmprestimo(new Emprestimo(usuario, livro));
        }
    }

    public void devolver(String idLivros) {
        ddl("UPDATE " + TABELA + " SET data_devolucao = CURRENT_DATE WHERE id_livro IN (" + idLivros + ");");
        //ddl("") atualizar cliente e historico
        bibliotecaDAO.devolverLivros(idLivros);
    }
}
