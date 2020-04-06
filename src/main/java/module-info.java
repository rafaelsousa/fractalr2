module ca.renardnumerique.fractalr2 {

    requires transitive javafx.controls;
    requires lombok;
    requires io.reactivex.rxjava2;
    requires rxjavafx;

    exports ca.renardnumerique.fractalr2 to javafx.graphics;
}