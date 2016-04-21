package com.essence.Controller;

import com.essence.Gui.LoginLayout;
import com.essence.Gui.MainLayout;
import com.essence.Model.User;
import com.essence.Service.SpringService;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by jonatan on 2016-04-20.
 */
@Component
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    private SpringService springService;
    private LoginLayout layout;
    private String ipHome = "http://195.178.224.74:44344/users";
    private String ipEdu = "http://172.16.2.12:44344/users";
    private Stage primaryStage;
    private MainLayout mainLayout = new MainLayout();


    public void login() {
        springService.setIp(ipEdu);
        springService.getUser();
        primaryStage.close();
        primaryStage.setScene(new Scene(mainLayout,400,400));
        primaryStage.show();
    }

    public void loginHome() {
        springService.setIp(ipHome);
        springService.getUser();
        mainLayout.setController(this);
        primaryStage.close();
        primaryStage.setScene(new Scene(mainLayout,400,400));
        primaryStage.show();
    }

    public void setSpringService(SpringService springService){
        this.springService = springService;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ArrayList<User> getUserList() {
        System.out.println("controller");
        ArrayList<User> test = springService.getAllUsers();
        log.info("Controller " + test.toString());
        return test;
    }
}