module oop.githubdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens oop.githubdemo to javafx.fxml;
    exports oop.githubdemo;
    exports oop.githubdemo.raghibul;
    opens oop.githubdemo.raghibul to javafx.fxml;
    exports oop.githubdemo.momo;
    opens oop.githubdemo.momo to javafx.fxml;
}