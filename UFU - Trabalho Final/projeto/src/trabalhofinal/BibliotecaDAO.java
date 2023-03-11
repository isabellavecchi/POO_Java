package trabalhofinal;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BibliotecaDAO {
    private ConectaBD conectaBD;
    private static final String TABELA = "biblioteca.tb_livro";
    private static final String SEQUENCE = "biblioteca.sq_livro";

    public BibliotecaDAO(ConectaBD conectaBD) {
        this.conectaBD = conectaBD;
    }

    public String getTABELA() {
        return TABELA;
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

    private Optional<Livro> queryLivro(String sql) {

        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosLivro = query.executeQuery(sql)) {


            if (dadosLivro.next()) {
                Livro livro = new Livro(dadosLivro.getInt("id_livro"), dadosLivro.getString("titulo"),
                        dadosLivro.getString("autor"), dadosLivro.getBoolean("fg_emprestado"));
                livro.printLivro();
                return Optional.of(livro);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return Optional.empty();
    }

    private ArrayList<Livro> queryBiblioteca(String sql) {
        ArrayList<Livro> biblioteca = new ArrayList<Livro>();
        try (Connection conexao = conectaBD.criarConexao();
             Statement query = conexao.createStatement();
             ResultSet dadosLivro = query.executeQuery(sql)) {

            while (dadosLivro.next()) {
                Livro livro = new Livro(dadosLivro.getInt("id_livro"), dadosLivro.getString("titulo"),
                        dadosLivro.getString("autor"), dadosLivro.getBoolean("fg_emprestado"));
                biblioteca.add(livro);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
        return biblioteca;
    }

    public Optional<Integer> addLivro(Livro livro) {
        String sql = "INSERT INTO " + TABELA +"(titulo, autor, fg_emprestado)" +
                "VALUES (\'" + livro.getTitulo() + "\', \'" +
                livro.getAutor()+ "\', " + livro.isFgEmprestado() + ");";

        ddl(sql);

        sql = "SELECT last_value FROM " + SEQUENCE + ";";
        Optional<Integer> id = queryInt(sql, "last_value");

        if (id.isPresent()) {
            livro.setId(id.get());
            return id;
        }
        return Optional.empty();
    }

    public Optional<Livro> buscaLivroPorId(int idLivro) {
        return queryLivro("SELECT * FROM " + TABELA + " WHERE id_livro = " +
                idLivro + ";");
    }

    public void atualizaLivroPorId(int idLivro, String titulo, String autor) {
        ddl("UPDATE " + TABELA + " SET titulo = \'" + titulo + "\', autor = \'" + autor + "\' WHERE id_livro = " +
                idLivro + ";");
    }

    public void rmLivro(int idLivro) {
        ddl("DELETE FROM " + TABELA + " WHERE id_livro = " + idLivro + ";");
    }

    public ArrayList<Livro> listarLivros() {
        return queryBiblioteca("SELECT * FROM " + TABELA + ";");
    }

    public ArrayList<Livro> listarLivrosOrdemAlfabetica() {
        return queryBiblioteca("SELECT * FROM " + TABELA + " ORDER BY nome;");
    }

    public ArrayList<Livro> listarLivrosOrdemId () {
        return queryBiblioteca("SELECT * FROM " + TABELA + " ORDER BY id_cliente;");
    }

    public ArrayList<Livro> buscaLivroPorAutor (String autor) {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE LOWER(autor) LIKE LOWER('%" + autor + "%');");
    }

    public ArrayList<Livro> buscaLivroPorTitulo (String titulo) {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE LOWER(titulo) LIKE LOWER('%" + titulo + "%');");
    }

    public ArrayList<Livro> buscaLivroDispPorAutor (String autor) {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE LOWER(autor) LIKE LOWER('%" + autor + "%')"
                + " AND fg_emprestado = FALSE;");
    }

    public ArrayList<Livro> buscaLivroDispPorTitulo (String titulo) {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE LOWER(titulo) LIKE LOWER('%" + titulo + "%')"
                + " AND fg_emprestado = FALSE;");
    }

    public ArrayList<Livro> buscaLivroDispPorTituloEAutor (String titulo, String autor) {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE LOWER(titulo) LIKE LOWER('%" + titulo + "%')"
                + "AND LOWER(autor) LIKE LOWER('%" + autor + "%') "
                + "AND fg_emprestado = FALSE;");
    }

    public Optional<Integer> getQtdLivro() {
        return queryInt("SELECT COUNT(1) FROM " + TABELA + ";", "count");
    }

    public void emprestarLivro(int idLivro) {
        ddl("UPDATE " + TABELA + " SET fg_emprestado = TRUE WHERE id_livro = " + idLivro + ";");
    }

    public void emprestarLivros(String idLivros) {
        ddl("UPDATE " + TABELA + " SET fg_emprestado = TRUE WHERE id_livro IN (" + idLivros + ");");
    }

    public void devolverLivros(String idLivros) {
        ddl("UPDATE " + TABELA + " SET fg_emprestado = FALSE WHERE id_livro IN (" + idLivros + ");");
    }

    public ArrayList<Livro> listarLivrosDisponiveis() {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE fg_emprestado = FALSE;");
    }

    public ArrayList<Livro> listarLivrosLocados() {
        return queryBiblioteca("SELECT * FROM " + TABELA + " WHERE fg_emprestado = TRUE;");
    }
}
