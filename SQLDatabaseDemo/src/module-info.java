module Database {

    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;

    opens db;
    opens common;
    opens models;
}