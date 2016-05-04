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
    private GridPane pane = new GridPane();

    public LoginLayout(){


        btnLogin = new Button("Login");
        btnLogin.setOnAction(event -> {
            //TODO FIX 1 login only
        });
        btnHomeLog = new Button("HomeLogin");
        btnHomeLog.setOnAction(event -> {
            //TODO FIX 1 login only
        });

        pane.add(btnLogin,0,0);
        pane.add(btnHomeLog,0,1);
        this.setCenter(pane);
    }


    public void setCtrl(AppController ctrl) {
        this.ctrl = ctrl;
    }
}
