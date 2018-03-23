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
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import tam.data.TeachingAssistant;

/**
 *
 * @author chenj
 */
public class EditTime_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    Controller controller;
    AppGUI gui;

    final int oldStartTime;
    final int oldEndTime;
    final int newStartTime;
    final int newEndTime;
    HashMap<String, StringProperty> topOfficeHours;
    HashMap<String, StringProperty> bottomOfficeHours;
    public EditTime_Transaction(CourseSiteGenerator initApp, int oldStartTime, int oldEndTime, int startTime, int endTime, 
                                HashMap<String, StringProperty> topOfficeHours, HashMap<String, StringProperty> bottomOfficeHours) //total 7 stuff we need
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.oldStartTime = oldStartTime;
        this.oldEndTime = oldEndTime;
        this.newStartTime = startTime;
        this.newEndTime = endTime;
        
        this.topOfficeHours = topOfficeHours;
        this.bottomOfficeHours = bottomOfficeHours;
    }

    @Override
    public void doTransaction() 
    {
        data.updateStartTime(newStartTime); 
        data.updateEndTime(newEndTime);
                    
        // WE'VE CHANGED STUFF
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        data.updateStartTime(oldStartTime); 
        data.updateEndTime(oldEndTime);
                
        if(newStartTime > oldStartTime) //case 1: top is deleted, we set them back no matter if there is data or not
        {
            for(String key : topOfficeHours.keySet())
            {
                data.getOfficeHours().get(key).set(topOfficeHours.get(key).getValue());
            }
        }
        
        if(newEndTime < oldEndTime)     //case 2: bottom is deleted, we set them back no matter if there is data or not
        {
            for(String key : bottomOfficeHours.keySet())
            {
                data.getOfficeHours().get(key).set(bottomOfficeHours.get(key).getValue());
            }
        }
       
        gui.getFileController().markAsEdited(gui); 
    }
}

