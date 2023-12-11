package main.java.certificado;

import java.util.ArrayList;
import java.util.List;

import main.java.enums.TipoCurso;
import main.java.disciplina.Disciplina;



public class Certificado {

    private int id;
    private int estudanteId;
    private final String nomeCertificado;
    private final List<Disciplina> disciplinas;
    private List<Integer> disciplinasId;
    private final TipoCurso tipoCurso;

    public Certificado(String nomeCertificado, TipoCurso tipoCurso) {
        this.nomeCertificado = nomeCertificado;
        this.disciplinas = new ArrayList<>();
        this.disciplinasId = new ArrayList<>();
        this.tipoCurso = tipoCurso;
    }

    public Certificado(String nomeCertificado, List<Disciplina> disciplinas, TipoCurso tipoCurso) {
        this.nomeCertificado = nomeCertificado;
        this.disciplinas = disciplinas;
        this.disciplinasId = new ArrayList<>();
        this.tipoCurso = tipoCurso;
    }

    public Certificado(int id, int estudanteId, String nomeCertificado, TipoCurso tipoCurso, List<Integer> disciplinaIds) {
        this.id = id;
        this.estudanteId = estudanteId;
        this.nomeCertificado = nomeCertificado;
        this.disciplinas = new ArrayList<>();
        this.disciplinasId = disciplinaIds;
        this.tipoCurso = tipoCurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(int estudanteId) {
        this.estudanteId = estudanteId;
    }

    public String getNomeCertificado() {
        return nomeCertificado;
    }

    public void addDisciplina(Disciplina disciplina) {
        getDisciplinas().add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
        getDisciplinas().remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public List<Integer> getDisciplinasId() {
        return disciplinasId;
    }

    public void setDisciplinasId(List<Integer> disciplinasId) {
        this.disciplinasId = disciplinasId;
    }

    public void addDisciplinaId(Integer disciplina) {
        getDisciplinasId().add(disciplina);
    }

    public void removeDisciplinaId(Integer disciplina) {
        getDisciplinasId().remove(disciplina);
    }

    public String getDisciplinasIdToString() {
        StringBuilder stringBuilder = new StringBuilder();
        getDisciplinasId().forEach(dId -> stringBuilder.append(dId.toString()).append(","));
        return stringBuilder.toString().trim();
    }

    //ver se está certo,acresentar no pai e não falar no filho(certificadoCursoMestrado,bache,Tecno)
    public String getDescricao() {
        return "Certificado Genérico";
    }
}
