//Alunos:
//Vinicius Dionizio Patrocinio

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {
    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        int opcao = -1, encontrado = -1;

        while (opcao != 0) {
            System.out.println(" 1-Cadastrar contato 2-Listar contatos 3-Editar contato 4-Excluir contato 0-Sair");
            System.out.println("Opção Desejada:");
            opcao = input.nextInt();
            System.out.println("");

            switch (opcao) {
                case 1:
                    Pessoa p = Contato.cadastrarContato();
                    System.out.println("Contato cadastrado com sucesso!!\n");
                    pessoas.add(p);
                    break;

                case 2:
                    System.out.println("\n -Lista de todos os contatos da agenda \n");
                    Collections.sort(pessoas);
                    System.out.println(pessoas);
                    break;

                case 3:
                    System.out.println("Editar um contato: ");
                    input.nextLine();
                    System.out.println("Qual o nome do contato que deseja editar? ");
                    String nomeUsuario = input.nextLine();

                    for (Pessoa pessoa : pessoas) {
                        if (pessoa.getNome().equals(nomeUsuario)) {
                            pessoa = Editcont.editarContato(pessoa);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Excluir contato: ");
                    input.nextLine();
                    System.out.println("Informe o nome do contato a ser excluído: ");
                    String nomePesquisa = input.nextLine();

                    for (Pessoa pessoa : pessoas) {
                        if (pessoa.getNome().equals(nomePesquisa)) {
                            encontrado = 1;
                            pessoas.remove(pessoa);
                        }
                    }

                    if (encontrado == 1) {
                        System.out.println("Contato excluído!\n");
                    } else {
                        System.out.println("Contato não encontrado!\n");
                    }
                    break;

                default:
                    System.out.println("Operação inválida!\n");
                    break;

                    case 0:
                    System.out.println("Saindo do Programa");
                    break;
            }
        }

        input.close();
    }  
}
