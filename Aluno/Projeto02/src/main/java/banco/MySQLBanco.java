package main.java.banco;

public class MySQLBanco extends BancoDeDados {

    @Override
    protected String getUrl() {
        return "jdbc:mysql://localhost:3306/escola";
    }

    @Override
    protected String getUsuario() {
        return "root";
    }

    @Override
    protected String getSenha() {
        return "1234";
    }
}
