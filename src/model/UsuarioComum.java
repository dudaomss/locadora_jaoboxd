package model;

import java.util.List;

public class UsuarioComum extends Usuario implements GerenciamentoAvaliacoes {
    public UsuarioComum(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    @Override
    public boolean login() {
        // Implementação do login para o usuário comum
        return true;
    }

    @Override
    public String toString() {
        return "Usuário Comum: " + nome;
    }

    @Override
    public void avaliarFilme(int idFilme, double nota) {
        // Implementação para avaliar filmes
    }

    @Override
    public List<Avaliacao> listarAvaliacoes() {
        // Implementação para listar avaliações
        return null;
    }

    // Getters e Setters
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    @Override
    public void setSenha(String senha) {
        super.setSenha(senha);
    }
}