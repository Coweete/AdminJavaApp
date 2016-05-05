package com.essence.Gui;

import com.essence.Controller.AppController;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by jonatan on 2016-04-26.
 */
public class LoginScene extends VBox{

    private Button btnSignin;
    private Button btnSigninHome;
    private Button btnSetUp;
    private Text txtUser;
    private Text txtPass;
    private TextField txfUser;
    private PasswordField passwordField;
    private GridPane textLayout;
    private GridPane buttonLayout;
    private SetUpScene setUpScene;
    private AppController controller;
    private String userName;
    private String passWord;
    private Button btnInfo;


    public LoginScene(){

        setUpScene = new SetUpScene();
        txtUser = new Text("UserName");
        txtPass = new Text("Password");
        txfUser = new TextField();
        passwordField = new PasswordField();
        btnSetUp = new Button("Settings");
        btnSignin = new Button("Sign in");


        btnSetUp.setOnAction(event -> {
            controller.setUpView(controller);
        });

        btnSignin.setOnAction(event -> {
            //controller.login();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateLoginInfo();
                    controller.login(userName,passWord);
                }
            });

        });

        textLayout = new GridPane();
        textLayout.add(txtUser,0,0);
        textLayout.add(txfUser,0,1);
        textLayout.add(txtPass,1,0);
        textLayout.add(passwordField,1,1);

        buttonLayout = new GridPane();
        buttonLayout.add(btnSetUp,0,0);
        buttonLayout.add(btnSignin,1,0);

        this.getChildren().addAll(textLayout,buttonLayout);
    }

    private void updateLoginInfo() {
        this.userName = txfUser.getText();
        this.passWord = passwordField.getText();
    }


    public void setController(AppController controller) {
        this.controller = controller;
    }
}
