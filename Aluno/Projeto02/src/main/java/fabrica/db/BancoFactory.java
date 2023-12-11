package main.java.fabrica.db;

import main.java.banco.BancoDeDados;

public interface BancoFactory {

    BancoDeDados criarBanco();
    BancoDeDados getBanco();
}
