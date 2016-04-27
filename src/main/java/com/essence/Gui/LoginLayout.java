package com.essence.Gui;

import com.essence.Controller.AppController;
import com.sun.org.glassfish.external.statistics.BoundedRangeStatistic;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.awt.*;


/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class LoginLayout extends BorderPane {

    private AppController ctrl;

    private Button btnLogin;
    private Button btnHomeLog;
    private Button btnTest;
    private GridPane pane = new GridPane();

    public LoginLayout(){


        btnLogin = new Button("Login");
        btnLogin.setOnAction(event -> {
            ctrl.login();
        });
        btnHomeLog = new Button("HomeLogin");
        btnHomeLog.setOnAction(event -> {
            ctrl.loginHome();
        });
        btnTest = new Button("TestButton");
        btnTest.setOnAction(event -> {
            ctrl.testServer();
        });
        pane.add(btnLogin,0,0);
        pane.add(btnHomeLog,0,1);
        pane.add(btnTest,0,2);
        this.setCenter(pane);
    }


    public void setCtrl(AppController ctrl) {
        this.ctrl = ctrl;
    }
}
