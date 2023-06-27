package com.example.demo.controller;

import com.example.demo.HelloApplication;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    public void abrirProdutos() throws IOException {
        System.out.println("Teste");
        HelloApplication.setRoot("produtos-view");
    }

    @FXML
    public void sair() throws IOException {
        HelloApplication.setRoot("login-view");
    }
}
