module com.assignment.customeraccountapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.assignment.customeraccountapp to javafx.fxml;
    exports com.assignment.customeraccountapp;
}
