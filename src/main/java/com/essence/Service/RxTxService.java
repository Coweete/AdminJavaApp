package com.essence.Service;

import com.essence.Controller.AppController;
import com.essence.helper.AppSerialPortEventListener;
import de.root1.rxtxrebundled.App;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 * Created by jonatan on 2016-04-27.
 */
public class RxTxService {

    private String compPort = "COM3";
    private String piPort = "/dev/ttyACM0";
    private static Boolean usesPi = false;
    private static final Logger log = LoggerFactory.getLogger(SpringService.class);
    private AppSerialPortEventListener appSerialPortEventListener;
    private AppController ctrl;

    SerialPort serialPort;
    /**
     * The port we're normally going to use.
     */
    private ArrayList<String> PORT_NAMES = new ArrayList<>();
    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;


    public void initialize() {

        PORT_NAMES.add("/dev/tty.usbserial-A9007UX1");
        PORT_NAMES.add("/dev/ttyUSB0");
        for (String portName : PORT_NAMES) {
            log.info("portname " + portName);
        }


        log.info("find portname");
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            appSerialPortEventListener.setSerialPort(serialPort);
            serialPort.notifyOnDataAvailable(true);
            serialPort.addEventListener(appSerialPortEventListener);
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        ctrl.setScanStatus("Scan ur card");
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }

    }


    /**
     * Returns the Serialport
     *
     * @return serialport
     */
    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * Lets the user set the event handler that is going too be used
     *
     * @param eventHandler An piSerialportEventlistener
     */
    public void setEventHandler(AppSerialPortEventListener eventHandler) {
        this.appSerialPortEventListener = eventHandler;
    }

    public void setCompPort(String compPort) {
        this.compPort = compPort;
        PORT_NAMES.add(this.compPort);
        System.out.println("COmp port " + this.compPort);
    }

    public void setCtrl(AppController ctrl){
        this.ctrl = ctrl;
    }

}


