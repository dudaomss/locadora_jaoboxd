package services;
import interfaces.IArmazenamentoDados;
import interfaces.IGerenciamentoAvaliacoes;
import model.Avaliacao;
import java.util.List;

public class AvaliacaoService {
    private IGerenciamentoAvaliacoes gerenciamentoAvaliacoes;
    private IArmazenamentoDados armazenamentoDeDados;

    public AvaliacaoService(IGerenciamentoAvaliacoes gerenciamentoAvaliacoes, IArmazenamentoDados armazenamentoDeDados) {
        this.gerenciamentoAvaliacoes = gerenciamentoAvaliacoes;
        this.armazenamentoDeDados = armazenamentoDeDados;
    }

    // Avaliar filme
    public void avaliarFilme(int idFilme, double nota) {
        gerenciamentoAvaliacoes.avaliarFilme(idFilme, nota);
        armazenamentoDeDados.salvarDados(); // Salva dados após avaliação
    }

    // Listar avaliações
    public List<Avaliacao> listarAvaliacoes() {
        return gerenciamentoAvaliacoes.listarAvaliacoes();
    }
}