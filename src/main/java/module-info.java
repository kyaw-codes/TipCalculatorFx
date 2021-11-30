module com.yci.tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.yci.tipcalculator to javafx.fxml;
    exports com.yci.tipcalculator;
}