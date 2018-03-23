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
import tam.data.Student;

/**
 *
 * @author chenj
 */
public class EditStudent_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    //OLD
    String oldFirstName;
    String oldLastName;
    String oldTeam;
    String oldRole;
    String oldStudentToString;
    
    //NEW
    String newFirstName;
    String newLastName;
    String newTeam;
    String newRole;
    String newStudentToString;
    
    public EditStudent_Transaction( CourseSiteGenerator initApp, 
                                    String oldFirstName, String oldLastName, String oldTeam, String oldRole, 
                                    String newFirstName, String newLastName, String newTeam, String newRole) 
    {
        app = initApp;
        gui = app.getGUI();
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        
        //OLD
        this.oldFirstName = oldFirstName;
        this.oldLastName = oldLastName;
        this.oldTeam = oldTeam;
        this.oldRole = oldRole;
    
        this.oldStudentToString = new Student(oldFirstName, oldLastName, oldTeam, oldRole).toString();
        
        //NEW
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newTeam = newTeam;
        this.newRole = newRole;
    
        this.newStudentToString = new Student(newFirstName, newLastName, newTeam, newRole).toString();
    }

    @Override
    public void doTransaction() 
    {
        data.updateStudent(oldStudentToString, newFirstName, newLastName, newTeam, newRole);
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        data.updateStudent(newStudentToString, oldFirstName, oldLastName, oldTeam, oldRole);
        gui.getFileController().markAsEdited(gui); 
    }
}

