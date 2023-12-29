module com.example.payment {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.payment to javafx.fxml;
    exports com.example.payment;
}