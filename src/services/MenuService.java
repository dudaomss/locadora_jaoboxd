package services;

import exceptions.FilmeIndisponivelException;
import exceptions.FilmeJaCadastradoException;
import exceptions.FilmeNaoEncontradoException;
import services.FilmeService;
import services.LoginService;
import model.UsuarioAdmin;
import model.UsuarioComum;
import java.util.Scanner;

public class MenuService {
    private final FilmeService filmeService;
    private final LoginService loginService;
    private final AvaliacaoService avaliacaoService;
    private final Scanner scanner;

    public MenuService(FilmeService filmeService, LoginService loginService, AvaliacaoService avaliacaoService) {
        this.filmeService = filmeService;
        this.loginService = loginService;
        this.avaliacaoService = avaliacaoService;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuInicial() throws FilmeNaoEncontradoException, FilmeIndisponivelException, FilmeJaCadastradoException {
        while (true) {
            System.out.println("\nEscolha o tipo de login:");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Usuário Comum");
            System.out.println("3. Registrar Usuário Comum");
            System.out.println("4. Sair");
            int tipoLogin = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (tipoLogin) {
                case 1 -> exibirMenuAdmin();
                case 2 -> exibirMenuUsuarioComum();
                case 3 -> {
                    System.out.println("Iniciando processo de registro de usuário comum...");
                    loginService.registrarUsuario();
                }
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    return; // Encerra o programa
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenuAdmin() throws FilmeJaCadastradoException, FilmeNaoEncontradoException {
        UsuarioAdmin usuarioAdmin = loginService.loginAdmin();
        if (usuarioAdmin == null) {
            System.out.println("Tentativa de login falhou. Retornando ao menu inicial.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Listar Filmes");
            System.out.println("3. Voltar para o login");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> filmeService.cadastrarFilmePeloUsuario();
                case 2 -> listarFilmes();
                case 3 -> {
                    System.out.println("Voltando para o login...");
                    return; // Sai do menu admin e retorna ao menu inicial
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true); // Loop contínuo até o usuário selecionar "Voltar para o login"
    }

    private void exibirMenuUsuarioComum() throws FilmeNaoEncontradoException, FilmeIndisponivelException {
        UsuarioComum usuarioComum = loginService.loginComum();
        if (usuarioComum == null) {
            System.out.println("Usuário não encontrado. Retornando ao menu inicial.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- Menu Usuário Comum ---");
            System.out.println("1. Alugar Filme");
            System.out.println("2. Devolver Filme");
            System.out.println("3. Listar Filmes");
            System.out.println("4. Avaliar Filme");
            System.out.println("5. Listar Avaliações");
            System.out.println("6. Voltar para o login");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> filmeService.alugarFilme();
                case 2 -> filmeService.devolverFilme();
                case 3 -> listarFilmes();
                case 4 -> avaliacaoService.avaliarFilme();
                case 5 -> avaliacaoService.listarAvaliacoes();
                case 6 -> {
                    System.out.println("Voltando para o login...");
                    return; // Sai do menu usuário comum e retorna ao menu inicial
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true); // Loop contínuo até o usuário selecionar "Voltar para o login"
    }

    private void listarFilmes() throws FilmeNaoEncontradoException {
        System.out.println("\nFilmes cadastrados:");
        for (var filme : filmeService.listarFilmes()) {
            System.out.println(filme);
        }
    }
}
