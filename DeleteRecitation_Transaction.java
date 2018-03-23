/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tam.workspace;

import djf.ui.AppGUI;
import jtps.jTPS_Transaction;
import tam.CourseSiteGenerator;
import tam.data.Data;

/**
 *
 * @author chenj
 */
public class DeleteRecitation_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String section;
    String instructor;
    String dayOrTime;
    String location;
    String TA1;
    String TA2;
        
    public DeleteRecitation_Transaction(CourseSiteGenerator initApp, String section, String instructor, String dayOrTime, String location, String TA1, String TA2) 
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.section = section;
        this.instructor = instructor;
        this.dayOrTime = dayOrTime;
        this.location = location;
        this.TA1 = TA1;
        this.TA2 = TA2;
    }

    @Override
    public void doTransaction() 
    {           
        data.deleteRecitation(section);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.addRecitation(section, instructor, dayOrTime, location, TA1, TA2);
        gui.getFileController().markAsEdited(gui); 
    }
}
