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
import tam.data.Schedule;
/**
 *
 * @author chenj
 */
public class EditSchedule_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    //OLD
    String year;
    String month;
    String day;
    
    String type;
    String title;
    String topic;
    
    String time;
    String link;
    String criteria;
    
    String scheduleString;
    
    //NEW
    String newYear;
    String newMonth;
    String newDay;
    
    String newType;
    String newTitle;
    String newTopic;
    
    String newTime;
    String newLink;
    String newCriteria;
    
    String newScheduleString;
    
    public EditSchedule_Transaction(CourseSiteGenerator initApp, 
                                    String type, String year, String month, String day, 
                                    String time, String title, String topic, String link, String criteria,
                                    
                                    String newType, String newYear, String newMonth, String newDay, 
                                    String newTime, String newTitle, String newTopic, String newLink, String newCriteria) 
    {
        app = initApp;
        gui = app.getGUI();
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        
        //OLD
        this.year = year;
        this.month = month;
        this.day = day;
    
        this.type = type;
        this.title = title;
        this.topic = topic;
        this.time = time;
        this.link =link;
        this.criteria = criteria;
    
        this.scheduleString = new Schedule(type, year, month, day, time, title, topic, link, criteria).toString();
        
        //NEW
        this.newYear = newYear;
        this.newMonth = newMonth;
        this.newDay = newDay;
    
        this.newType = newType;
        this.newTitle = newTitle;
        this.newTopic = newTopic;
        this.newTime = newTime;
        this.newLink = newLink;
        this.newCriteria = newCriteria;
        
        this.newScheduleString = new Schedule(newType, newYear, newMonth, newDay, newTime, newTitle, newTopic, newLink, newCriteria).toString();
    }

    @Override
    public void doTransaction() 
    {
        data.updateSchedule(scheduleString, 
                            newType, newYear, newMonth, newDay, 
                            newTime, newTitle, newTopic, newLink, newCriteria);
               
        gui.getFileController().markAsEdited(gui);        
    }

    @Override
    public void undoTransaction()
    {
        data.updateSchedule(newScheduleString, 
                            type, year, month, day, 
                            time, title, topic, link, criteria);
                    
        gui.getFileController().markAsEdited(gui); 
    }
}

