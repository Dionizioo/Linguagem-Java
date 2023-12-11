package main.java.fabrica.disciplina;

import java.util.List;

import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;
import main.java.disciplina.DisciplinaBacharelado;
import main.java.disciplina.DisciplinaMestrado;
import main.java.disciplina.DisciplinaTecnico;
import main.java.disciplina.Nota;

public class DisciplinaFactory {

    public static Disciplina criarDisciplina(int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso, List<Nota> notas) {
        return switch (tipoCurso) {
            case TECNICO -> new DisciplinaTecnico(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
            case BACHARELADO -> new DisciplinaBacharelado(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
            case MESTRADO -> new DisciplinaMestrado(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
        };
    }

    public static Disciplina criarDisciplina(int id, int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso, String notaFinal, boolean aprovacao) {
        return switch (tipoCurso) {
            case TECNICO -> new DisciplinaTecnico(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
            case BACHARELADO -> new DisciplinaBacharelado(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
            case MESTRADO -> new DisciplinaMestrado(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
        };
    }
}
