package com.essence.Gui;

import com.essence.Controller.AppController;
import de.root1.rxtxrebundled.App;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-26.
 */
public class SetUpScene {

    private GridPane layout;
    private Button btnSave;
    private Button btnQuit;
    private Text txtIp;
    private Text txtPort;
    private Text txtUsbPort;
    private TextField txfIp;
    private TextField txfPort;
    private TextField txfUsbport;
    //private AppController controller;

    public void display(AppController controller){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);


        layout = new GridPane();
        txtIp = new Text("Ip");
        txtPort = new Text("Ip port");
        txtUsbPort = new Text("USB port");
        txfIp = new TextField("http://172.16.2.12:");
        txfPort = new TextField("44344/users");
        txfUsbport = new TextField("OKLART");
        btnQuit = new Button("Quit");
        btnSave = new Button("Save");

        btnQuit.setOnAction(event -> window.close());

        btnSave.setOnAction(event -> {
            controller.settings(getSettings());
            window.close();
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                layout.add(txtIp,0,0);
                layout.add(txfIp,1,0);
                layout.add(txtPort,0,1);
                layout.add(txfPort,1,1);
                layout.add(txtUsbPort,0,2);
                layout.add(txfUsbport,1,2);
                layout.add(btnSave,0,3);
                layout.add(btnQuit,1,3);
            }
        });


        window.setScene(new Scene(layout,400,400));
        window.showAndWait();
    }

    private String[] getSettings(){
        String[] setting = new String[3];
        setting[0] = txfIp.getText();
        setting[1] = txfPort.getText();
        setting[2] = txtUsbPort.getText();
        return setting;
    }

    /*
    public void setController(AppController controller) {
        this.controller = controller;
    }
    */
}
