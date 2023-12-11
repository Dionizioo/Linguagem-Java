package main.java.disciplina;

import java.util.ArrayList;
import java.util.List;

import main.java.enums.TipoCurso;

public class Disciplina {

    private int id;
    private int estudanteId;
    private final String nome;
    private final int cargaHoraria;
    private final String professor;
    private final List<Nota> notas;
    private String notaFinal;
    private final TipoCurso tipoCurso;

    private boolean aprovacao;

    public Disciplina(int estudanteId, String nome, int cargaHoraria, String professor, TipoCurso tipoCurso) {
        this.estudanteId = estudanteId;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.notas = new ArrayList<>();
        this.tipoCurso = tipoCurso;
    }

    public Disciplina(int estudanteId, String nome, int cargaHoraria, String professor, List<Nota> notas, TipoCurso tipoCurso) {
        this.estudanteId = estudanteId;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.notas = notas;
        this.tipoCurso = tipoCurso;
        this.aprovacao = isAprovado();
    }

    public Disciplina(int id, int estudanteId, String nome, int cargaHoraria, String professor, String notaFinal, TipoCurso tipoCurso, boolean aprovacao) {
        this.id = id;
        this.estudanteId = estudanteId;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.notas = new ArrayList<>();
        this.notaFinal = notaFinal;
        this.tipoCurso = tipoCurso;
        this.aprovacao = aprovacao;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getProfessor() {
        return professor;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void addNota(Nota nota) {
        notas.add(nota);
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

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getNotaFinal() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Notas: ");
        getNotas().forEach(nota -> stringBuilder.append(nota.getNota()).append("\n"));
        return stringBuilder.toString();
    }

    public boolean isAprovado() {
        return false;
    }

    @Override
    public String toString() {
        return "Disciplina {" +
                "id =" + id + ", " +
                "estudanteId = " + estudanteId + ", " +
                "nome ='" + nome + ", " +
                "cargaHoraria = " + cargaHoraria + ", " +
                "professor = " + professor + ", " +
                "notas = " + notas.stream().mapToDouble(Nota::getNota).toString() +
                ", notaFinal ='" + notaFinal + '\'' +
                ", tipoCurso =" + tipoCurso + '}';
    }
}
