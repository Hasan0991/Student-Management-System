module com.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.fx to javafx.fxml;
    exports com.example.fx;
}