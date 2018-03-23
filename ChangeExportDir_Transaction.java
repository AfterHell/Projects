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
public class ChangeExportDir_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String oldExportDir;
    String newExportDir;
    
    public ChangeExportDir_Transaction(CourseSiteGenerator initApp, String oldExportDir, String newExportDir) 
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.oldExportDir = oldExportDir;
        this.newExportDir = newExportDir;
    }

    @Override
    public void doTransaction() 
    {                
        workSpace.getExportDirectoryLabel().setText(newExportDir);
        data.setCourseExportDirectory(newExportDir);
        gui.getFileController().setExportDir(newExportDir);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        workSpace.getExportDirectoryLabel().setText(oldExportDir);
        data.setCourseExportDirectory(oldExportDir); 
        gui.getFileController().setExportDir(oldExportDir);
        gui.getFileController().markAsEdited(gui); 
    }
}
