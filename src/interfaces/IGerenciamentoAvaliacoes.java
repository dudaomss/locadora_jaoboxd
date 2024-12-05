package interfaces;
import model.Avaliacao;
import java.util.List;

public interface IGerenciamentoAvaliacoes {

    void avaliarFilme(int idFilme, double nota);
    List<Avaliacao> listarAvaliacoes();

}
