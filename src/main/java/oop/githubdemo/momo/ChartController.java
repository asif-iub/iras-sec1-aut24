package oop.githubdemo.momo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import oop.githubdemo.HelloApplication;
import oop.githubdemo.User;
import oop.githubdemo.UserManager;

import java.io.IOException;
import java.util.List;

public class ChartController {
    @javafx.fxml.FXML
    private PieChart chart;

    @javafx.fxml.FXML
    public void onLoadButtonClick(ActionEvent actionEvent) {
        List<User> userList = UserManager.getUsers();

        int young, middleAged, old;
        young = middleAged = old = 0;
        for (User u: userList) {
            if (u.getAge() < 30) young += 1;
            else if (u.getAge() < 50) middleAged += 1;
            else old += 1;
        }

        chart.getData().add(new PieChart.Data("Young", young));
        chart.getData().add(new PieChart.Data("Middle Aged", middleAged));
        chart.getData().add(new PieChart.Data("Old", old));
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
