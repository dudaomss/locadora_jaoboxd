package model;

public abstract class FilmeBase {
    protected int id;
    protected String titulo;
    protected int ano;
    protected String genero;
    private boolean statusAlugado;

    public FilmeBase(int id, String titulo, int ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.statusAlugado = true;
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
        return statusAlugado;
    }

    public void setDisponivel(boolean statusAlugado) {
        this.statusAlugado = statusAlugado;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", status=" + (statusAlugado ? "Disponível" : "Alugado") +
                '}';
    }
}
