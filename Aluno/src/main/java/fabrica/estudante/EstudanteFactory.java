package main.java.fabrica.estudante;

import main.java.enums.TipoCurso;
import main.java.estudante.Estudante;
import main.java.estudante.EstudanteBacharelado;
import main.java.estudante.EstudanteMestrado;
import main.java.estudante.EstudanteTecnico;

public class EstudanteFactory {
    public static Estudante criarEstudante(String nome, String cpf, TipoCurso tipoCurso) {
        return switch (tipoCurso) {
            case TECNICO -> new EstudanteTecnico(0, nome, cpf, tipoCurso, false);
            case BACHARELADO -> new EstudanteBacharelado(0, nome, cpf, tipoCurso, false);
            case MESTRADO -> new EstudanteMestrado(0, nome, cpf, tipoCurso, false);
        };
    }

    public static Estudante criarEstudante(int id, String nome, String cpf, TipoCurso tipoCurso, boolean aprovacao) {
        return switch (tipoCurso) {
            case TECNICO -> new EstudanteTecnico(id, nome, cpf, tipoCurso, aprovacao);
            case BACHARELADO -> new EstudanteBacharelado(id, nome, cpf, tipoCurso, aprovacao);
            case MESTRADO -> new EstudanteMestrado(id, nome, cpf, tipoCurso, aprovacao);
        };
    }
}
