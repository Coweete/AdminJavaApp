package com.essence.Gui;

import com.essence.Controller.AppController;
import com.essence.Model.Account;
import com.essence.Model.RfidKey;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-25.
 */
public class UpdateScene {

    private GridPane paneTextInfo;
    private Text textFirstname;
    private Text textLastname;
    private Text textRfid;
    private Text textUsername;
    private Text textPass;
    private TextField textFieldUser;
    private TextField textFieldRfid;
    private TextField textFieldFirstname;
    private TextField textFieldLastname;
    private Text textShowScanStatus;
    private PasswordField passwordField;
    private Button btnSave;
    private Button btnDelete;
    private Button btnQuit;
    private Button btnScan;
    private Button btnAdd;
    private Account account;
    private Account newUser;
    private RfidKey rfidKey;


    public void display(Account account, AppController controller) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        textShowScanStatus = new Text("");
        textFieldFirstname = new TextField();
        textFieldLastname = new TextField();
        textFieldRfid = new TextField();
        textUsername = new Text("Username");
        textPass = new Text("Password");
        textFieldUser = new TextField();
        passwordField = new PasswordField();
        textRfid = new Text("Rfid");
        textLastname = new Text("Lastname");
        textFirstname = new Text("Firstname");
        btnSave = new Button("Save");
        btnQuit = new Button("Quit");
        btnAdd = new Button("Add");
        btnScan = new Button("Scan Card");
        btnDelete = new Button("Delete");
        paneTextInfo = new GridPane();

        checkAccount(account);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                paneTextInfo.add(textFirstname, 0, 0);
                paneTextInfo.add(textLastname, 0, 1);
                paneTextInfo.add(textRfid, 0, 2);
                paneTextInfo.add(textFieldFirstname, 1, 0);
                paneTextInfo.add(textFieldLastname, 1, 1);
                paneTextInfo.add(textFieldRfid, 1, 2);
                paneTextInfo.add(textUsername, 0, 3);
                paneTextInfo.add(textFieldUser, 1, 3);
                paneTextInfo.add(textPass, 0, 4);
                paneTextInfo.add(passwordField, 1, 4);
                paneTextInfo.add(btnSave, 0, 5);
                paneTextInfo.add(btnQuit, 1, 5);
                paneTextInfo.add(btnAdd, 0, 6);
                paneTextInfo.add(btnScan, 1, 6);
                paneTextInfo.add(btnDelete,2,6);
                paneTextInfo.add(textShowScanStatus,0,7);
                paneTextInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                            textShowScanStatus.setText("");
                    }
                });
            }
        });

        btnSave.setOnAction(event -> {
            controller.updateUser(getUpdatedAccount(account));
            window.close();
        });

        btnDelete.setOnAction(event ->{
            controller.deleteAccount(account);
        });

        btnAdd.setOnAction(event -> {
            controller.addAccount(getNewAccount());
        });
        btnQuit.setOnAction(event -> window.close());

        btnScan.setOnAction(event -> {
            controller.testRxTx();
        });

        window.setScene(new Scene(paneTextInfo, 400, 400));
        window.showAndWait();
    }

    private synchronized void checkAccount(Account account){
        if(account != null){
            textFieldFirstname.setText(account.getFirstName());
            textFieldLastname.setText(account.getLastName());
            textFieldUser.setText(account.getUsername());
            passwordField.setText(account.getPassword());
            if(account.getRfidKey() != null)
                textFieldRfid.setText(account.getRfidKey().getId());
        }
    }

    public Account getUpdatedAccount(Account account) {
        account.setFirstName(textFieldFirstname.getText());
        account.setLastName(textFieldLastname.getText());
        account.setUsername(textFieldUser.getText());
        account.setRfidKey(new RfidKey(textFieldRfid.getText()));
        //// TODO: 2016-05-05 kryptera lösenordet
        //account.setPassword();
        return account;
    }

    public Account getNewAccount() {
        Account newAccount = new Account();

        return newAccount;
    }

    public synchronized void updateRfid(RfidKey rfidKey) {
        //this.rfidKey = rfidKey;
        System.out.println("Inside update scene");
        System.out.println(rfidKey.getId());
        textFieldRfid.setText(rfidKey.getId());
        setScanStatusText("Scan Complete");
    }

    public synchronized void setScanStatusText(String text){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textShowScanStatus.setText(text);

            }
        });
    }
}