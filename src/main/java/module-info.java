module oop.githubdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens oop.githubdemo to javafx.fxml;
    exports oop.githubdemo;
}