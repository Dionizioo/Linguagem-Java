package main.java.disciplina;

import java.util.List;

import main.java.enums.TipoCurso;

public class DisciplinaBacharelado extends Disciplina {

    public DisciplinaBacharelado(int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, tipoCurso);
    }

    public DisciplinaBacharelado(int estudanteId, String nome, int cargaHoraria, String professor, List<Nota> notas, TipoCurso tipoCurso) {
        super(estudanteId, nome, cargaHoraria, professor, notas, tipoCurso);
    }

    public DisciplinaBacharelado(int id, int estudanteId, String nome, int cargaHoraria, String professor, String notaFinal, TipoCurso tipoCurso,
                                 boolean aprovacao) {
        super(id, estudanteId, nome, cargaHoraria, professor, notaFinal, tipoCurso, aprovacao);
    }

    @Override
    public boolean isAprovado() {
        double media = calcularMediaDasNotas();
        
        // Alteração: Verifique se a média é maior ou igual a 6.0 para Bacharelado
        return media >= 7.0;
    }

    private double calcularMediaDasNotas() {
        return getNotas().stream().mapToDouble(Nota::getNota).reduce(0, Double::sum) / getNotas().size();
    }
}
