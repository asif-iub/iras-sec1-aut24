package oop.githubdemo.raghibul;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import com.itextpdf.layout.property.UnitValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oop.githubdemo.HelloApplication;
import oop.githubdemo.User;
import oop.githubdemo.UserManager;

import java.io.File;
import java.io.IOException;

public class PdfController {
    private final String dest = "sample.pdf";
    @FXML
    private Label messageLabel;


    @javafx.fxml.FXML
    public void onGeneratePDFButtonClick(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Documents", "*.pdf")
        );

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile == null) {
            return;
        }

        messageLabel.setText("");
        PdfWriter writer = new PdfWriter(selectedFile);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Table table = new Table(new float[]{3, 1, 1});
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell("Item")
                .addHeaderCell("Quantity")
                .addHeaderCell("Price");

        for (User u: UserManager.getUsers()) {
            table.addCell(u.getUsername())
                    .addCell(u.getPassword())
                    .addCell("" + u.getAge());
        }

        table.addCell("Total").addCell("").addCell("" + 965);

        document.add(table);

        document.close();
        messageLabel.setText("PDF created successfully at " + selectedFile.getAbsolutePath());
    }

    @FXML
    protected void onLogOutButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
