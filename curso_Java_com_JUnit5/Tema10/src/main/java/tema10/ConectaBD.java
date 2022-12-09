package tema10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaBD {
    static final String JDBCDriver = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://192.168.0.37:5432/agenda";

    public Connection criarConexao() throws ClassNotFoundException, SQLException {
        String usuario;
        String senha;

        try (FileReader frClientela = new FileReader("C:\\Users\\ilegra\\Documents\\estudosBase\\git\\jovens-talentos\\1-core-engineering\\isabella-ferreira\\Tema10\\src\\main\\resources\\conexao.txt");
             BufferedReader br = new BufferedReader(frClientela)){

            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(JDBCDriver);
            Properties props = new Properties();

            usuario = br.readLine();
            senha = br.readLine();

            br.close();

            props.setProperty("user", usuario);
            props.setProperty("password", senha);

            Connection conectaBD = DriverManager.getConnection(URL,props);
            if (conectaBD != null) {
                System.out.print("Conexão efetuada com sucesso...");
                return conectaBD;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
