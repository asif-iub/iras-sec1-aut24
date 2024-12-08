package oop.githubdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = null;
        if (UserManager.getUsers().isEmpty()) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("first-user.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        }
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }
}