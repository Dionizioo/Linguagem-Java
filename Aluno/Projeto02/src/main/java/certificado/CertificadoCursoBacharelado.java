package main.java.certificado;

import main.java.enums.TipoCurso;

public class CertificadoCursoBacharelado extends Certificado {

    public CertificadoCursoBacharelado(String nomeCertificado) {
        super(nomeCertificado, TipoCurso.BACHARELADO);
    }

    //public String getDescricao() {
     //   return "Certificado de Curso de Bacharelado";
    //}
    @Override
    public String getDescricao() {
        return "Certificado de Curso de Bacharelado";
    }
}
