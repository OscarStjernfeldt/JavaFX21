module com.example.javafx21 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires se.iths.shapes;

    opens laboration3 to javafx.fxml;
    exports laboration3;
}