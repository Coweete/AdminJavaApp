package com.essence.Gui;

import com.essence.Controller.AppController;
import com.essence.Model.User;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Created by jonatan on 2016-04-20.
 */
public class MainLayout extends GridPane {

    private AppController controller;
    private ListView<String> listView;
    private Button btnGetAll;
    private ArrayList<User> userList;

    public MainLayout(){
        listView = new ListView<String>();
        btnGetAll = new Button("Get Users");
        btnGetAll.setOnAction(event -> {
            setList();
        });

        add(btnGetAll,7,0);
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
                System.out.println("gui" + userList.toString());
                for (int i = 0; i < userList.size(); i++) {
                    listView.getItems().add(i,"Userlost" + i);
                }
            }
        });
    }

}
