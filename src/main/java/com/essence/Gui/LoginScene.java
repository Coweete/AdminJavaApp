package com.essence.Gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-26.
 */
public class LoginScene {

    private static Button btnSignin;
    private static Button btnSetUp;
    private static Text txtUser;
    private static Text txtPass;
    private static TextField txfUser;
    private static PasswordField passwordField;
    private static VBox mainLayout;
    private static GridPane textLayout;
    private static GridPane buttonLayout;
    private static SetUpScene setUpScene;



    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

        setUpScene = new SetUpScene();
        txtUser = new Text("UserName");
        txtPass = new Text("Password");
        txfUser = new TextField();
        passwordField = new PasswordField();
        btnSetUp = new Button("Set Up");
        btnSignin = new Button("Sign in");

        btnSetUp.setOnAction(event -> {
            setUpScene.display();
        });

        textLayout = new GridPane();
        textLayout.add(txtUser,0,0);
        textLayout.add(txfUser,0,1);
        textLayout.add(txtPass,1,0);
        textLayout.add(passwordField,1,1);

        buttonLayout = new GridPane();
        buttonLayout.add(btnSetUp,0,0);
        buttonLayout.add(btnSignin,3,0);

        mainLayout = new VBox();
        mainLayout.getChildren().addAll(textLayout,buttonLayout);


        window.setScene(new Scene(mainLayout,400,400));
        window.show();


    }
}
