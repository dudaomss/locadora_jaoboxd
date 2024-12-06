package model;

public abstract class AvaliacaoBase {
    protected int id;
    protected int idFilme;
    protected double nota;

    public AvaliacaoBase(int id, int idFilme, double nota) {
        this.id = id;
        this.idFilme = idFilme;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public abstract String toString();
}