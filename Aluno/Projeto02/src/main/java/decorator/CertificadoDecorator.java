package main.java.decorator;

import main.java.estudante.Estudante;
import main.java.enums.TipoCurso;

public class CertificadoDecorator extends Estudante {
    private Estudante estudanteDecorado;
    private String novaEscolaridade;

    public CertificadoDecorator(Estudante estudante, String novaEscolaridade) {
        super(estudante.getNome(), estudante.getCpf(), estudante.getTipoCurso(), novaEscolaridade); // Chame o construtor da classe Estudante

        this.estudanteDecorado = estudante;
        this.novaEscolaridade = novaEscolaridade;

        // Adicione a nova escolaridade ao estudante decorado
        setEscolaridade(estudante.getEscolaridade() + "," + novaEscolaridade);
    }

    @Override
    public TipoCurso getTipoCurso() {
        return estudanteDecorado.getTipoCurso();
    }

    @Override
    public void avaliarAprovacao() {
        estudanteDecorado.avaliarAprovacao();
    }
}
