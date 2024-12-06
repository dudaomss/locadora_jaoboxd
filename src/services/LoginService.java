package services;

import model.UsuarioAdmin;
import model.UsuarioComum;
import java.util.Scanner;

public class LoginService {

    public UsuarioComum loginComum() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        UsuarioComum usuario = UsuarioComum.buscarUsuarioPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login realizado com sucesso como Usuário Comum.");
            return usuario;
        } else {
            System.out.println("Usuário não encontrado ou senha incorreta.");
            return null;
        }
    }

    public UsuarioComum registrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        UsuarioComum usuario = new UsuarioComum(UsuarioComum.getUsuariosComuns().size() + 1, nome, email, senha);
        UsuarioComum.registrarUsuario(usuario);

        System.out.println("Usuário registrado com sucesso.");
        return usuario;
    }

    public UsuarioAdmin loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o email (admin): ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha (admin): ");
        String senha = scanner.nextLine();

        if (email.equals("admin@admin.com") && senha.equals("admin")) {
            System.out.println("Login realizado com sucesso como Admin.");
            return new UsuarioAdmin(1, "Administrador", email, senha);  // Retorna um usuário admin de exemplo
        } else {
            System.out.println("Credenciais inválidas para admin.");
            return null;
        }
    }
}
