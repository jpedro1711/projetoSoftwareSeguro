package dao;

import utils.ExceptionsLogger;

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
            dbHost = "jdbc:mysql://127.0.0.1:3306/projeto_softwareseguro?useSSL=false";
            dbUser = "root";
            dbPassword = "mysqlRoot2023";

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            ExceptionsLogger.log(e);
        } catch (Exception e) {
            ExceptionsLogger.log(e);
        }
    }

    public static Conexao getInstance() {
        if (instance == null) {
            return new Conexao();
        }
        return instance;
    }

    public Connection getConexao() {
        try {
            return DriverManager.getConnection(dbHost, dbUser, dbPassword);
        } catch (SQLException e) {
            ExceptionsLogger.log(e);
        }
        return null;
    }
}
