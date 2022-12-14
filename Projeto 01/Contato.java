import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Contato {
    public static Pessoa cadastrarContato() throws ParseException {
        Scanner teclado = new Scanner(System.in);

        //Nome
        System.out.println("Informe o nome:  ");
        String nome = teclado.nextLine();

        //RG
        System.out.println("Informe o RG:  ");
        String rg = teclado.nextLine();

        //Data de Nascimento
        System.out.println("Informe a data de nascimento: (Formato: dd/mm/yyyy) ");
        String dataRecebida = teclado.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formato.parse(dataRecebida);

        //Endereço e Logradouro
        System.out.println(" Endereço Residencial e Informe o logradouro:  ");
        String logradouro = teclado.nextLine();

        //Logradouro numero
        System.out.println("Informe o número do logradouro:  ");
        String numero = teclado.nextLine();

        //Complemento
        System.out.println("Informe o complemento:  ");
        String complemento = teclado.nextLine();

        //Bairro
        System.out.println("Informe o bairro:  ");
        String bairro = teclado.nextLine();

        //Cep
        System.out.println("Informe o CEP:  ");
        String cep = teclado.nextLine();

        //Cidade
        System.out.println("Informe a cidade:  ");
        String cidade = teclado.nextLine();

        Endereco Residenciale = new Endereco(logradouro, numero, complemento, bairro, cep, cidade);

        //Endereço Comercial
        System.out.println(" Endereço Comercial e Informe o logradouro:  ");
        logradouro = teclado.nextLine();

        //Logradouro numero Comercial
        System.out.println("Informe o número do logradouro:  ");
        numero = teclado.nextLine();

        //Complemento Comercial
        System.out.println("Informe o complemento:  ");
        complemento = teclado.nextLine();

        //Bairro Comercial
        System.out.println("Informe o bairro:  ");
        bairro = teclado.nextLine();

        //CEP Comercial
        System.out.println("Informe o CEP:  ");
        cep = teclado.nextLine();

        //Cidade Comercial
        System.out.println("Informe a cidade:  ");
        cidade = teclado.nextLine();

        Endereco comerciale = new Endereco(logradouro, numero, complemento, bairro, cep, cidade);

        Pessoa p = new Pessoa(nome, rg, dataNascimento, Residenciale,comerciale);

        //Email Primario
        System.out.println("Informe o email primário:  ");
        String emailum = teclado.nextLine();
        p.getEmailum().put("email", emailum);

        //Email Secundario
        System.out.println("Informe o email secundário:  ");
        String emaildois = teclado.nextLine();
        p.getEmaildois().put("email reserva", emaildois);

        //Telefone Residencial
        System.out.println("Informe o telefone residencial:  ");
        String residencial = teclado.nextLine();
        p.getResidencial().put("telefone Residencial", residencial);

        //Telefone Comercial
        System.out.println("Informe o telefone comercial:  ");
        String comercial = teclado.nextLine();
        p.getComercial().put("telefone Comercial", comercial);

        //Telefone Celular
        System.out.println("Informe o telefone celular:  ");
        String celular = teclado.nextLine();

        //Gets do Celular-Residenale-Comerciale
        p.getCelular().put("telefone Celular", celular);

        p.getResidenciale().put("endereco-Residencial", Residenciale);

        p.getComerciale().put("endereco-Comercial", comerciale);


        return p;
    }
}
