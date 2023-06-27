package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.model.Usuario;
import com.example.demo.model.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    TextField usuarioField;

    @FXML
    PasswordField senhaField;

    @FXML
    Label labelEntrar;

    @FXML
    public void entrar() throws IOException, SQLException {
        Usuario loginUser = new Usuario(usuarioField.getText(),senhaField.getText());
        boolean usuarioExiste = new UsuarioDAO().existe(loginUser);

        if (usuarioExiste){
            //System.out.println("Entrando...");
            HelloApplication.setRoot("main-view");
        } else {
            //System.out.println("Usuário ou senha incorretos!");
            labelEntrar.setText("Usuário ou senha incorretos!");
        }
    }
}