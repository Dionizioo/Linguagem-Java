package main.java.service;

import java.util.List;

import main.java.banco.BancoDeDados;
import main.java.disciplina.Disciplina;
import main.java.estudante.Estudante;

public abstract class Service {

    final BancoDeDados bancoDeDados;

    protected Service(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public void insert(Object object) {
    }

    public void update(Object object) {
    }

    public Object select(int id) {
        return null;
    }

    public void delete(Object object) {
    }

    public List<Estudante> selectAllSEstudantes() {
        return null;
    }
     
    public List<Disciplina> selectAllDisciplinas() {
        return null;
    }
    public abstract List<Object> selectAll();
}
