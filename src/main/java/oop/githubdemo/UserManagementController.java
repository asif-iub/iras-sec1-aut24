package oop.githubdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

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
    private TableColumn<User, LocalDate> dobTC;

    @FXML
    private DatePicker dobDP;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private Label messageLabel;

    public void initialize() {
        usernameTC.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordTC.setCellValueFactory(new PropertyValueFactory<>("password"));
        ageTC.setCellValueFactory(new PropertyValueFactory<>("age"));
        dobTC.setCellValueFactory(new PropertyValueFactory<>("dob"));

        userTableView.getItems().addAll(UserManager.getUsers());
    }

    @FXML
    void onDeleteUserButtonClick(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userTableView.getItems().remove(selectedUser);
            UserManager.deleteUser(selectedUser);
        }
    }

    @FXML
    void onAddUserButtonClick(ActionEvent event) {
        try {
            String username = usernameTF.getText();
            if (username.isBlank()) {
                messageLabel.setText("Username cannot be empty!");
                return;
            }
            if (username.contains(" ")) {
                messageLabel.setText("Invalid username!");
                return;
            }

            for (User u: userTableView.getItems()) {
                if (u.getUsername().equals(username)) {
                    messageLabel.setText("This username is not available!");
                    return;
                }
            }

            String password = passwordTF.getText();
            if (password.isBlank()) {
                messageLabel.setText("Password cannot be empty");
                return;
            }

            LocalDate dob = dobDP.getValue();
            if (dob == null || dob.plusYears(18).isAfter(LocalDate.now())){
                messageLabel.setText("User must be at least 18 years old!");
                return;
            }

            User u = new User(username, password, dob);
            userTableView.getItems().add(u);
            UserManager.addUser(u);
        }
        catch (NumberFormatException e) {
            messageLabel.setText("Invalid age!");
        }
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

    @FXML
    void onLoadText(ActionEvent event) throws IOException {
        FileReader fr = new FileReader("user.txt");

        char[] content = new char[20];
        while (true) {
            int ret = fr.read(content);
            if (ret == -1)
                break;
            System.out.println(new String(content));
        }

        fr.close();
    }

}
