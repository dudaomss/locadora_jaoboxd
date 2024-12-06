package services;

import exceptions.FilmeNaoEncontradoException;
import model.Avaliacao;
import model.Filme;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AvaliacaoService {
    private final List<Avaliacao> avaliacoes;
    private final FilmeService filmeService;

    public AvaliacaoService(FilmeService filmeService) {
        this.avaliacoes = new ArrayList<>();
        this.filmeService = filmeService;
    }

    public void avaliarFilme() throws FilmeNaoEncontradoException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual filme você deseja avaliar?");
        // Listar os filmes com número de identificação
        List<Filme> filmes = filmeService.listarFilmes(); // Obtém a lista de filmes do FilmeService
        for (int i = 0; i < filmes.size(); i++) {
            System.out.println((i + 1) + ". " + filmes.get(i).getTitulo());
        }

        System.out.print("Digite o número do filme: ");
        int numeroFilme = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        if (numeroFilme < 1 || numeroFilme > filmes.size()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        Filme filmeSelecionado = filmes.get(numeroFilme - 1);

        System.out.print("Digite a nota de 0 a 5: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        if (nota < 0 || nota > 5) {
            System.out.println("Nota inválida. A nota deve ser entre 0 e 5.");
            return;
        }

        System.out.print("Digite um comentário (opcional): ");
        String comentario = scanner.nextLine();

        // Gerar um ID para a nova avaliação
        int id = avaliacoes.size() + 1;
        Avaliacao avaliacao = new Avaliacao(id, filmeSelecionado.getId(), nota, comentario);
        avaliacoes.add(avaliacao);

        System.out.println("Filme avaliado com sucesso!");
    }

    public void listarAvaliacoes() throws FilmeNaoEncontradoException {
        if (avaliacoes.isEmpty()) {
            System.out.println("Ainda não há avaliações.");
            return;
        }

        // Agrupar avaliações por filme e calcular a média
        List<Filme> filmes = filmeService.listarFilmes(); // Obtém a lista de filmes do FilmeService

        System.out.println("\n--- Avaliações ---");
        for (Filme filme : filmes) {
            // Filtrar avaliações do filme atual
            List<Avaliacao> avaliacoesFilme = avaliacoes.stream()
                    .filter(avaliacao -> avaliacao.getIdFilme() == filme.getId())
                    .toList();

            if (!avaliacoesFilme.isEmpty()) {
                // Calcular a média das avaliações
                double mediaAvaliacoes = avaliacoesFilme.stream()
                        .mapToDouble(Avaliacao::getNota)
                        .average()
                        .orElse(0);

                System.out.println("Filme: " + filme.getTitulo() + " (" + filme.getAno() + ") - Gênero: " + filme.getGenero());
                System.out.println("Média de Avaliação: " + String.format("%.2f", mediaAvaliacoes));
                System.out.println("--- Comentários ---");

                for (Avaliacao avaliacao : avaliacoesFilme) {
                    System.out.println("Nota: " + avaliacao.getNota() + " - Comentário: " + avaliacao.getComentario());
                }
                System.out.println();
            }
        }
    }
}
