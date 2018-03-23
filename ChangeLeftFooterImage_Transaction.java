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
public class ChangeLeftFooterImage_Transaction implements jTPS_Transaction 
{
    CourseSiteGenerator app;
    Data data;
    Workspace workSpace;
    AppGUI gui;
    
    String oldDir;
    String newDir;
    
    public ChangeLeftFooterImage_Transaction(CourseSiteGenerator initApp, String oldDir, String newDir) 
    {
        app = initApp;
        data = (Data)app.getDataComponent();
        workSpace = (Workspace) app.getWorkspaceComponent();
        gui = app.getGUI();
        
        this.oldDir = oldDir;
        this.newDir = newDir;
    }

    @Override
    public void doTransaction() 
    {                
        workSpace.updateLeftFooterImage(newDir);
        data.updateLeftFooterImageDir(newDir);
        gui.getFileController().markAsEdited(gui); 
    }

    @Override
    public void undoTransaction() 
    {
        workSpace.updateLeftFooterImage(oldDir);
        data.updateLeftFooterImageDir(oldDir); 
        gui.getFileController().markAsEdited(gui); 
    }
}
