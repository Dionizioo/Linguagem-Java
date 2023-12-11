package main.java.service;

import java.util.Collections;
import java.util.List;

import main.java.banco.BancoDeDados;
import main.java.certificado.Certificado;

public class CertificadoService extends Service {

    public CertificadoService(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Certificado certificado) {
            bancoDeDados.inserirCertificado(certificado.getEstudanteId(), certificado.getNomeCertificado(),
                certificado.getDisciplinasIdToString(), certificado.getTipoCurso().toString());
        }
    }

    @Override
    public void update(Object object) {
        if (object instanceof Certificado certificado) {
            bancoDeDados.atualizarCertificado(certificado.getId(), certificado.getNomeCertificado(),
                certificado.getDisciplinasIdToString(), certificado.getTipoCurso().toString());
        }
    }

    @Override
    public Object select(int certificadoId) {
        return bancoDeDados.selecionarCertificadoPorId(certificadoId);
    }

    //@Override
    public List<Object> selectAll() {
    // Supondo que você tenha um estudante ou certificado de referência, pegue o estudanteId apropriado da instância.
        int estudanteId = 1; // Substitua pelo ID do estudante correto

        return Collections.singletonList(bancoDeDados.selecionarCertificados(estudanteId));
}


    @Override
    public void delete(Object object) {
        if (object instanceof Certificado certificado) {
            bancoDeDados.excluirCertificado(certificado.getId());
        }
    }
}
