module com.padawansduckscoders.buscaminas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires javafx.media;

    opens com.padawansduckscoders.buscaminas to javafx.fxml;
    exports com.padawansduckscoders.buscaminas;
    exports com.padawansduckscoders.buscaminas.controller;
    opens com.padawansduckscoders.buscaminas.controller to javafx.fxml;
}