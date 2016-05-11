package com.essence.helper;

import com.essence.Controller.AppController;
import com.essence.Service.RxTxService;
import com.essence.Service.SpringService;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by seb on 2016-04-12.
 */

@Component
public class AppSerialPortEventListener implements gnu.io.SerialPortEventListener {


    private SerialPort serialPort;
    private BufferedReader input;
    private AppController controller;

    /**
     * This method will take care of an serialportevent and then return it to the controller
     *
     * @param oEvent serialportevent
     */
    @Override
    public void serialEvent(SerialPortEvent oEvent) {
        System.out.println("Got request from Arduino");
        if(oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE){
         try {
             String inputLine = input.readLine();
             System.out.println(inputLine);
             System.out.println("hello team");
             serialPort.close();
             controller.setNewRfid(inputLine);

         }catch (Exception e){
             System.out.println(e.toString());
         }
        }
    }

    /**
     * This method will set the local serialport to a destinated one
     *
     * @param serialPort the port we need to use
     */
    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
        try {
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will connect the controller object to this class
     *
     * @param controller the controller
     */
    public void setController(AppController controller) {
        this.controller = controller;
    }
}
