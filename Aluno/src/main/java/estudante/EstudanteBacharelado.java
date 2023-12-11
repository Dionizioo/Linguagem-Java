package main.java.estudante;

import java.util.ArrayList;
import java.util.List;

import main.java.certificado.Certificado;
import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;


public class EstudanteBacharelado extends Estudante {

    // public EstudanteBacharelado(String nome, String cpf, TipoCurso tipoCurso) {
    //     super(nome, cpf, tipoCurso);
    // }

    public EstudanteBacharelado(int id, String nome, String cpf, TipoCurso tipoCurso, boolean aprovacao) {
        super(id, nome, cpf, tipoCurso, "Bacharelado", aprovacao);
    }

    @Override
    public TipoCurso getTipoCurso() {
        return TipoCurso.BACHARELADO;
    }

    @Override
    public void avaliarAprovacao() {
        List<Disciplina> disciplinasAprovadas = new ArrayList<>();

        for (Disciplina disciplina : getDisciplinas()) {
            if (disciplina.isAprovado()) {
                disciplinasAprovadas.add(disciplina);
            }
        }

        if (!disciplinasAprovadas.isEmpty()) {
            Certificado certificado = new Certificado("Certificado de Conclusão - Bacharelado", disciplinasAprovadas, getTipoCurso());
            adicionarCertificado(certificado);
        }
        setAprovado(disciplinasAprovadas.size() == getDisciplinas().size());
    }
}
