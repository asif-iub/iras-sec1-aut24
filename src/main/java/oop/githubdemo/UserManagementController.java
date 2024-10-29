package oop.githubdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManagementController {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> usernameTC;

    @FXML
    private TableColumn<User, String> passwordTC;

    @FXML
    private TableColumn<User, Integer> ageTC;

    @FXML
    private TextField ageTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField usernameTF;

    public void initialize() {
        usernameTC.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordTC.setCellValueFactory(new PropertyValueFactory<>("password"));
        ageTC.setCellValueFactory(new PropertyValueFactory<>("age"));
    }


    @FXML
    void onAddUserButtonClick(ActionEvent event) {

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
