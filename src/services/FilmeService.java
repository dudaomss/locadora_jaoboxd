package services;
import interfaces.IGerenciamentoFilmes;
import interfaces.IArmazenamentoDados;
import model.Filme;
import java.util.List;

public class FilmeService {
    private IGerenciamentoFilmes gerenciamentoFilmes;
    private IArmazenamentoDados armazenamentoDeDados;

    public FilmeService(IGerenciamentoFilmes gerenciamentoFilmes, IArmazenamentoDados armazenamentoDeDados) {
        this.gerenciamentoFilmes = gerenciamentoFilmes;
        this.armazenamentoDeDados = armazenamentoDeDados;
    }

    public void cadastrarFilme(Filme filme) {
        gerenciamentoFilmes.cadastrarFilme(filme);
        armazenamentoDeDados.salvarDados();
    }

    public List<Filme> listarFilmes() {
        return gerenciamentoFilmes.listarFilmes();
    }

    public void alugarFilme(Filme filme) {
        if (filme.verificarDisponibilidade()) {
            filme.alugar();
            armazenamentoDeDados.salvarDados();
        } else {
            System.out.println("Filme não disponível para aluguel.");
        }
    }

    public void devolverFilme(Filme filme) {
        filme.devolver();
        armazenamentoDeDados.salvarDados();
    }
}