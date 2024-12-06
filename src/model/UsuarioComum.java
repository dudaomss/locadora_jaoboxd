package model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioComum extends Usuario {
    private static List<UsuarioComum> usuariosComuns = new ArrayList<>();

    public UsuarioComum(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    @Override
    public boolean login() {
        return false;
    }

    public static List<UsuarioComum> getUsuariosComuns() {
        return usuariosComuns;
    }

    public static UsuarioComum buscarUsuarioPorEmail(String email) {
        for (UsuarioComum usuario : usuariosComuns) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public static void registrarUsuario(UsuarioComum usuario) {
        usuariosComuns.add(usuario);
    }
}
