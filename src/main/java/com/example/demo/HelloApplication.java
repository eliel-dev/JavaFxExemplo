package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene scene;
    private static Scene originalScene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login-view"), 600, 500);
        stage.setTitle("Ola!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Exibe uma janela em modo modal com base no nome do arquivo fxml
     */
    public static void showModal(String fxml) throws IOException {
        // Obtém a tela atual
        Window primaryStage = scene.getRoot().getScene().getWindow();

        originalScene = scene;

        // Carrega a nova tela
        scene = new Scene(loadFXML(fxml));

        // Abre o modal
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setScene(scene);
        dialog.showAndWait();

        // Restaura a cena original
        scene = originalScene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Fecha a janela atual
     */
    public static void closeCurrentWindow() {
        ((Stage) scene.getRoot().getScene().getWindow()).close();
    }}