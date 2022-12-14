import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Pessoa implements Comparable<Pessoa> {

                    //Privates das Caracteristcas da Pessoa
    private Endereco enderecoC;
    private String nome;
    private String rg;
    private Date dataNascimento;
    private Endereco enderecoR;

                    // HashMaps

    //email
    private HashMap<String, String> emailum;
    private HashMap<String, String> emaildois;
    //telefone
    private HashMap<String, String> residencial;
    private HashMap<String, String> comercial;
    private HashMap<String, String> celular;
    //endereco
    private HashMap<String, Endereco> residenciale;
    private HashMap<String, Endereco> comerciale;

    public Pessoa() {
    }

    public Pessoa(String nome, String rg, Date dataNascimento, Endereco enderecoR, Endereco enderecoC) {
        
        this.nome = nome;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.enderecoR = enderecoR;
        this.enderecoC = enderecoC;

        emailum = new HashMap<String, String>();
        emaildois = new HashMap<String, String>();
        residencial = new HashMap<String, String>();
        comercial = new HashMap<String, String>();
        celular = new HashMap<String, String>();
        residenciale = new HashMap<String, Endereco>();
        comerciale = new HashMap<String, Endereco>();
    }
 
    // Get e Set

    public Endereco getEnderecoR() {
        return enderecoR;
    }

    public void setEnderecoR(Endereco enderecoR) {
        this.enderecoR = enderecoR;
    }

    public Endereco getEnderecoC() {
        return enderecoC;
    }

    public void setEmaildois(HashMap<String, String> emaildois) {
        this.emaildois = emaildois;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public HashMap<String, String> getEmailum() {
        return emailum;
    }

    public HashMap<String, String> getEmaildois() {
        return emaildois;
    }

    public HashMap<String, String> getComercial() {
        return comercial;
    }

    public HashMap<String, String> getResidencial() {
        return residencial;
    }

    public HashMap<String, String> getCelular() {
        return celular;
    }

    public HashMap<String, Endereco> getComerciale() {
        return comerciale;
    }

    public HashMap<String, Endereco> getResidenciale() {
        return residenciale;
    }

    private String dateConvertor(Date dataNascimento) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(dataNascimento);
        return strDate;
    }

    // Parte de Impressão
    @Override
    public String toString() {
        return String.format(
                "\n Informações do contato:\n \n Nome: %s\n RG: %s\n Data de nascimento: %s \n Email : %s\n Email reserva: %s\n \n Telefone residencial: %s\n Telefone comercial: %s\n Telefone celular: %s\n \n Endereço residencial: %s\n \n Endereço comercial: %s\n",
                nome, rg, dateConvertor(dataNascimento), emailum, emaildois,residencial,
                comercial, celular,
                residenciale,comerciale);
    }

    @Override
    public int compareTo(Pessoa o) {
        return this.nome.compareTo(o.nome);
    }
}
