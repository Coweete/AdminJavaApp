package com.essence.Controller;

import com.essence.Gui.InfoScene;
import com.essence.Gui.LoginLayout;
import com.essence.Gui.MainLayout;
import com.essence.Gui.SetUpScene;
import com.essence.Model.Account;
import com.essence.Model.User;
import com.essence.Service.RxTxService;
import com.essence.Service.SpringService;
import com.essence.helper.AppSerialPortEventListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


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
    private Account currentUser;
    private MainLayout mainLayout = new MainLayout();
    private String[] tings;
    private String usbPort;
    private String ip;
    private SetUpScene settingsview = new SetUpScene();
    private InfoScene infoScene = new InfoScene();
    private RxTxService rxTxService = new RxTxService();
    private AppSerialPortEventListener eventListener = new AppSerialPortEventListener();


    public void setSpringService(SpringService springService){
        this.springService = springService;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    //Sätter ip och portnummer
    public void settings(String[] tings) {
        this.ip = tings[0] + tings[1];
        this.usbPort = tings[2];
    }

    // visar setup view
    public void setUpView(AppController controller) {
        settingsview.display(controller);

    }


    //Kallar på login
    public void newLogin(String userName, String passWord) {
        springService.login(userName,passWord);
    }

    /**
     * Testar med rxtx av/på
     */
    public void testRxTx() {
        rxTxService.setEventHandler(eventListener);
        rxTxService.setCompPort("COM5");
        log.info("Före tråd");
        //rxTxService.initialize();
        //rxTxService.readCard();
    }

    public Account[] getAllUsers() {
        //// TODO: 2016-05-03 Fix this shit plzzzz
        return null;
    }
}
