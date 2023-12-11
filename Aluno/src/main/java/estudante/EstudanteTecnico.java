package main.java.estudante;

import main.java.certificado.Certificado;
import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;


public class EstudanteTecnico extends Estudante {

    // public EstudanteTecnico(String nome, String cpf, TipoCurso tipoCurso) {
    //     super(nome, cpf, tipoCurso);
    // }

    public EstudanteTecnico(int id, String nome, String cpf, TipoCurso tipoCurso, boolean aprovacao) {
        super(id, nome, cpf, tipoCurso, "Técnico", aprovacao);
    }

    @Override
    public TipoCurso getTipoCurso() {
        return TipoCurso.TECNICO;
    }

    @Override
    public void avaliarAprovacao() {
        boolean todasAprovadas = true;

        for (Disciplina disciplina : getDisciplinas()) {
            if (!disciplina.isAprovado()) {
                todasAprovadas = false;
                break;
            }
        }

        if (todasAprovadas) {
            Certificado certificado = new Certificado("Certificado de Conclusão - Técnico", getDisciplinas(), getTipoCurso());
            adicionarCertificado(certificado);
        }
        setAprovado(todasAprovadas);
    }
}
