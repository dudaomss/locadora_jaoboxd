package interfaces;
import model.Filme;
import java.util.List;

public interface IGerenciamentoFilmes {

    void cadastrarFilme(Filme filme);
    List<Filme> listarFilmes();

}
