/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tam.workspace;

import djf.ui.AppGUI;
import java.util.ArrayList;
import jtps.jTPS_Transaction;
import tam.CourseSiteGenerator;
import tam.data.Data;
import tam.data.Student;
import tam.data.Team;

/**
 *
 * @author chenj
 */
public class DeleteTeam_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String name;
    String color;
    String textColor;
    String link;
    
    String teamToString;
    
    ArrayList<Student> oldStudents;
    
    public DeleteTeam_Transaction(CourseSiteGenerator initApp, 
                                  String name, String color, String textColor, String link, ArrayList<Student> oldStudents)
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
        
        this.teamToString = new Team(name, color, textColor, link).toString();
        
        this.oldStudents = oldStudents;
    }

    @Override
    public void doTransaction() 
    {           
        data.deleteTeam(teamToString);
        data.deleteTeamFromStudentsByName(name);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.addTeam(name, color, textColor, link);
        
        data.getStudents().clear();
        data.getStudents().addAll(oldStudents);
        
        gui.getFileController().markAsEdited(gui); 
    }
}
