package main.java.certificado;

import main.java.enums.TipoCurso;

public class CertificadoCursoMestrado extends Certificado {

    public CertificadoCursoMestrado(String nomeCertificado) {
        super(nomeCertificado, TipoCurso.MESTRADO);
    }

    //public String getDescricao() {
      //  return "Certificado de Curso de Mestrado";
    //}

    @Override
    public String getDescricao() {
        return "Certificado de Curso de Mestrado";
    }
}
