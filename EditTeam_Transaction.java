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
import java.util.ArrayList;
import tam.data.Student;
import tam.data.Team;
/**
 *
 * @author chenj
 */
public class EditTeam_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    //OLD
    String oldName;
    String oldColor;
    String oldTextColor;
    String oldLink;
    String oldTeamToString;
    
    //NEW
    String newName;
    String newColor;
    String newTextColor;
    String newLink;
    String newTeamToString;
    
    
    ArrayList<Student> oldStudents;
    ArrayList<Student> newStudents;
    
    public EditTeam_Transaction(    CourseSiteGenerator initApp, 
                                    String oldName, String oldColor, String oldTextColor, String oldLink, 
                                    String newName, String newColor, String newTextColor, String newLink,
                                    ArrayList<Student> oldStudents, ArrayList<Student> newStudents) 
    {
        app = initApp;
        gui = app.getGUI();
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        
        //OLD
        this.oldName = oldName;
        this.oldColor = oldColor;
        this.oldTextColor = oldTextColor;
        this.oldLink = oldLink;
    
        this.oldTeamToString = new Team(oldName, oldColor, oldTextColor, oldLink).toString();
        
        //NEW
        this.newName = newName;
        this.newColor = newColor;
        this.newTextColor = newTextColor;
        this.newLink = newLink;
    
        this.newTeamToString = new Team(newName, newColor, newTextColor, newLink).toString();
        
        this.oldStudents = oldStudents;
        this.newStudents = newStudents;
    }

    @Override
    public void doTransaction() 
    {
        data.updateTeam(oldTeamToString, newName, newColor, newTextColor, newLink);
        
        System.out.println(newStudents);
        data.getStudents().clear();
        data.getStudents().addAll(newStudents);
        
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        data.updateTeam(newTeamToString, oldName, oldColor, oldTextColor, oldLink);
        
        data.getStudents().clear();
        data.getStudents().addAll(oldStudents);
        
        gui.getFileController().markAsEdited(gui); 
    }
}

