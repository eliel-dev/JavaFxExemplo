package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.model.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoModalController implements Initializable {

    @FXML
    TextField codigofield;
    @FXML
    TextField nomefield;
    @FXML
    TextField precofield;

    @FXML
    Button salvaProduto;
    @FXML
    Button cancelaProduto;

    public static Produto produto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produto produtoSelecionado = ProdutoModalController.produto;

        if (produtoSelecionado != null){
            codigofield.setText(Integer.toString(produtoSelecionado.codigo));
            nomefield.setText(produtoSelecionado.nome);
            precofield.setText(Double.toString(produtoSelecionado.preco));
        }

        precofield.textProperty().addListener((o, oldValue, newValue) -> {
            precofield.setText(newValue.replaceAll("[^\\d.]",""));
        });
    }

    @FXML
    public void salvar(){

        if(!nomefield.getText().isBlank()){
            if (precofield.getText().matches("[0-9]+[\\.]*[0-9]*[\\,.]*[0-9]*")){
                Produto novoProduto = new Produto();

                novoProduto.nome = nomefield.getText();
                novoProduto.preco = Double.parseDouble(precofield.getText());

                produto = novoProduto;

                HelloApplication.closeCurrentWindow();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Preço invalido");
                alert.setHeaderText(null);
                alert.setContentText("O preço deve ser informado no formato (99.99)! ");

                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nome vazio");
            alert.setHeaderText(null);
            alert.setContentText("O nome do produto não pode estar em branco! ");

            alert.showAndWait();
        }
    }

    @FXML
    public void cancela(){
        HelloApplication.closeCurrentWindow();
    }
}
