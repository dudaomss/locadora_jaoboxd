package services;

import model.Usuario;

public class UsuarioService {
    private Usuario usuario;
    private FilmeService filmeService;
    private AvaliacaoService avaliacaoService;

    public UsuarioService(Usuario usuario, FilmeService filmeService, AvaliacaoService avaliacaoService) {
        this.usuario = usuario;
        this.filmeService = filmeService;
        this.avaliacaoService = avaliacaoService;
    }

    // Realizar login
    public boolean login(String email, String senha) {
        return usuario.login(email, senha);
    }

    // Ações específicas do Usuário Comum (listar filmes, avaliar, etc.)
    public void listarFilmes() {
        filmeService.listarFilmes().forEach(filme -> System.out.println(filme));
    }

    public void avaliarFilme(int idFilme, double nota) {
        avaliacaoService.avaliarFilme(idFilme, nota);
    }

    public void listarAvaliacoes() {
        avaliacaoService.listarAvaliacoes().forEach(avaliacao -> System.out.println(avaliacao));
    }
}