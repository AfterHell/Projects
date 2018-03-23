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

import tam.data.Team;
/**
 *
 * @author chenj
 */
public class AddTeam_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String name;
    String color;;
    String textColor;
    String link;
    
    String teamToString;
    
    public AddTeam_Transaction(CourseSiteGenerator initApp, String name, String color, String textColor, String link) 
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
    }

    @Override
    public void doTransaction() 
    {         
        data.addTeam(name, color, textColor, link);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.deleteTeam(teamToString);
        gui.getFileController().markAsEdited(gui); 
    }
}
