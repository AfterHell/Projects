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
public class CellToggle_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;
    
    final String name;
    final String cellKey;
    
    public CellToggle_Transaction(CourseSiteGenerator initApp, String name, String cellKey) //total 2 stuff we need
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.name = name;
        this.cellKey = cellKey;   
    }

    @Override
    public void doTransaction() 
    {
        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        data.toggleTAOfficeHours(cellKey, name);
            
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        data.toggleTAOfficeHours(cellKey, name);
            
        gui.getFileController().markAsEdited(gui); 
    }
}

