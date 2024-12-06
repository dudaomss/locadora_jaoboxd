import services.FilmeService;
import services.LoginService;
import services.AvaliacaoService;
import services.MenuService;
import exceptions.FilmeJaCadastradoException;
import exceptions.FilmeIndisponivelException;
import exceptions.FilmeNaoEncontradoException;

public class Main {
    public static void main(String[] args) {
        FilmeService filmeService = new FilmeService();
        LoginService loginService = new LoginService();
        AvaliacaoService avaliacaoService = new AvaliacaoService(filmeService);
        MenuService menuService = new MenuService(filmeService, loginService, avaliacaoService);

        try {
            // Carregar filmes do arquivo
            filmeService.carregarFilmesDoArquivo();

            // Exibir o menu inicial
            menuService.exibirMenuInicial();

        } catch (FilmeJaCadastradoException | FilmeIndisponivelException | FilmeNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}