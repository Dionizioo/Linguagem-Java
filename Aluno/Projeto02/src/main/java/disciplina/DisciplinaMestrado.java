package main.java.disciplina;

import java.util.List;

import main.java.enums.TipoCurso;

public class DisciplinaMestrado extends Disciplina {

    public DisciplinaMestrado(int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, tipoCurso);
    }

    public DisciplinaMestrado(int estudanteId, String nome, int cargaHoraria, String professor, List<Nota> notas, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
    }

    public DisciplinaMestrado(int id, int estudanteId, String nome, int cargaHoraria, String professor, String notaFinal, TipoCurso tipoCurso,
                              boolean aprovacao) {
        super(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
    }

    @Override
    public boolean isAprovado() {
        for (Nota nota : getNotas()) {
            if (nota.getConceito().equals("D")) {
                return false;
            }
        }
        return true;
    }
}
