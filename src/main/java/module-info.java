module com.assignment.customeraccountapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.assignment.customeraccountapp to javafx.fxml;
    exports com.assignment.customeraccountapp;
}
