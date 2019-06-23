module ui {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.rafaelsousa.fractalr2.ui to javafx.fxml;
    exports ca.rafaelsousa.fractalr2.ui;

}