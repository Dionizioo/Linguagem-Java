package main.java.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.banco.BancoDeDados;
import main.java.disciplina.Disciplina;
import main.java.enums.TipoCurso;

public class DisciplinaService extends Service {

    public DisciplinaService(BancoDeDados bancoDeDados) {
        super(bancoDeDados);
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Disciplina disciplina) {
            int disciplinaId =
                bancoDeDados.inserirDisciplina(disciplina.getEstudanteId(), disciplina.getNome(), disciplina.getProfessor(),
                    disciplina.getCargaHoraria(), disciplina.getNotaFinal(), disciplina.isAprovado());

            disciplina.setId(disciplinaId);
        }
    }

    // @Override //NAÕ SEI SE TA CERTO AQUI?????
    // public void update(Object object) {
    // if (object instanceof Disciplina disciplina) {
    //     // Certifique-se de que você está passando um objeto do tipo TipoCurso para o parâmetro tipoCurso.
    //     TipoCurso tipoCurso = disciplina.getTipoCurso();
    //     bancoDeDados.atualizarDisciplina(disciplina.getId(), disciplina.getNome(), disciplina.getProfessor(),
    //         disciplina.getCargaHoraria(), tipoCurso, disciplina.getNotaFinal(), disciplina.isAprovado());
    //     }
    // }
    
    @Override
    public void update(Object object) {
        if (object instanceof Disciplina disciplina) {
            bancoDeDados.atualizarDisciplina(disciplina.getId(), disciplina.getNome(), disciplina.getProfessor(),
                disciplina.getCargaHoraria(), disciplina.getTipoCurso().toString(), // Alteração 1: Corrigido uso do tipoCurso
                disciplina.getNotaFinal(), disciplina.isAprovado());
       }
    }


    @Override
    public Object select(int disciplinaId) {
        Disciplina disciplinaSelecionada;
        disciplinaSelecionada = bancoDeDados.selecionarDisciplinaPorId(disciplinaId);

        if (disciplinaSelecionada != null) {
            System.out.println("Disciplina selecionada: " + disciplinaSelecionada.getNome());
        } else {
            System.out.println("Disciplina não encontrada (ID: " + disciplinaId + ")");
        }

        return disciplinaSelecionada;
    }

    @Override
    public void delete(Object object) {
        if (object instanceof Disciplina disciplina) {
            bancoDeDados.excluirDisciplina(disciplina.getId());
        }
    }

    //  @Override
    //  public List<Object> selectAll() {
    //      // Alteração 2: Adicionado cast para Disciplina na lista de retorno
    //      return Collections.singletonList((Disciplina) bancoDeDados.selectDisciplinas());
    //  }
    
    public List<Object> selectAll() {
        List<Disciplina> disciplinas = bancoDeDados.selectDisciplinas(-1); // -1 ou qualquer outro valor para buscar todas as disciplinas
        List<Object> objetos = new ArrayList<>(disciplinas);
        return objetos;
    }
    

    
}
