package com.essence.Controller;

import com.essence.Gui.UpdateScene;
import com.essence.Gui.LoginLayout;
import com.essence.Gui.MainLayout;
import com.essence.Gui.SetUpScene;
import com.essence.Model.Account;
import com.essence.Model.RfidKey;
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
    private UpdateScene updateScene = new UpdateScene();
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
    public void login(String userName, String passWord) {
        currentUser = springService.login(userName,passWord);
        primaryStage.close();
        mainLayout.setController(this);
        primaryStage.setScene(new Scene(mainLayout,500,500));
        primaryStage.show();
    }

    /**
     * Testar med rxtx av/på
     */
    public void testRxTx() {
        rxTxService.setCtrl(this);
        rxTxService.setEventHandler(eventListener);
        rxTxService.setCompPort("COM5");
        eventListener.setController(this);
        log.info("Före tråd");
        rxTxService.initialize();
    }

    /**
     * Metoden kommer att kalla på serverservicen som hämtar ner alla användare och sedan
     * kommer den att retunera det till gui
     * @return alla användare
     */
    public Account[] getAllUsers() {
        return springService.getAllUsers(currentUser.getEncryptedUserCredentials());
    }

    public void showInfo(Account account) {
        updateScene.display(account,this);
    }

    public void updateUser(Account account) {
        springService.updateUser(account);
    }

    public void addAccount(Account newUser) {
        springService.addUser(newUser);
    }

    public String createCryptedPass(String pass){

        return pass;
    }

    public void deleteAccount(Account account) {
        springService.deleteUser(account);
    }

    public void setNewRfid(String newRfid) {
        RfidKey key = new RfidKey(newRfid);
        updateScene.updateRfid(key);
    }

    public void setScanStatus(String scanStatus) {
        updateScene.setScanStatusText(scanStatus);
    }
}
