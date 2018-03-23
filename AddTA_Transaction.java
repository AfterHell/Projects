/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tam.workspace;

import jtps.jTPS_Transaction;
import tam.CourseSiteGenerator;
import tam.data.Data;

import djf.ui.AppGUI;
/**
 *
 * @author chenj
 */
public class AddTA_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    final private String name;
    final private String email;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;
    public AddTA_Transaction(CourseSiteGenerator initApp, String name, String email) //total 6 stuff we need
    {
        app = initApp;
        this.name = name;
        this.email = email;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
    }

    @Override
    public void doTransaction() 
    {
        // ADD THE NEW TA TO THE DATA
        data.addTA(name, email);
                
        // CLEAR THE TEXT FIELDS
        workSpace.getNameTextField().setText("");
        workSpace.getEmailTextField().setText("");
            
        // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
        workSpace.getNameTextField().requestFocus();      
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.removeTA(name);
        gui.getFileController().markAsEdited(gui); 
    }
}

