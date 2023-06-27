package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.model.Produto;
import com.example.demo.model.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {

    @FXML
    TableView<Produto> tabelaProduto;
    @FXML
    TableColumn<Produto, Integer> colunaCodigo;
    @FXML
    TableColumn<Produto, String> colunaNome;
    @FXML
    TableColumn<Produto, Integer> colunaPreco;

    @FXML
    Button btnRemover;
    @FXML
    Button btnEditar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        btnRemover.setDisable(true);
        btnEditar.setDisable(true);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = produtoDAO.getAll();
            tabelaProduto.getItems().addAll(produtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Ouvinte que habilita botÕes (remover e editar) quando seleciona uma linha da tableview tabelaProduto
        tabelaProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (tabelaProduto.getSelectionModel().getSelectedItem() != null) {
                btnRemover.setDisable(false);
                btnEditar.setDisable(false);
            }
        });
    }

    @FXML
    public void novo() throws IOException, SQLException {
        ProdutoModalController.produto = null;

        HelloApplication.showModal("produto-modal-view");

        Produto novoProduto = ProdutoModalController.produto;

        if(novoProduto != null){
            tabelaProduto.getItems().add(novoProduto);
            new ProdutoDAO().insert(novoProduto);
        }
    }

    @FXML
    public void editar() throws IOException, SQLException {
        Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        ProdutoModalController.produto = produtoSelecionado;

        if (produtoSelecionado != null){
            HelloApplication.showModal("produto-modal-view");

            Produto produtoEditado = ProdutoModalController.produto;

            produtoSelecionado.codigo = produtoEditado.codigo;
            produtoSelecionado.nome = produtoEditado.nome;
            produtoSelecionado.preco = produtoEditado.preco;

            new ProdutoDAO().update(produtoEditado);
        }
        tabelaProduto.refresh();
    }

    @FXML
    public void remover() throws SQLException {
        Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Deseja remover " + produtoSelecionado.nome + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                new ProdutoDAO().delete(produtoSelecionado);
                tabelaProduto.getItems().remove(produtoSelecionado);
            }
        }
    }

    @FXML
    public void voltar() throws IOException {
        HelloApplication.setRoot("main-view");
    }
}
