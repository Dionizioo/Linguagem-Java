package main.java.estudante;

import java.util.ArrayList;
import java.util.List;

import main.java.certificado.Certificado;
import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;

public abstract class Estudante {

    private int id;
    private String nome;
    private final String cpf;
    private TipoCurso tipoCurso;
    private List<Disciplina> disciplinas;
    private List<Integer> disciplinasId;
    private List<Certificado> certificados;
    private boolean aprovado;
    private String escolaridade;

    protected Estudante(String nome, String cpf, TipoCurso tipoCurso, String escolaridade) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCurso = tipoCurso;
        this.disciplinas = new ArrayList<>();
        this.certificados = new ArrayList<>();
        this.disciplinasId = new ArrayList<>();
        this.aprovado = false;
        this.escolaridade = escolaridade;
    }

    protected Estudante(int id, String nome, String cpf, TipoCurso tipoCurso, String escolaridade, boolean aprovacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCurso = tipoCurso;
        this.disciplinas = new ArrayList<>();
        this.certificados = new ArrayList<>();
        this.disciplinasId = new ArrayList<>();
        this.aprovado = aprovacao;
        this.escolaridade = escolaridade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public abstract TipoCurso getTipoCurso();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getDisciplinasId() {
        getDisciplinas().forEach(disciplina -> adicionarDisciplinaId(disciplina.getId()));
        return disciplinasId;
    }

    public String getDisciplinasIdToString() {
        StringBuilder stringBuilder = new StringBuilder();
        getDisciplinasId().forEach(id -> stringBuilder.append(id.toString()).append(","));
        return stringBuilder.substring(0, stringBuilder.length() - 1).toString();
    }
    

    public void setDisciplinasIdFromString(String disciplinasIdString) {
        List<Integer> disciplinasId = new ArrayList<>();
        String[] idStrings = disciplinasIdString.split(";");

        for (String idString : idStrings) {
            try {
                int id = Integer.parseInt(idString.trim());
                disciplinasId.add(id);
            } catch (NumberFormatException e) {
                // Lidar com IDs inválidos, se necessário
            }
        }

        this.disciplinasId = disciplinasId;
    }
   

    public void setDisciplinasId(List<Integer> disciplinasId) {
        this.disciplinasId = disciplinasId;
    }

    public void adicionarDisciplinaId(Integer disciplina) {
        disciplinasId.add(disciplina);
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void adicionarCertificado(Certificado certificado) {
        certificados.add(certificado);
    }

    public abstract void avaliarAprovacao();
    
    public boolean todasDisciplinasAprovadas() {
        for (Disciplina disciplina : disciplinas) {
            if (!disciplina.isAprovado()) {
                return false;
            }
        }
        return true;
    }

    public void evoluirCurso(TipoCurso novoTipoCurso) {
        if (isAprovado() && todasDisciplinasAprovadas()) {
            if (getTipoCurso() == TipoCurso.TECNICO && novoTipoCurso == TipoCurso.BACHARELADO) {
                setTipoCurso(TipoCurso.BACHARELADO);
                System.out.println(getNome() + " evoluiu para o curso de Bacharelado.");
            } else if (getTipoCurso() == TipoCurso.BACHARELADO && novoTipoCurso == TipoCurso.MESTRADO) {
                setTipoCurso(TipoCurso.MESTRADO);
                System.out.println(getNome() + " evoluiu para o curso de Mestrado.");
            }
        }
    }
    
    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }


    @Override
    public String toString() {
        return "Estudante { " +
                "id = " + id +
                ", nome = " + nome +
                ", aprovacao = " + aprovado +
                ", cpf = " + cpf +
                ", tipoCurso = " + tipoCurso +
                " }";
    }
    


}
