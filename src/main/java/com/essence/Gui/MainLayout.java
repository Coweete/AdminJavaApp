package com.essence.Gui;

import com.essence.Controller.AppController;
import com.essence.Model.User;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by jonatan on 2016-04-20.
 */
public class MainLayout extends GridPane {

    private AppController controller;
    private ListView<User> listView;
    private Button btnGetAll;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnshowInfo;
    private User currentUser;
    private Text adminLogg;
    private User[] userList;

    public MainLayout(){
        adminLogg = new Text();
        listView = new ListView<>();
        btnAdd = new Button("Add User");
        btnUpdate = new Button("Update User");
        btnshowInfo = new Button("Show User Info");
        btnshowInfo.setOnAction(event1 -> {
            showInfo();
        });
        btnGetAll = new Button("Get Users");
        btnGetAll.setOnAction(event -> {
            setList();
        });
        add(adminLogg,0,0);
        add(btnGetAll,7,0);
        add(btnshowInfo,7,1);
        add(btnAdd,7,2);
        add(btnUpdate,7,3);
        add(listView,1,1);
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }

    public synchronized void setList(){
        System.out.println("hello1");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
                userList = controller.getUserList();
                for (int i = 0; i < userList.length; i++) {
                    //listView.getItems().add(i,userList[i].getFirstName() + " " + userList[i].getLastName());
                    listView.getItems().add(i,userList[i]);
                }
            }
        });
    }

    public synchronized void showInfo(){
        System.out.println(listView.getSelectionModel().getSelectedItem());
        //System.out.println(userList[1].getKey().getId());
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        System.out.println("innan login skrivs ut");
        loginName();
    }
    private synchronized void loginName(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adminLogg.setText(currentUser.getFirstName() + " " + currentUser.getLastName()
                    + " " + currentUser.getKey().toString());

            }
        });
    }
}
