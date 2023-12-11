package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.disciplina.Disciplina;
import main.java.disciplina.Nota;
import main.java.enums.TipoBanco;
import main.java.enums.TipoCurso;
import main.java.estudante.Estudante;
import main.java.gerenciamento.Gerenciador;

public class Main {

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador(TipoBanco.MARIADB);

         Estudante estudanteTecnico = gerenciador.criarEstudante("Alice", "123456789", TipoCurso.TECNICO);
         Estudante estudanteBacharelado = gerenciador.criarEstudante("Bob", "987654321", TipoCurso.BACHARELADO);
         Estudante estudanteMestado = gerenciador.criarEstudante("Vanessa", "123456788", TipoCurso.MESTRADO);
        
         List<Nota> notas = new ArrayList<>();

         notas.add(new Nota(8.5f, "Primeira nota"));
         estudanteTecnico.avaliarAprovacao();
         notas.add(new Nota(7.0f, "Segunda nota"));
         notas.add(new Nota(9.2f, "Terceira nota"));

         gerenciador.criarDisciplina(estudanteTecnico, "Matemática", "Prof. Silva", 40, notas);
         gerenciador.criarDisciplina(estudanteBacharelado, "Física", "Prof. Santos", 60, notas);
         gerenciador.criarDisciplina(estudanteMestado, "Física", "Prof. Santos", 60, notas);


         estudanteTecnico.setNome("aRTHURRRRRR");
         gerenciador.updateEstudante(estudanteTecnico);
         gerenciador.updateEstudante(estudanteBacharelado);

         if (!estudanteTecnico.getDisciplinas().isEmpty()) {
             Disciplina disciplinaParaDeletar = estudanteTecnico.getDisciplinas().get(0);
             gerenciador.deletarDisciplina(estudanteTecnico, disciplinaParaDeletar);
         }

         gerenciador.exibirTodosEstudantes();
         gerenciador.exibirTodasDisciplinas();

        //Estudante estudante = gerenciador.selectEstudante(2);
        //System.out.println(estudante.toString());

        //adicionei na selecionarDisciplinaPorId
        Disciplina disciplina = gerenciador.selectDisciplina(1);
        if (disciplina != null) {
            System.out.println(disciplina.toString());
        }   else {
            System.out.println("A disciplina não foi encontrada.");
        }
        

        //Verificar esse metodo - public void excluirEstudante(int id) {
         gerenciador.deletarEstudante(estudanteTecnico);
         gerenciador.deletarEstudante(estudanteBacharelado);

         //gerenciador.trocarBanco(TipoBanco.MYSQL);

         //gerenciador.exibirTodosEstudantes();



        // Troca para o banco de dados MySQL
         gerenciador.trocarBanco(TipoBanco.MYSQL);

         // Após a troca de banco, mantenha a conexão com o MySQL aberta
         gerenciador.exibirTodosEstudantes();

         gerenciador.exibirTodasDisciplinas();

         // Depois de concluir todas as operações no banco MySQL, você pode fechar a conexão
         gerenciador.desconectar();

         
    }
}
