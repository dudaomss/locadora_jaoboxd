package model;

public class Avaliacao extends AvaliacaoBase {
    private String comentario;

    public Avaliacao(int id, int idFilme, double nota, String comentario) {
        super(id, idFilme, nota);
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Filme ID: " + getIdFilme() + " - Nota: " + getNota() + " - Comentário: " + comentario;
    }
}
