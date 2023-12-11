package main.java.disciplina;

import java.util.List;

import main.java.enums.TipoCurso;

public class DisciplinaTecnico extends Disciplina {

    public DisciplinaTecnico(int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, tipoCurso);
    }

    public DisciplinaTecnico(int estudanteId, String nome, int cargaHoraria, String professor, List<Nota> notas, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
    }

    public DisciplinaTecnico(int id, int estudanteId, String nome, int cargaHoraria, String professor, String notaFinal, TipoCurso tipoCurso, boolean aprovacao) {
        super(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
    }

    @Override
    public boolean isAprovado() {
        double media = calcularMediaDasNotas();
        return media >= 7.0;
    }

    private double calcularMediaDasNotas() {
        return getNotas().stream().mapToDouble(Nota::getNota).reduce(0, Double::sum) / getNotas().size() ;
    }
}
