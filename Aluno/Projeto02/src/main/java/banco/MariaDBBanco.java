package main.java.banco;

public class MariaDBBanco extends BancoDeDados {

    @Override
    protected String getUrl() {
        return "jdbc:mariadb://localhost:3307/escola";
    }

    @Override
    protected String getUsuario() {
        return "root";
    }

    @Override
    protected String getSenha() {
        return "1243";
    }
}