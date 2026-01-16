module com.example.front_devmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.material;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.front_devmanager to javafx.fxml;
    opens com.example.front_devmanager.controller to javafx.fxml;
    opens com.example.front_devmanager.model to com.google.gson;
    
    exports com.example.front_devmanager;
    exports com.example.front_devmanager.controller;
    exports com.example.front_devmanager.model;
    exports com.example.front_devmanager.service;
    exports com.example.front_devmanager.config;
}