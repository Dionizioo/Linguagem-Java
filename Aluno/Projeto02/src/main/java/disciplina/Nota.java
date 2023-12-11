package main.java.disciplina;

public class Nota {
    private float nota;
    private String conceito;
    private String descricao;

    public Nota(float nota, String descricao) {
        this.nota = nota;
        this.descricao = descricao;
        this.conceito = "";
    }

    public Nota(String conceito, String descricao) {
        this.conceito = conceito;
        this.descricao = descricao;
        this.nota = 0;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isNumerica() {
        return this.nota > 0;
    }

    public boolean isConceito() {
        return !this.conceito.isEmpty();
    }
}
