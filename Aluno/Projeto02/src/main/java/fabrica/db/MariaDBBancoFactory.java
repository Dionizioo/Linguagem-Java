package main.java.fabrica.db;

import main.java.banco.BancoDeDados;
import main.java.banco.MariaDBBanco;

public class MariaDBBancoFactory implements BancoFactory {

    private MariaDBBanco mariaDBBanco;

    @Override
    public MariaDBBanco criarBanco() {
        mariaDBBanco = new MariaDBBanco();
        return mariaDBBanco;
    }

    @Override
    public BancoDeDados getBanco() {
        return mariaDBBanco;
    }
}
