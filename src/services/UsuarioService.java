package services;

import model.UsuarioAdmin;
import model.UsuarioComum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    private List<UsuarioComum> usuariosComum;
    private UsuarioAdmin usuarioAdmin;

    public UsuarioService() {
        usuariosComum = new ArrayList<>();
        // Adicionando um usuário admin fixo
        usuarioAdmin = new UsuarioAdmin(1, "Admin", "admin@admin.com", "admin");
    }

    // Método para registrar um usuário comum
    public void registrarUsuarioComum() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        UsuarioComum novoUsuario = new UsuarioComum(usuariosComum.size() + 1, nome, email, senha);
        usuariosComum.add(novoUsuario);

        System.out.println("Usuário comum registrado com sucesso!");
    }

    // Método de login
    public String login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verificando se é o admin
        if (email.equals(usuarioAdmin.getEmail()) && senha.equals(usuarioAdmin.getSenha())) {
            return "Login bem-sucedido como Admin!";
        }

        // Verificando se é um usuário comum
        for (UsuarioComum usuario : usuariosComum) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return "Login bem-sucedido como Usuário Comum!";
            }
        }

        return "Credenciais inválidas!";
    }

    // Métodos para acessar os menus
    public void acessarMenu(String tipoUsuario) {
        if (tipoUsuario.equals("Admin")) {
            System.out.println("Bem-vindo ao Menu Admin: Aqui você pode cadastrar filmes.");
        } else if (tipoUsuario.equals("Comum")) {
            System.out.println("Bem-vindo ao Menu Usuário Comum: Acesso restrito.");
        }
    }

    public List<UsuarioComum> getUsuariosComum() {
        return usuariosComum;
    }

    public UsuarioAdmin getUsuarioAdmin() {
        return usuarioAdmin;
    }
}
