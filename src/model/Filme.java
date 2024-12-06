package model;

public class Filme {
    private int id;
    private String titulo;
    private int ano;
    private String genero;
    private boolean disponivel;

    public Filme(int id, String titulo, int ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.disponivel = true; // Por padrão, o filme é disponível
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") - " + genero + " - " + (disponivel ? "Disponível" : "Alugado");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
