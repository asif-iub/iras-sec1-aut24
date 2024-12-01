package oop.githubdemo.momo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import oop.githubdemo.HelloApplication;

import java.io.IOException;

public class ChartController {
    @javafx.fxml.FXML
    private LineChart<String, Integer> chart;

    @javafx.fxml.FXML
    public void onLoadButtonClick(ActionEvent actionEvent) {
        Series<String, Integer> s23 = new Series<>();
        s23.setName("2023");
        s23.getData().add(new Data<>("Dhaka", 64));
        s23.getData().add(new Data<>("Rajshahi", 39));
        s23.getData().add(new Data<>("Chittagong", 75));
        s23.getData().add(new Data<>("Khulna", 77));

        Series<String, Integer> s24 = new Series<>();
        s24.setName("2024");
        s24.getData().add(new Data<>("Dhaka", 29));
        s24.getData().add(new Data<>("Rajshahi", 82));
        s24.getData().add(new Data<>("Chittagong", 81));
        s24.getData().add(new Data<>("Khulna", 35));

        Series<String, Integer> s25 = new Series<>();
        s25.setName("2025");
        s25.getData().add(new Data<>("Dhaka", 72));
        s25.getData().add(new Data<>("Rajshahi", 43));
        s25.getData().add(new Data<>("Chittagong", 71));
        s25.getData().add(new Data<>("Khulna", 7));

        chart.getData().addAll(s23, s24, s25);
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onLogOutButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
