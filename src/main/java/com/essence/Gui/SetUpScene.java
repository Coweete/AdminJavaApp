package com.essence.Gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-26.
 */
public class SetUpScene {

    private static Button btnSave;
    private static Button btnTest;
    private static Button btnQuit;
    private static Text txtIp;
    private static Text txtPort;
    private static TextField txfIp;
    private static TextField txfPort;


    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Setup");

        window.showAndWait();

    }
}
