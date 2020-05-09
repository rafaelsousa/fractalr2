module ca.renardnumerique.fractalr2 {

    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires rxjavafx;
    requires io.reactivex.rxjava2;
    requires jakarta.el.api;
    requires com.jfoenix;    
    requires jakarta.enterprise.cdi.api;
    requires jakarta.annotation;
    requires jakarta.inject.api;


    opens ca.renardnumerique.fractalr2;
    opens ca.renardnumerique.fractalr2.examples;
    opens ca.renardnumerique.fractalr2.lsystem;
    opens ca.renardnumerique.fractalr2.utils;
    opens ca.renardnumerique.fractalr2.ui;
    


}