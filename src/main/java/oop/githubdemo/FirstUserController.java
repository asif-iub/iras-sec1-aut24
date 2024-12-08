package oop.githubdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class FirstUserController {
    @javafx.fxml.FXML
    private TextField usernameTF;
    @javafx.fxml.FXML
    private DatePicker dobDP;
    @javafx.fxml.FXML
    private TextField passwordTF;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void onUpdate(ActionEvent actionEvent) throws IOException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        LocalDate dob = dobDP.getValue();

        User u = new User(username, password, dob);

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("user.bin"));
            oos.writeObject(u);
            UserManager.addUser(u);
        }
        catch (IOException e){}
        finally {
            if (oos != null) {
                oos.close();
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
