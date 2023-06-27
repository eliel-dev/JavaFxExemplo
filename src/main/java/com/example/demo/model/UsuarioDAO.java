package com.example.demo.model;

import com.example.demo.ConnetionSingleton;

import java.sql.*;

public class UsuarioDAO {

    public boolean existe (Usuario usuario) throws SQLException {
        String sql = "select count(*) from usuario where usuario = ? and senha = ?";
        try (PreparedStatement preparedStatement = ConnetionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.usuario);
            preparedStatement.setString(2, usuario.senha);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                resultado.next();
                int quantidadeUsuarios = resultado.getInt(1);
                if (quantidadeUsuarios > 0){
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
