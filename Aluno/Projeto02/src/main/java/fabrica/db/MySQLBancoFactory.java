package main.java.fabrica.db;

import main.java.banco.BancoDeDados;
import main.java.banco.MySQLBanco;

public class MySQLBancoFactory implements BancoFactory {

    private MySQLBanco mySQLBanco;

    @Override
    public MySQLBanco criarBanco() {
        mySQLBanco = new MySQLBanco();
        return mySQLBanco;
    }

    @Override
    public BancoDeDados getBanco() {
        return mySQLBanco;
    }
}
