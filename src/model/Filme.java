package model;

import interfaces.IArmazenamentoDados;

import java.util.ArrayList;
import java.util.List;

public class Filme extends FilmeBase implements IArmazenamentoDados {
    private boolean statusAlugado;
    private List<Avaliacao> listaAvaliacoes;

    public Filme(int id, String titulo, int ano, String genero) {
        super(id, titulo, ano, genero);
        this.listaAvaliacoes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return titulo + " (" + ano + ") - " + genero;
    }

    public boolean isStatusAlugado() {
        return statusAlugado;
    }

    public void setStatusAlugado(boolean statusAlugado) {
        this.statusAlugado = statusAlugado;
    }

    public List<Avaliacao> getListaAvaliacoes() {
        return listaAvaliacoes;
    }

    public void setListaAvaliacoes(List<Avaliacao> listaAvaliacoes) {
        this.listaAvaliacoes = listaAvaliacoes;
    }

    public void alugar() {
        this.statusAlugado = true;
    }

    public void devolver() {
        this.statusAlugado = false;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        listaAvaliacoes.add(avaliacao);
    }
    @Override
    public boolean verificarDisponibilidade(){
        return true;
    }

    @Override
    public void salvarDados() {
        // Implementação para salvar dados do filme
    }

    @Override
    public void carregarDados() {
        // Implementação para carregar dados do filme
    }
}