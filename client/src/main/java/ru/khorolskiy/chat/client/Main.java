package ru.khorolskiy.chat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/window.fxml"));
        primaryStage.setTitle("Cha-cha chat");
        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
