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
import tam.data.Recitation;
import java.util.ArrayList;

/**
 *
 * @author chenj
 */
public class EditTA_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;

    String oldName;
    String oldEmail;
    String newName;
    String newEmail;
    
    ArrayList<Recitation> oldRecitations;
    ArrayList<Recitation> newRecitations;
    
    public EditTA_Transaction(CourseSiteGenerator initApp, String oldName, String oldEmail, String newName, String newEmail,
                                ArrayList<Recitation> oldRecitations, ArrayList<Recitation> newRecitations)
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.oldName = oldName;
        this.oldEmail = oldEmail;
        this.newName = newName;
        this.newEmail = newEmail;
        
        this.oldRecitations = oldRecitations;
        this.newRecitations = newRecitations;
    }

    @Override
    public void doTransaction() 
    {
        // update the existing TA in THE DATA
        data.updateTA(oldName, newName, newEmail);
              
        data.getRecitations().clear();
        data.getRecitations().addAll(newRecitations);
        
        // CLEAR THE TEXT FIELDS
        workSpace.getNameTextField().setText("");
        workSpace.getEmailTextField().setText("");
            
        // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
        workSpace.getNameTextField().requestFocus();

        // WE'VE CHANGED STUFF
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        // update the existing TA in THE DATA
        data.updateTA(newName, oldName, oldEmail);
               
        data.getRecitations().clear();
        data.getRecitations().addAll(oldRecitations);
        
        // CLEAR THE TEXT FIELDS
        workSpace.getNameTextField().setText(newName);
        workSpace.getEmailTextField().setText(newEmail);
            
        // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
        workSpace.getNameTextField().requestFocus();

        gui.getFileController().markAsEdited(gui); 
    }
}

