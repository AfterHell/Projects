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
import tam.data.Schedule;
/**
 *
 * @author chenj
 */
public class DeleteSchedule_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String year;
    String month;
    String day;
    
    String type;
    String date;
    String title;
    String topic;
    
    String time;
    String link;
    String criteria;
    
    String scheduleString;
    
    public DeleteSchedule_Transaction(  CourseSiteGenerator initApp, 
                                        String type, String year, String month, String day, 
                                        String time, String title, String topic, String link, String criteria) 
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.year = year;
        this.month = month;
        this.day = day;
        
        this.type = type;
        this.date = month+"/"+day+"/"+year;
        this.title = title;
        this.topic = topic;
        
        this.time = time;
        this.link = link;
        this.criteria = criteria;
        
        this.scheduleString = new Schedule(type, year, month, day, time, title, topic, link, criteria).toString();
    }

    @Override
    public void doTransaction() 
    {           
        data.deleteSchedule(scheduleString);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        data.addSchedule(type, year, month, day, time, title, topic, link, criteria);
        gui.getFileController().markAsEdited(gui); 
    }
}
