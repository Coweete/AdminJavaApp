package com.essence.Gui;

import com.essence.Controller.AppController;
import com.essence.Model.RfidKey;
import com.essence.Model.User;
import com.essence.Service.SpringService;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-25.
 */
public class InfoScene {

    private GridPane paneTextInfo;
    private Text textFirstname;
    private Text textLastname;
    private Text textRfid;
    private TextField textFieldRfid;
    private TextField textFieldFirstname;
    private TextField textFieldLastname;
    private Button btnSave;
    private Button btnQuit;
    private Button btnScan;
    private Button btnAdd;



    public void display(User user, AppController controller){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        textFieldFirstname = new TextField();
        textFieldLastname = new TextField();
        textFieldRfid = new TextField();
        textRfid = new Text("Rfid");
        textLastname = new Text("Lastname");
        textFirstname = new Text("Firstname");
        btnSave = new Button("Save");
        btnQuit = new Button("Quit");
        btnAdd = new Button("Add");
        btnScan = new Button("Scan Card");
        paneTextInfo = new GridPane();

        //checkUser(user);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                paneTextInfo.add(textFirstname,0,0);
                paneTextInfo.add(textLastname,0,1);
                paneTextInfo.add(textRfid,0,2);
                paneTextInfo.add(textFieldFirstname,1,0);
                paneTextInfo.add(textFieldLastname,1,1);
                paneTextInfo.add(textFieldRfid,1,2);
                paneTextInfo.add(btnSave,0,3);
                paneTextInfo.add(btnQuit,1,3);
                paneTextInfo.add(btnAdd,0,4);
                paneTextInfo.add(btnScan,1,4);

            }
        });

        btnSave.setOnAction(event -> {
            localUpdate(user);
            //TODO fixa update user
            window.close();
        });

        btnAdd.setOnAction(event1 -> {
            //TODO fixa så att add user fungerar
        });
        btnQuit.setOnAction(event -> window.close());

        btnScan.setOnAction(event -> {
            controller.testRxTx();
        });

        window.setScene(new Scene(paneTextInfo,400,400));
        window.showAndWait();
    }

    private void checkUser(User user){
        if(user != null) {
            textFieldFirstname.setText(user.getFirstName());
            textFieldLastname.setText(user.getLastName());
            textFieldRfid.setText(user.getRfid().getId());
        }
    }

    private void localUpdate(User user){
        user.setFirstName(textFieldFirstname.getText());
        user.setLastName(textFieldLastname.getText());
        user.setRfid(new RfidKey(textFieldRfid.getText()));
    }

    private User creatUser(){
        User newUser = new User();
        newUser.setFirstName(textFieldFirstname.getText());
        newUser.setLastName(textFieldLastname.getText());
        newUser.setRfid(new RfidKey(textFieldRfid.getText()));
        return newUser;
    }
}
