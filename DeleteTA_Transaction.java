/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tam.workspace;

/**
 *
 * @author chenj
 */
import jtps.jTPS_Transaction;
import tam.CourseSiteGenerator;
import tam.data.Data;

import djf.ui.AppGUI;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import tam.data.Recitation;
import java.util.ArrayList;
/**
 *
 * @author chenj
 */
public class DeleteTA_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;
    
    final String name;
    final String email;
    final HashMap<String, StringProperty> officeHours;
    
    ArrayList<Recitation> oldRecitations;
    
    public DeleteTA_Transaction(CourseSiteGenerator initApp, String name, String email, HashMap<String, StringProperty> temp, ArrayList<Recitation> oldRecitations)
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.name = name;
        this.email = email;
        this.officeHours = temp;
        
        this.oldRecitations = oldRecitations;
    }

    @Override
    public void doTransaction() 
    {
        data.removeTA(name);   
        data.deleteTAFromRecitationsByName(name);
        
        // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
                HashMap<String, Label> labels = workSpace.getOfficeHoursGridTACellLabels();
                for (Label label : labels.values()) 
                {
                    if (label.getText().equals(name)
                    || (label.getText().contains(name + "\n"))
                    || (label.getText().contains("\n" + name))) {
                        data.removeTAFromCell(label.textProperty(), name);
                    }
                }        
        // WE'VE CHANGED STUFF
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        data.addTA(name, email);
        
        data.getRecitations().clear();
        data.getRecitations().addAll(oldRecitations);
        
        for(String cellKey : officeHours.keySet())
        {
            data.toggleTAOfficeHours(cellKey, name);
        }
        gui.getFileController().markAsEdited(gui); 
    }
}

