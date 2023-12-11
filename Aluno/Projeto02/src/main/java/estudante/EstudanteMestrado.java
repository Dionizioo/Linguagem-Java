package main.java.estudante;

import java.util.Objects;

import main.java.certificado.Certificado;
import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;


public class EstudanteMestrado extends Estudante {

    // public EstudanteMestrado(String nome, String cpf, TipoCurso tipoCurso) {
    //     super(nome, cpf, tipoCurso);
    // }

    public EstudanteMestrado(int id, String nome, String cpf, TipoCurso tipoCurso, boolean aprovacao) {
        super(id, nome, cpf, tipoCurso, "Mestrado", aprovacao);
    }

    @Override
    public TipoCurso getTipoCurso() {
        return TipoCurso.MESTRADO;
    }

    @Override
    public void avaliarAprovacao() {
        boolean aprovado = true;

        for (Disciplina disciplina : getDisciplinas()) {
            if (Objects.equals(disciplina.getNotas().get(0).getConceito(), "D")) {
                aprovado = false;
                break;
            }
        }

        if (aprovado) {
            Certificado certificado = new Certificado("Certificado de Conclusão - Mestrado", getDisciplinas(), getTipoCurso());
            adicionarCertificado(certificado);
        }
        setAprovado(aprovado);
    }
}
