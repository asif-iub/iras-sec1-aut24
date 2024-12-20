package oop.githubdemo.momo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oop.githubdemo.HelloApplication;
import oop.githubdemo.User;
import oop.githubdemo.UserManager;

import java.io.*;
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

    @Deprecated
    void onLoadText(ActionEvent event) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("user.txt"));

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Could not open file!");
        }
        catch(IOException e) {
            System.out.println("Error: Could not read from the file!");
        }
        finally {
            if (br != null)
                br.close();
        }
    }

    @FXML
    public void onEdit(ActionEvent actionEvent) throws IOException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) return;

        // change scene to edit user
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("momo/edit-user.fxml"));
        Parent root = fxmlLoader.load();

        EditUserController controller = fxmlLoader.getController();
        controller.setUser(selectedUser);

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

    @FXML
    public void onSave(ActionEvent actionEvent) throws IOException{
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("user.bin"));
            for (User u : userTableView.getItems()) {
                oos.writeObject(u);
            }
        }
        catch (IOException e){}
        finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
}
