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

/**
 *
 * @author chenj
 */
public class EditRecitation_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;

    String oldSection;
    String oldInstructor;
    String oldDayOrTime;
    String oldLocation;
    String oldTA1;
    String oldTA2;
    
    String newSection;
    String newInstructor;
    String newDayOrTime;
    String newLocation;
    String newTA1;
    String newTA2;
    
    public EditRecitation_Transaction(CourseSiteGenerator initApp, 
                                        String oldSection, String oldInstructor, String oldDayOrTime, String oldLocation, String oldTA1, String oldTA2,
                                        String newSection, String newInstructor, String newDayOrTime, String newLocation, String newTA1, String newTA2) 
    {
        app = initApp;
        gui = app.getGUI();
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        
        this.oldSection = oldSection;
        this.oldInstructor = oldInstructor;
        this.oldDayOrTime = oldDayOrTime;
        this.oldLocation = oldLocation;
        this.oldTA1 = oldTA1;
        this.oldTA2 = oldTA2;
    
        this.newSection = newSection;
        this.newInstructor = newInstructor;
        this.newDayOrTime = newDayOrTime;
        this.newLocation = newLocation;
        this.newTA1 = newTA1;
        this.newTA2 = newTA2;
    }

    @Override
    public void doTransaction() 
    {
        data.updateRecitation(oldSection, newSection, newInstructor, newDayOrTime, newLocation, newTA1, newTA2);
               
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        // update the existing TA in THE DATA
        data.updateRecitation(newSection, oldSection, oldInstructor, oldDayOrTime, oldLocation, oldTA1, oldTA2);
                    
        gui.getFileController().markAsEdited(gui); 
    }
}

