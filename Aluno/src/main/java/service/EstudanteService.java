package main.java.service;

import java.util.Collections;
import java.util.List;

import main.java.banco.BancoDeDados;
import main.java.estudante.Estudante;

public class EstudanteService extends Service {

    public EstudanteService(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Estudante estudante) {
            int estudanteId =
                bancoDeDados.inserirEstudante(estudante.getNome(), estudante.getCpf(), String.valueOf(estudante.getTipoCurso()),
                    estudante.isAprovado());

            estudante.setId(estudanteId);
        }
    }

    @Override
    public void update(Object object) {
        if (object instanceof Estudante estudante) {
            System.out.print("fdgdfgdfgdfgfdgdfgdgfd" + estudante.getId());
            bancoDeDados.atualizarEstudante(estudante.getId(), estudante.getNome(), String.valueOf(estudante.getTipoCurso()), // Alteração 1: Corrigido uso do tipoCurso
                estudante.isAprovado(), estudante.getDisciplinasIdToString());
        }
    }

    @Override
    public Object select(int estudanteId) {
        Estudante estudanteSelecionado;

        estudanteSelecionado = bancoDeDados.selecionarEstudantePorId(estudanteId);

        if (estudanteSelecionado != null) {
            System.out.println("Estudante selecionado: " + estudanteSelecionado.getNome());
        } else {
            System.out.println("Estudante não encontrado (ID: " + estudanteId + ")");
        }

        return estudanteSelecionado;
    }

    @Override
    public List<Estudante> selectAllSEstudantes() {
        // Alteração 2: Adicionado cast para Estudante na lista de retorno
        return bancoDeDados.selectAllEstudantes();
    }

    @Override
    public void delete(Object object) {
        if (object instanceof Estudante estudante) {
            bancoDeDados.excluirEstudante(estudante.getId());
            bancoDeDados.excluirCertificadoEstudanteId(estudante.getId());
            bancoDeDados.excluirDisciplinaEstudanteId(estudante.getId());
        }
    }

    @Override
    public List<Object> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }
}
