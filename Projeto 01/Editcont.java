import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Editcont {
    public static Scanner tecladoScanner = new Scanner(System.in);

    public static Pessoa editarContato(Pessoa pessoa) throws ParseException {
        int editar;

        System.out.println("Qual informação você deseja editar? 1-RG 2-Nome 3-Data de Nascimento 4-Email 5-Endereço 6-Telefone ");
        editar = tecladoScanner.nextInt();
        tecladoScanner.nextLine();

        // Switch para Escolher qual deseja alterar do contato
        switch (editar) {
            case 1:
                pessoa = rg(pessoa);
                break;

            case 2:
                pessoa = nome(pessoa);
                break;

            case 3:
                pessoa = datanasc(pessoa);
                break;

            case 4:
                pessoa = email(pessoa);
                break;

            case 5:
                pessoa = endereco(pessoa);
                break;

            case 6:
                pessoa = telefone(pessoa);
                break;

            default:
                System.out.println("Erro");
                break;
        }

        return pessoa;
    }

    // edição rg
    private static Pessoa rg(Pessoa pessoa) {
        System.out.println("\nQual o novo RG do contato?");
        String mudarrg = tecladoScanner.nextLine();
        pessoa.setRg(mudarrg);
        System.out.println("RG editado!\n");
        return pessoa;
    }

    // edição data
    private static Pessoa datanasc(Pessoa pessoa) throws ParseException {
        System.out.println("\nQual a nova data de nascimento do contato?");
        String novaData = tecladoScanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("xx/xx/xxxx");
        Date dataNascimento = formato.parse(novaData);

        pessoa.setDataNascimento(dataNascimento);
        System.out.println("Data de nascimento editada!\n");
        return pessoa;
    }
    // edição nome
    private static Pessoa nome(Pessoa pessoa) {

        System.out.println("\n Escreva o novo nome do contato");
        String mudarnome = tecladoScanner.nextLine();
        pessoa.setNome(mudarnome);
        System.out.println("O nome foi Editado com SUCESSO!!\n");
        return pessoa;
    }

    // edição telefone
    private static Pessoa telefone(Pessoa pessoa) {
        
        // Selecionar qual deseja alterar Telefone
        System.out.println("\n Qual telefone você gostaria de atualizar? 1-Comercial 2-Residente 3-Celular");
        int mudarcelular = tecladoScanner.nextInt();

        tecladoScanner.nextLine();

        System.out.println("Qual telfone teve ser atualizado? ");
        String telefonenovo = tecladoScanner.nextLine();

        // Switch para alterar entre 3 telefones
        switch (mudarcelular) {
            case 1:
                pessoa.getComercial().put("telefone Comercial", telefonenovo);
                break;
           
            case 2:
                pessoa.getResidencial().put("telefone Residencial", telefonenovo);
                break;

            case 3:
                pessoa.getCelular().put("telefone Celular", telefonenovo);
                break;

            default:
                System.out.println("Erro");
                break;
        }

        System.out.println("O telefone doi atualizado/alterado\n");
        return pessoa;
    }

    // edição email
    private static Pessoa email(Pessoa pessoa) {
        // Selecionar qual deseja alterar
        System.out.println("\n Qual email você gostaria de atualizar? 1-Primário 2-Reserva");
        int opcaoEmail = tecladoScanner.nextInt();

        tecladoScanner.nextLine();

        //Alterando o contato
        System.out.println("Qual o novo email a ser atualizado? ");
        String mudaremail = tecladoScanner.nextLine();
        switch (opcaoEmail) {
            case 1:
                pessoa.getEmailum().put("email", mudaremail);
                break;
            case 2:
                pessoa.getEmaildois().put("email reserva", mudaremail);
                break;

            default:
                System.out.println("ERRO");
                break;
        }

        System.out.println("Seu Email foi alterado com SUCESSO!!\n");
        return pessoa;
    }
    
    //Switch para alterar o endereço
    private static Pessoa editarendereco(String x, String mudarendereco, int opcao, Pessoa p) {
        switch (opcao) {
            //mudar Logradouro
            case 1:
                p.getEnderecoR().setLogradouro(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //mudar Numero
            case 2:
                p.getEnderecoR().setNumero(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //mudar complemento
            case 3:
                p.getEnderecoR().setComplemento(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //mudar Bairro
            case 4:
                p.getEnderecoR().setBairro(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //mudar cep
            case 5:
                p.getEnderecoR().setCep(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //mudar Cidade
            case 6:
                p.getEnderecoR().setCidade(mudarendereco);
                p.getResidenciale().put(x, p.getEnderecoR());
                break;
            //Se digitar errado
            default:
                System.out.println("ERRO\n");
                break;
        }

        System.out.println("O endereço desejado foi alterado com SUCESSO!!!\n");
        return p;
    }

    // Alteração do Novo Endereço
    private static Pessoa endereco(Pessoa pessoa) {
        System.out.println("\n Qual endereço você gostaria de atualizar? 1-Residencial 2-Comercial");
        int opcao = tecladoScanner.nextInt();

        System.out.println("\n Qual informação você gostaria de atualizar? 1-Logradouro 2-Número 3-Complemento 4-Bairro 5-CEP 6-Cidade");
        int opcaoEndereco = tecladoScanner.nextInt();

        tecladoScanner.nextLine();

        System.out.println("Qual a nova informação vai ser alterada ");
        String mudarendereco = tecladoScanner.nextLine();
         
        if (opcao == 1) {
            pessoa = editarendereco("endereco-Residencial", mudarendereco, opcaoEndereco, pessoa);
        } else if(opcao == 2) {
            pessoa = editarendereco("endereco-Comercial", mudarendereco, opcaoEndereco, pessoa);
        } else {
            System.out.println("Opção inválida - Tente Novamente!!!");
        }
        return pessoa;
    }

}
