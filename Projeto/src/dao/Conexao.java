package dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao instance = null;
    private String dbHost;
    private String dbUser;
    private String dbPassword;
    private Connection conexao;

    private Conexao(){
        try {
            Dotenv dotenv = Dotenv.load();
            dbHost = dotenv.get("DB_HOST");
            dbUser = dotenv.get("DB_USER");
            dbPassword = dotenv.get("DB_PASSWORD");

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Conexao getInstance() {
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(dbHost, dbUser, dbPassword);
            }
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
