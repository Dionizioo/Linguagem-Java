package main.java.certificado;

import main.java.enums.TipoCurso;

public class CertificadoCursoTecnico extends Certificado {

    public CertificadoCursoTecnico(String nomeCertificado) {
        super(nomeCertificado, TipoCurso.TECNICO);
    }

    //public String getDescricao() {
    //    return "Certificado de Curso Técnico";
   // }
    
   @Override
    public String getDescricao() {
        return "Certificado de Curso Técnico";
    }
}
