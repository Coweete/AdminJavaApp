package com.essence.Gui;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-25.
 */
public class InfoScene extends Stage {

    private GridPane paneTextInfo;
    private GridPane paneTextField;
    private GridPane paneButtons;
    private TextField textFieldFirstName = new TextField("Firstname");
    private TextField textFieldLastName = new TextField("Lastname");
    private TextField textFieldRfid = new TextField("RfidKey");
    private TextField textFieldId = new TextField("Id");


    public InfoScene(){
        paneTextField.add(textFieldFirstName,0,1);
        paneTextField.add(textFieldLastName,0,2);
        paneTextField.add(textFieldRfid,0,3);
        paneTextField.add(textFieldId,0,4);

        
    }
}
