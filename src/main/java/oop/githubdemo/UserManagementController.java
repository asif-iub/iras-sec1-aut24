package oop.githubdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    void onBackButtonClick(ActionEvent event) {

    }

    @FXML
    void onLogOutButtonClick(ActionEvent event) {

    }

}
