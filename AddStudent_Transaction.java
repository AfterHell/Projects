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

import tam.data.Student;

/**
 *
 * @author chenj
 */
public class AddStudent_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String firstName;
    String lastName;;
    String team;
    String role;
    
    String studentToString;
    
    public AddStudent_Transaction(CourseSiteGenerator initApp, String firstName, String lastName, String team, String role) 
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.role = role;
        
        this.studentToString = new Student(firstName, lastName, team, role).toString();
    }

    @Override
    public void doTransaction() 
    {         
        data.addStudent(firstName, lastName, team, role);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.deleteStudent(studentToString);
        gui.getFileController().markAsEdited(gui); 
    }
}
