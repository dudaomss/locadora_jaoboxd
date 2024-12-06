package services;

import exceptions.FilmeIndisponivelException;
import exceptions.FilmeJaCadastradoException;
import exceptions.FilmeNaoEncontradoException;
import model.Filme;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class FilmeService {
    private final List<Filme> filmes;
    private static final String ARQUIVO_FILMES = "src/arquivos/filmes.txt";

    public FilmeService() {
        this.filmes = new ArrayList<>();
        carregarFilmesDoArquivo(); // Carrega os filmes do arquivo quando o serviço é iniciado
    }

    public void cadastrarFilme(Filme filme) throws FilmeJaCadastradoException {
        if (filmes.stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(filme.getTitulo()) && f.getAno() == filme.getAno())) {
            throw new FilmeJaCadastradoException("Filme já cadastrado: " + filme.getTitulo());
        }
        filmes.add(filme);
        salvarFilmesNoArquivo(); // Salva o novo filme no arquivo
    }

    public List<Filme> listarFilmes() throws FilmeNaoEncontradoException {
        if (filmes.isEmpty()) {
            throw new FilmeNaoEncontradoException("Nenhum filme encontrado.");
        }
        return filmes;
    }

    public void cadastrarFilmePeloUsuario() throws FilmeJaCadastradoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do filme:");
        String titulo = scanner.nextLine();

        System.out.println("Digite o ano do filme:");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.println("Digite o gênero do filme:");
        String genero = scanner.nextLine();

        if (filmes.stream().anyMatch(f -> f.getTitulo().equalsIgnoreCase(titulo) && f.getAno() == ano)) {
            throw new FilmeJaCadastradoException("Filme já cadastrado: " + titulo);
        }

        int id = filmes.size() + 1;
        Filme filme = new Filme(id, titulo, ano, genero);
        filmes.add(filme);
        salvarFilmesNoArquivo(); // Salva o novo filme no arquivo

        System.out.println("Filme cadastrado com sucesso: " + filme);
    }

    public void listarFilmesDisponiveis() {
        System.out.println("\n--- Filmes Disponíveis ---");
        for (Filme filme : filmes) {
            System.out.println(filme.getId() + ". " + filme.getTitulo() + " (" + filme.getAno() + ") - " +
                    (filme.isDisponivel() ? "Disponível" : "Alugado"));
        }
    }

    public void alugarFilme() throws FilmeIndisponivelException {
        listarFilmesDisponiveis();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDigite o ID do filme que deseja alugar:");
        int id = scanner.nextInt();

        Optional<Filme> filmeOpt = filmes.stream()
                .filter(f -> f.getId() == id && f.isDisponivel())
                .findFirst();

        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            filme.setDisponivel(false);
            salvarFilmesNoArquivo(); // Atualiza o estado no arquivo
            System.out.println("Filme alugado com sucesso: " + filme);
        } else {
            throw new FilmeIndisponivelException("Filme não encontrado ou já está alugado.");
        }
    }

    public void devolverFilme() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Filmes Alugados ---");
        filmes.stream()
                .filter(f -> !f.isDisponivel())
                .forEach(System.out::println);

        System.out.println("\nDigite o ID do filme que deseja devolver:");
        int id = scanner.nextInt();

        Optional<Filme> filmeOpt = filmes.stream()
                .filter(f -> f.getId() == id && !f.isDisponivel())
                .findFirst();

        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            filme.setDisponivel(true);
            salvarFilmesNoArquivo(); // Atualiza o estado no arquivo
            System.out.println("Filme devolvido com sucesso: " + filme);
        } else {
            System.out.println("Filme não encontrado ou não está alugado.");
        }
    }

    public void salvarFilmesNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FILMES))) {
            for (Filme filme : filmes) {
                writer.write(filme.getTitulo() + "," + filme.getAno() + "," + filme.getGenero() + "," + filme.isDisponivel());
                writer.newLine();
            }
            System.out.println("Filmes salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar filmes no arquivo: " + e.getMessage());
        }
    }

    public void carregarFilmesDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FILMES))) {
            String linha;
            filmes.clear(); // Limpa a lista antes de carregar os filmes do arquivo
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String titulo = dados[0];
                    int ano = Integer.parseInt(dados[1]);
                    String genero = dados[2];
                    boolean disponivel = Boolean.parseBoolean(dados[3]);

                    int id = filmes.size() + 1;
                    Filme filme = new Filme(id, titulo, ano, genero);
                    filme.setDisponivel(disponivel);
                    filmes.add(filme);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de filmes não encontrado. Um novo será criado ao salvar.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar filmes do arquivo: " + e.getMessage());
        }
    }
}