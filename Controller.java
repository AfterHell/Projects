package tam.workspace;

import static djf.settings.AppPropertyType.ABOUT_TITLE;
import static djf.settings.AppPropertyType.ABOUT_MESSAGE;
import static djf.settings.AppStartupConstants.PATH_EXPORT_IMAGES;
import djf.ui.AppGUI;
import static tam.CourseSiteGeneratorProperties.*;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.VerificationDialogSingleton;
import java.io.File;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import properties_manager.PropertiesManager;
import tam.CourseSiteGenerator;
import tam.data.Data;
import tam.data.TeachingAssistant;

import jtps.jTPS;
import jtps.jTPS_Transaction;
import tam.CourseSiteGeneratorProperties;
import javafx.stage.FileChooser;

import tam.data.Recitation;
import tam.data.Schedule;
import tam.data.Student;
import tam.data.Team;

import java.time.LocalDate;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import static djf.settings.AppStartupConstants.NAME_FROM_FILE;

import static djf.settings.AppStartupConstants.PATH_SITE_TEMPLATE;

public class Controller 
{
    CourseSiteGenerator app;
    static jTPS jTPS = new jTPS(); 
    public Controller(CourseSiteGenerator initApp) {app = initApp;}
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    //TAB1
    public void handleChangeSubjectComboBox(String newSubject)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseSubject(newSubject);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    public void handleChangeNumberComboBox(String newNumber)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseNumber(newNumber);
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleChangeSemesterComboBox(String newSemester)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseSemester(newSemester);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    public void handleChangeYearComboBox(String newYear)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseYear(newYear);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    public void handleChangeTitleTextField(String newTitle)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseTitle(newTitle);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    public void handleChangeInstrNameTextField(String newInstrName)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseInstructorName(newInstrName);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    public void handleChangeInstrHomeTextField(String newInstrHome)
    {
        Data data = (Data)app.getDataComponent();
        data.setCourseInstructorHome(newInstrHome);
        app.getGUI().getFileController().markAsEdited(app.getGUI());
    }
    
    
    public void handleChangeExportDir()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String oldExportDir = workspace.getExportDirectoryLabel().getText();
        String newExportDir = "";
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select The Export Directory");
        chooser.setInitialFileName(NAME_FROM_FILE); //THE DEFAULT NAME OF THE FILE TO BE EXPORTED IS public_html
        
        File selectedDirectory = chooser.showSaveDialog(app.getGUI().getWindow());

        newExportDir = selectedDirectory.getAbsolutePath();
        
        jTPS_Transaction changeExportDirTransaction = new ChangeExportDir_Transaction(app, oldExportDir, newExportDir);
        jTPS.addTransaction(changeExportDirTransaction);
    }
    public void handleChangeTemplateDir()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select The Template Directory");
        chooser.setInitialDirectory(new File(PATH_SITE_TEMPLATE));
        
        File selectedDirectory = chooser.showDialog(app.getGUI().getWindow());
        
        data.getSitePages().clear();
        
        for(String s: selectedDirectory.list())
        {
            if(s.contains("index.html"))
            {
                this.handleAddIndexPage();
            }
            if(s.contains("syllabus.html"))
            {
                this.handleAddSyllabusPage();
            }
            if(s.contains("schedule.html"))
            {
                this.handleAddSchedulePage();
            }
            if(s.contains("hws.html"))
            {
                this.handleAddHWsPage();
            }
            if(s.contains("projects.html"))
            {
                this.handleAddProjectsPage();
            } 
        }
        
        
        String siteTemplateDir = selectedDirectory.getAbsolutePath();
        


        
       
        //FOR TEMPLATE DIRECTORY
        app.getGUI().getFileController().setExportFromDir(siteTemplateDir);
        workspace.getSiteTemplateDirectoryLabel().setText(siteTemplateDir);
        data.updateSiteTemplateDirectory(siteTemplateDir);
        this.markWorkAsEdited();
    }
    
    
    public void handleAddIndexPage()
    {
        Data data = (Data) app.getDataComponent();
        data.addSitePage(true, "Home", "index.html", "HomeBuilder.js");
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleAddSyllabusPage()
    {
        Data data = (Data) app.getDataComponent();
        data.addSitePage(true, "Syllabus", "syllabus.html", "SyllabusBuilder.js");
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleAddSchedulePage()
    {
        Data data = (Data) app.getDataComponent();
        data.addSitePage(true, "Schedule", "schedule.html", "ScheduleBuilder.js");
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleAddHWsPage()
    {
        Data data = (Data) app.getDataComponent();
        data.addSitePage(true, "HWs", "hws.html", "HWsBuilder.js");
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleAddProjectsPage()
    {
        Data data = (Data) app.getDataComponent();
        data.addSitePage(true, "Projects", "projects.html", "ProjectsBuilder.js");
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    
    
    public void handleChangeBannerSchoolImage()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String oldDir = workspace.getBannerSchoolImageDir();
        String newDir = "";
        
        //GET FILE AND ITS URI, THEN CONVERT THE URI TO STRING AS THE NEWDIR
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_EXPORT_IMAGES));

        File toFile = fc.showOpenDialog(app.getGUI().getWindow());
        newDir = toFile.toURI().toString();
        
        newDir = newDir.substring(newDir.lastIndexOf('/') + 1);
        newDir = "file:./images/" + newDir;

        jTPS_Transaction changeBannerSchoolImageTransaction = new ChangeBannerSchoolImage_Transaction(app, oldDir, newDir);
        jTPS.addTransaction(changeBannerSchoolImageTransaction);
    }
    public void handleChangeLeftFooterImage()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String oldDir = workspace.getLeftFooterImageDir();
        String newDir = "";
        
        //GET FILE AND ITS URI, THEN CONVERT THE URI TO STRING AS THE NEWDIR
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_EXPORT_IMAGES));

        File toFile = fc.showOpenDialog(app.getGUI().getWindow());
        newDir = toFile.toURI().toString();
        
        newDir = newDir.substring(newDir.lastIndexOf('/') + 1);
        newDir = "file:./images/" + newDir;

        jTPS_Transaction changeLeftFooterImageTransaction = new ChangeLeftFooterImage_Transaction(app, oldDir, newDir);
        jTPS.addTransaction(changeLeftFooterImageTransaction);
    }
    public void handleChangeRightFooterImage()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String oldDir = workspace.getrightFooterImageDir();
        String newDir = "";
        
        //GET FILE AND ITS URI, THEN CONVERT THE URI TO STRING AS THE NEWDIR
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(PATH_EXPORT_IMAGES));

        File toFile = fc.showOpenDialog(app.getGUI().getWindow());
        newDir = toFile.toURI().toString();
        
        newDir = newDir.substring(newDir.lastIndexOf('/') + 1);
        newDir = "file:./images/" + newDir;

        jTPS_Transaction changeRightFooterImageTransaction = new ChangeRightFooterImage_Transaction(app, oldDir, newDir);
        jTPS.addTransaction(changeRightFooterImageTransaction);
    }
    public void handleChangeStyleSheet(String styleSheet)
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        String newDir;
        
        if(styleSheet == null)
        {
            newDir = "";
        }
        else
        {
            newDir = styleSheet;
        }
        
        workspace.updateStyleSheetComboBox(newDir);
        data.updateStyleSheetDir(newDir);
        this.markWorkAsEdited();
    }
    
    
    
    
    
    //TAB2
    public void handleAddTA() 
    {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        if(workspace.getAddButton().getText().equals(props.getProperty(CourseSiteGeneratorProperties.ADD_BUTTON_TEXT.toString())))//we can only add TA when the button is "Add TA"
        {
            
            TextField nameTextField = workspace.getNameTextField();
            TextField emailTextField = workspace.getEmailTextField();
            
            String name = nameTextField.getText();
            String email = emailTextField.getText();
        
            //--------------------Email Validator---------------------------------------------
            EmailValidator emailValidator1 = new EmailValidator();
            boolean emailFormatValid = emailValidator1.validate(email);
        
            // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
            Data data = (Data) app.getDataComponent();
        
            // DID THE USER NEGLECT TO PROVIDE A TA NAME?
            if (name.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
            }
            // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
            else if (email.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));                        
            }
            //Requirement1: is the format of the email valid?
            else if(!emailFormatValid)
            {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE),props.getProperty(INVALID_TA_EMAIL_MESSAGE));
            }
            // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
            else if (data.containsTA(name, email)) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
            }
            // EVERYTHING IS FINE, ADD A NEW TA
            else 
            {
                //create an addTATransaction of type jTPS_Transaction
                //jTPS_Transaction = interface = apparent type
                //AddTA_Transaction = class implements the interface = actual type
                jTPS_Transaction addTATransaction = new AddTA_Transaction(app, name, email);
                
                jTPS.addTransaction(addTATransaction);
            }
        }
    }
    public void handleEditTA()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Data data = (Data)app.getDataComponent();
        
        TableView taTable = workspace.getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String oldName = ta.getName();
            String oldEmail = ta.getEmail();
            
            TextField nameTextField = workspace.getNameTextField();
            TextField emailTextField = workspace.getEmailTextField();
            nameTextField.setText(oldName);
            emailTextField.setText(oldEmail);
            
            
            ObservableList recitations = data.getRecitations();
            
            ArrayList<Recitation> oldRecitations = new ArrayList<Recitation>();
            for (Object recitation : recitations) 
            {
                oldRecitations.add((Recitation) recitation);
            }
                    
                    
                    
                    
            //-----------NOW WE GET THE OLD NAME AND EMAIL----------------------------------
            //change "Add TA"button to "Update TA" button and set a controller on it
            //here we disable the addTA functionality temporarily by changing the text on the addTA button
            //and enable the editTA functionality
            workspace.getAddButton().setText(props.getProperty(UPDATE_TA_BUTTON_TEXT));

            //AFTER PRESSING THE UPDATE TA BUTTON
            workspace.getAddButton().setOnMouseClicked(e->
            {
                //if the old name and email are changed, then we will get new version of them
                String newName = nameTextField.getText();
                String newEmail = emailTextField.getText();
            
                //--------------------Email Validator---------------------------------------------
                EmailValidator emailValidator1 = new EmailValidator();
                boolean emailFormatValid = emailValidator1.validate(newEmail);
            

                // DID THE USER NEGLECT TO PROVIDE A TA NAME?
                if (newName.isEmpty()) {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));            
                }
                // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
                else if (newEmail.isEmpty()) {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE)); 
                }
                //Requirement1: is the format of the email valid?
                else if(!emailFormatValid)
                {                       
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE),props.getProperty(INVALID_TA_EMAIL_MESSAGE));
                }
                // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
                else if (data.containsOtherTA(oldName, oldEmail, newName, newEmail)) {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));                                    
                }
                // EVERYTHING IS FINE, ADD A NEW TA
                else 
                {
                    ArrayList<Recitation> newRecitations = new ArrayList<Recitation>();
                    for (Recitation recitation : oldRecitations) 
                    {
                        Recitation newRecitation = new Recitation(recitation.getSection(), recitation.getInstructor(), recitation.getDayOrTime(),
                                                                    recitation.getLocation(), recitation.getTA1(), recitation.getTA2());
                        if(recitation.getTA1().equals(oldName))
                        {
                            newRecitation.setTA1(newName);
                        }
                        if(recitation.getTA2().equals(oldName))
                        {
                            newRecitation.setTA2(newName);
                        }
                        newRecitations.add(newRecitation);
                    }
                   
                    jTPS_Transaction editTATransaction = new EditTA_Transaction(app, oldName, oldEmail, newName, newEmail, oldRecitations,  newRecitations);
                    jTPS.addTransaction(editTATransaction);
                }
            });
            
        }   
    
    }
    public void handleClear()
    {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        //the following two lines of codes enable the functionality of adding TA
        //and temporarily remove the functionality of editing TA on addTA button
        workspace.getAddButton().setText(props.getProperty(ADD_BUTTON_TEXT));
        workspace.getAddButton().setOnMouseClicked(e->
        {});//toggle the updateTA back to Add TA by removing the event handler on mouseClicked
        
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        
        // CLEAR THE TEXT FIELDS
        nameTextField.setText("");
        emailTextField.setText("");
            
        // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
        nameTextField.requestFocus();
        // WE'VE CHANGED STUFF
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleKeyPress(KeyCode code) 
    {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) 
        {
            this.handleDeleteTA();
        }
    }
    public void handleDeleteTA() 
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean delete = false;
        VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
        verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
        // AND NOW GET THE USER'S SELECTION
        String selection = verificationDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(VerificationDialogSingleton.YES)) 
        {
            delete = true;
        } 
        else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
        {
            delete = false;
        }
        else
        {
            delete = false;
        }         
        
        if(delete == true)
        {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        TableView taTable = workspace.getTATable();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA AND REMOVE IT
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            String taEmail = ta.getEmail(); //used for redo transaction to add it back

            HashMap<String, StringProperty> temp = new HashMap<String, StringProperty>();
            //go through the whole officeHours to find the panes with taNames
            for (String cellKey : data.getOfficeHours().keySet()) 
            {
                String cellText = data.getOfficeHours().get(cellKey).getValue();
                // IF the cell contains the taName
                if (cellText.contains(taName)) 
                {
                    temp.put(cellKey, data.getOfficeHours().get(cellKey));
                } 
            } 
            
            ArrayList<Recitation> oldRecitations = new ArrayList<Recitation>();
            ObservableList recitations = data.getRecitations();
            for (Object recitation : recitations) 
            {
                oldRecitations.add((Recitation) recitation);
            }
            //create an addTATransaction of type jTPS_Transaction
            //jTPS_Transaction = interface = apparent type
            //AddTA_Transaction = class implements the interface = actual type
            jTPS_Transaction deleteTATransaction = new DeleteTA_Transaction(app, taName, taEmail, temp, oldRecitations);
            jTPS.addTransaction(deleteTATransaction);
        }  
        }
    }
    
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA
            TeachingAssistant ta = (TeachingAssistant)selectedItem;
            String taName = ta.getName();
            String cellKey = pane.getId();

            jTPS_Transaction cellToggleTransaction = new CellToggle_Transaction(app, taName, cellKey);
            jTPS.addTransaction(cellToggleTransaction);
            
        }
    }
    void handleGridCellMouseExited(Pane pane) {
        
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String cellKey = workspace.getCellKey(pane);
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        
        //get column and row from the cellKey of current cell pane
        String[] parts = cellKey.split("_");
        int column = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        
        pane.setStyle(props.getProperty(BORDER_COLOR_000000));

        if(column != 2)//we need to reset highlight cells on the left to current cell
        {
            for(int i = 2; i <= column - 1; i++)
            {
                int c = i;
                int r = row;
                workspace.getTACellPane(workspace.buildCellKey(c,r)).setStyle(props.getProperty(BORDER_COLOR_000000));
            }
        }
        
        if(row != 1)//we need to reset highlight cells on the top to current cell
        {
            for(int i = 1; i <= row - 1; i++)
            {
                int c = column;
                int r = i;
                workspace.getTACellPane(workspace.buildCellKey(c,r)).setStyle(props.getProperty(BORDER_COLOR_000000));
            }
        }
    }
    void handleGridCellMouseEntered(Pane pane) {
        
         // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String cellKey = workspace.getCellKey(pane);
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        
        //get column and row from the cellKey of current cell pane
        String[] parts = cellKey.split("_");
        int column = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        
        pane.setStyle(props.getProperty(BORDER_COLOR_FFFF00));
        
        if(column != 2)//we need to highlight cells on the left to current cell
        {
            for(int i = 2; i <= column - 1; i++)
            {
                int c = i;
                int r = row;
                workspace.getTACellPane(workspace.buildCellKey(c,r)).setStyle(props.getProperty(BORDER_COLOR_ADFF2F));
            }
        }
        
        if(row != 1)//we need to highlight cells on the top to current cell
        {
            for(int i = 1; i <= row - 1; i++)
            {
                int c = column;
                int r = i;
                workspace.getTACellPane(workspace.buildCellKey(c,r)).setStyle(props.getProperty(BORDER_COLOR_ADFF2F));
            }
        }
    }    
    public void handleEditTime(){
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        // used to change the fixed time
        Data data = (Data)app.getDataComponent();
        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        int startTime = 9;//initially the startTime is 9 am
        int endTime = 20; //initially the endTime is 8 pm
        
        String startTimeString = (String)workspace.getStartTimeBox().getValue();
        String endTimeString = (String)workspace.getEndTimeBox().getValue();
        
        if(startTimeString != null)
        {
            startTime = 0; 
            startTime += workspace.getStartTimeBox().getItems().indexOf(workspace.getStartTimeBox().getValue());
        }

        if(endTimeString != null)
        {
            endTime = 0; 
            endTime += workspace.getEndTimeBox().getItems().indexOf(workspace.getEndTimeBox().getValue());
        }
        //now we have the Integer startTime and endTime in the current combobox
        
        //To make sure user can't pick a time such that startTime > endTime
        if(startTime+1 > endTime) //this means startTime is after the current endTime
        {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_TIME_TITLE), props.getProperty(INVALID_TIME_MESSAGE));                                    
                
            //This line of code set the selected option in the startTime comboBox
            //to be the item of index of (startHour in TAData) in startTime comboBox items
            workspace.getStartTimeBox().setValue(workspace.getStartTimeBox().getItems().get(data.getStartHour()));
            workspace.getEndTimeBox().setValue(workspace.getEndTimeBox().getItems().get(data.getEndHour()));
        }
        else//startTime <= endTime
        {
            boolean delete = false;// by default we don't delete
            boolean overlapping = false; // by default there is no overlapping
            int oldStartTime = data.getStartHour();
            int oldEndTime = data.getEndHour();
            int newStartTime = startTime;
            int newEndTime = endTime;
            
            int topDifference = (newStartTime - oldStartTime)*2;    //how many rows will be deleted from top
            int buttomDifference = (oldEndTime - newEndTime)*2;     //how many rows will be deleted from buttom
            int lastRowIndex = (oldEndTime - oldStartTime) * 2;     //the last last row's index
            
            if(newStartTime > oldStartTime)//Case1: there might be overlapping cases
            {
                //go through the whole current officeHours without headers' information
                for(String key : data.getOfficeHours().keySet())
                {
                    String[] temp = key.split("_");
                    int col = Integer.parseInt(temp[0]);            //get col in col_row and change it to int
                    int row = Integer.parseInt(temp[1]);            //get row in col_row and change it to int
                    if(col > 1 && row > 0 && row <= topDifference)   //don't count headers
                    {
                        if(!data.getOfficeHours().get(key).getValue().equals(""))//if there is any cell that's not empty
                            overlapping = true;
                    }
                    
                }
            }
            if(newEndTime < oldEndTime)//Case2: there might be overlapping cases
            {
                //go through the whole current officeHours without headers' information
                for(String key : data.getOfficeHours().keySet())
                {
                    String[] temp = key.split("_");
                    int col = Integer.parseInt(temp[0]);            //get col in col_row and change it to int
                    int row = Integer.parseInt(temp[1]);            //get row in col_row and change it to int
                    if((col > 1) && (row > lastRowIndex - buttomDifference) && row <= lastRowIndex)   //don't count headers
                    {
                        if(!data.getOfficeHours().get(key).getValue().equals(""))//if there is any cell that's not empty
                            overlapping = true;
                    }
                }
            }
            
            if(overlapping) //if there is overlapping, verify the user whether delete or not
            {
                try
                {
                    //always create a new dialog instead of using an instance to avoid the case that
                    //the instance first time saves the YES and second time when hitting EXIT it will return true
                    VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
                    verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
                    // AND NOW GET THE USER'S SELECTION
                    String selection = verificationDialog.getSelection();

                    // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
                    if (selection.equals(VerificationDialogSingleton.YES)) 
                    {
                        delete = true;
                    } 
                    else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
                    {
                        delete = false;
                    }
                    else
                        delete = false;
                }
                catch(Exception e)//if the user quit the dialog by hitting the exit button
                {
                    workspace.getStartTimeBox().setValue(workspace.getStartTimeBox().getItems().get(data.getStartHour()));
                    workspace.getEndTimeBox().setValue(workspace.getEndTimeBox().getItems().get(data.getEndHour()));
                }
                
                if(delete == true) //yes, delete them
                {
                    //save the data which are in the deleted rows to a new HashMap and put them back while undoing
                    HashMap<String, StringProperty> topOfficeHours = new HashMap();
                    HashMap<String, StringProperty> bottomOfficeHours = new HashMap();
                    for(String key : data.getOfficeHours().keySet())
                    {
                        StringProperty oldProp = data.getOfficeHours().get(key);
                        //separate the key into integer
                        String[] temp = key.split("_");
                        int col = Integer.parseInt(temp[0]);
                        int row = Integer.parseInt(temp[1]); //get row in col_row and change it to int
                        //dont include those empty props
                        if(col > 1 && row > 0 && row <= topDifference)//dont include headers, start from col2 and row1
                        {
                            String newKey = col+"_"+row;
                            topOfficeHours.put(newKey, oldProp);
                        }
                        if((col > 1) && (row > lastRowIndex - buttomDifference) && row <= lastRowIndex)
                        {
                            String newKey = col+"_"+row;
                            bottomOfficeHours.put(newKey, oldProp);
                        }
                    }
                    
                    //case 1: No overlapping and be sure to delete, set transaction when data is changed
                    jTPS_Transaction editTimeTransaction = new EditTime_Transaction(app, oldStartTime, oldEndTime, startTime, endTime, topOfficeHours, bottomOfficeHours);
                    jTPS.addTransaction(editTimeTransaction);
                }
                else        //if cancel or no, do not change anything and reset the time in the comboBox
                {
                    workspace.getStartTimeBox().setValue(workspace.getStartTimeBox().getItems().get(data.getStartHour()));
                    workspace.getEndTimeBox().setValue(workspace.getEndTimeBox().getItems().get(data.getEndHour()));
                }
            }
            else // if there is no overlapping, update the time
            {
                /*data.updateStartTime(startTime); 
                data.updateEndTime(endTime);
                */
                //save the data in the deleted rows and put them back while undoing
                HashMap<String, StringProperty> topOfficeHours = new HashMap();
                    HashMap<String, StringProperty> bottomOfficeHours = new HashMap();
                    for(String key : data.getOfficeHours().keySet())
                    {
                        StringProperty oldProp = data.getOfficeHours().get(key);
                        //separate the key into integer
                        String[] temp = key.split("_");
                        int col = Integer.parseInt(temp[0]);
                        int row = Integer.parseInt(temp[1]); //get row in col_row and change it to int
                        //dont include those empty props
                        if(col > 1 && row > 0 && row <= topDifference)//dont include headers, start from col2 and row1
                        {
                            String newKey = col+"_"+row;
                            topOfficeHours.put(newKey, oldProp);
                        }
                        if((col > 1) && (row > lastRowIndex - buttomDifference) && row <= lastRowIndex)
                        {
                            String newKey = col+"_"+row;
                            bottomOfficeHours.put(newKey, oldProp);
                        }
                    }

                //case 2: With overlapping, set transaction when data is changed
                jTPS_Transaction editTimeTransaction = new EditTime_Transaction(app, oldStartTime, oldEndTime, startTime, endTime, topOfficeHours, bottomOfficeHours);
                jTPS.addTransaction(editTimeTransaction);
            }  
        }   
        markWorkAsEdited();
    }
    
    
    
    
    
    //TAB3
    public void handleAddRecitation()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        if(workspace.getAddRecitationButton().getText().equals(props.getProperty(CourseSiteGeneratorProperties.ADD_RECITATION_BUTTON.toString())))
        {
            String section = workspace.getSectionTextField().getText();
            String instructor = workspace.getInstructorTextField().getText();
            String dayOrTime = workspace.getDayOrTimeTextField().getText();
            String location = workspace.getLocationTextField().getText();
            String TA1 = workspace.getTA1ComboBox().getValue().toString();
            String TA2 = workspace.getTA2ComboBox().getValue().toString();
        
            jTPS_Transaction addRecitationTransaction = new AddRecitation_Transaction(app, section, instructor, dayOrTime, location, TA1, TA2);
            jTPS.addTransaction(addRecitationTransaction);
        }
    }
    public void handleClearEditRecitation()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        workspace.getAddRecitationButton().setText(props.getProperty(ADD_RECITATION_BUTTON));
        workspace.getAddRecitationButton().setOnMouseClicked(e->{});//toggle the updateTA back to Add TA by removing the event handler on mouseClicked

        // WE'VE CHANGED STUFF
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleDeleteRecitation()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean delete = false;
        VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
        verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
        // AND NOW GET THE USER'S SELECTION
        String selection = verificationDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(VerificationDialogSingleton.YES)) 
        {
            delete = true;
        } 
        else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
        {
            delete = false;
        }
        else
        {
            delete = false;
        }         
        
        if(delete == true)
        {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView recitationTableView = workspace.getRecitationTableView();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = recitationTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA AND REMOVE IT
            Recitation recitation = (Recitation)selectedItem;
            
            String section = recitation.getSection();
            String instructor = recitation.getInstructor();
            String dayOrTime = recitation.getDayOrTime();
            String location = recitation.getLocation();
            String TA1 = recitation.getTA1();
            String TA2 = recitation.getTA2();
        
            jTPS_Transaction deleteRecitationTransaction = new DeleteRecitation_Transaction(app, section, instructor, dayOrTime, location, TA1, TA2);
            jTPS.addTransaction(deleteRecitationTransaction);
        }  
        }
    }
    public void handleEditRecitation()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView recitationTableView = workspace.getRecitationTableView();
        
        Object selectedItem = recitationTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) 
        {
            // GET THE TA
            Recitation recitation = (Recitation)selectedItem;
            
            String oldSection = recitation.getSection();
            String oldInstructor = recitation.getInstructor();
            String oldDayOrTime = recitation.getDayOrTime();
            String oldLocation = recitation.getLocation();
            String oldTA1 = recitation.getTA1();
            String oldTA2 = recitation.getTA2();
            
            workspace.getSectionTextField().setText(oldSection);
            workspace.getInstructorTextField().setText(oldInstructor);
            workspace.getDayOrTimeTextField().setText(oldDayOrTime);
            workspace.getLocationTextField().setText(oldLocation);
            workspace.getTA1ComboBox().setValue(oldTA1);
            workspace.getTA2ComboBox().setValue(oldTA2);
            
            //-----------NOW WE GET THE OLD NAME AND EMAIL----------------------------------
            //change "Add TA"button to "Update TA" button and set a controller on it
            //here we disable the addTA functionality temporarily by changing the text on the addTA button
            //and enable the editTA functionality
            workspace.getAddRecitationButton().setText(props.getProperty(UPDATE_RECITATION_BUTTON));

            //AFTER PRESSING THE UPDATE TA BUTTON
            workspace.getAddRecitationButton().setOnMouseClicked(e->
            {
                //if the old name and email are changed, then we will get A new version of them
                String newSection = workspace.getSectionTextField().getText();
                String newInstructor = workspace.getInstructorTextField().getText();
                String newDayOrTime = workspace.getDayOrTimeTextField().getText();
                String newLocation = workspace.getLocationTextField().getText();
                String newTA1 = workspace.getTA1ComboBox().getValue().toString();
                String newTA2 = workspace.getTA2ComboBox().getValue().toString();
            
                this.markWorkAsEdited();
                jTPS_Transaction editRecitationTransaction = new EditRecitation_Transaction
                                                            (app, oldSection, oldInstructor, oldDayOrTime, oldLocation, oldTA1, oldTA2,
                                                                  newSection, newInstructor, newDayOrTime, newLocation, newTA1, newTA2);
                jTPS.addTransaction(editRecitationTransaction);
            });
        }   
    }
    public void handleKeyPressRecitation(KeyCode code) 
    {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) 
        {
            handleDeleteRecitation();
        }
    }
    
    
    
    
    //TAB4
    public void handleEditStartingMonday(LocalDate oldMonday, LocalDate newMonday)
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        LocalDate currentFriday = workspace.getEndingDatePicker().getValue();
        try
        {
        if(newMonday.isAfter(currentFriday) || !newMonday.getDayOfWeek().toString().equals("MONDAY")) 
        {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_DATE_TITLE), props.getProperty(INVALID_DATE_MESSAGE));                                    
                
            workspace.getStartingDatePicker().setValue(oldMonday);
        }
        else
        {
            workspace.getStartingDatePicker().setValue(newMonday);
            
            String year = String.valueOf(newMonday.getYear());
            String month = String.valueOf(newMonday.getMonthValue());
            String day = String.valueOf(newMonday.getDayOfMonth());
            
            data.setStartingMondayYear(year);
            data.setStartingMondayMonth(month);
            data.setStartingMondayDay(day);
            
            this.markWorkAsEdited();
        }  
        }
        catch(NullPointerException e){}
    }
    
    public void handleEditEndingFriday(LocalDate oldFriday, LocalDate newFriday)
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        LocalDate currentMonday = workspace.getStartingDatePicker().getValue();
        try
        {
        if(newFriday.isBefore(currentMonday) || !newFriday.getDayOfWeek().toString().equals("FRIDAY")) 
        {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_DATE_TITLE), props.getProperty(INVALID_DATE_MESSAGE));                                    
                
            workspace.getEndingDatePicker().setValue(oldFriday);
        }
        else
        {
            workspace.getEndingDatePicker().setValue(newFriday);
            
            String year = String.valueOf(newFriday.getYear());
            String month = String.valueOf(newFriday.getMonthValue());
            String day = String.valueOf(newFriday.getDayOfMonth());
            
            data.setEndingFridayYear(year);
            data.setEndingFridayMonth(month);
            data.setEndingFridayDay(day);
            
            this.markWorkAsEdited();
        } 
        }
        catch(NullPointerException e){}
    }
    
    
    public void handleAddSchedule()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        if(workspace.getAddScheduleButton().getText().equals(props.getProperty(CourseSiteGeneratorProperties.ADD_SCHEDULE_BUTTON.toString())))
        {
            String year = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getYear());
            String month = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getMonthValue());
            String day = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getDayOfMonth());
        
            String type = workspace.getTypeOfScheduleComboBox().getValue().toString();
            
            String time = workspace.getTimeOfScheduleTextField().getText();
            String title = workspace.getTitleOfScheduleTextField().getText();
            String topic = workspace.getTopicOfScheduleTextField().getText();
            String link = workspace.getLinkOfScheduleTextField().getText();
            String criteria = workspace.getCriteriaOfScheduleTextField().getText();
            
            jTPS_Transaction addScheduleTransaction = new AddSchedule_Transaction(  app, 
                                                                                    type, year, month, day, 
                                                                                    time, title, topic, link, criteria);
            jTPS.addTransaction(addScheduleTransaction);
        }
    }
    public void handleClearEditSchedule()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        workspace.getAddScheduleButton().setText(props.getProperty(ADD_SCHEDULE_BUTTON));
        workspace.getAddScheduleButton().setOnMouseClicked(e->{});//toggle the updateTA back to Add TA by removing the event handler on mouseClicked

        // WE'VE CHANGED STUFF
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleDeleteSchedule()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean delete = false;
        VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
        verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
        // AND NOW GET THE USER'S SELECTION
        String selection = verificationDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(VerificationDialogSingleton.YES)) 
        {
            delete = true;
        } 
        else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
        {
            delete = false;
        }
        else
        {
            delete = false;
        }         
        
        if(delete == true)
        {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView scheduleTableView = workspace.getSchduleTableView();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = scheduleTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA AND REMOVE IT
            Schedule schedule = (Schedule)selectedItem;
            
            String type = schedule.getType();
            String year = schedule.getYear();
            String month = schedule.getMonth();
            String day = schedule.getDay();
            
            String time = schedule.getTime();
            String title = schedule.getTitle();
            String topic = schedule.getTopic();
            String link = schedule.getLink();
            String criteria = schedule.getCriteria();
            
            jTPS_Transaction deleteScheduleTransaction = new DeleteSchedule_Transaction(app, 
                                                                                        type, year, month, day, 
                                                                                        time, title, topic, link, criteria);
            jTPS.addTransaction(deleteScheduleTransaction);
        }    
        }
    }
    public void handleEditSchedule()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView scheduleTableView = workspace.getSchduleTableView();
        
        Object selectedItem = scheduleTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) 
        {
            // GET THE TA
            Schedule schedule = (Schedule)selectedItem;
            
            String type = schedule.getType();
            String year = schedule.getYear();
            String month = schedule.getMonth();
            String day = schedule.getDay();
            
            String time = schedule.getTime();
            String title = schedule.getTitle();
            String topic = schedule.getTopic();
            String link = schedule.getLink();
            String criteria = schedule.getCriteria();
            
            LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

            workspace.getTypeOfScheduleComboBox().setValue(type);
            workspace.getDateOfScheduleDatePicker().setValue(localDate);
            workspace.getTimeOfScheduleTextField().setText(time);
            workspace.getTitleOfScheduleTextField().setText(title);
            workspace.getTopicOfScheduleTextField().setText(topic);
            workspace.getLinkOfScheduleTextField().setText(link);
            workspace.getCriteriaOfScheduleTextField().setText(criteria);
            
            //-----------NOW WE GET THE OLD NAME AND EMAIL----------------------------------
            
            workspace.getAddScheduleButton().setText(props.getProperty(EDIT_SCHEDULE_BUTTON));

            //AFTER PRESSING THE UPDATE TA BUTTON
            workspace.getAddScheduleButton().setOnMouseClicked(e->
            {
                String newYear = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getYear());
                String newMonth = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getMonthValue());
                String newDay = Integer.toString(workspace.getDateOfScheduleDatePicker().getValue().getDayOfMonth());
        
                String newType = workspace.getTypeOfScheduleComboBox().getValue().toString();
            
                String newTime = workspace.getTimeOfScheduleTextField().getText();
                String newTitle = workspace.getTitleOfScheduleTextField().getText();
                String newTopic = workspace.getTopicOfScheduleTextField().getText();
                String newLink = workspace.getLinkOfScheduleTextField().getText();
                String newCriteria = workspace.getCriteriaOfScheduleTextField().getText();
                
                jTPS_Transaction editScheduleTransaction = 
                        new EditSchedule_Transaction
                        (app, 
                        type, year, month, day, 
                        time, title, topic, link, criteria,
                                    
                        newType, newYear, newMonth, newDay, 
                        newTime, newTitle, newTopic, newLink, newCriteria);
                
                jTPS.addTransaction(editScheduleTransaction);
            });
        }   
    }
    public void handleKeyPressSchedule(KeyCode code) 
    {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) 
        {
            handleDeleteSchedule();
        }
    }
    
    
    
    
    //TAB5
    public void handleAddTeam()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
        if(workspace.getAddTeamButton().getText().equals(props.getProperty(CourseSiteGeneratorProperties.ADD_TEAM_BUTTON.toString())))
        {
            String name = workspace.getNameOfTeamsTextField().getText();
            
            String color = workspace.getColorColorPicker().getValue().toString();
            color = color.substring(2,8);
            color = "#"+color;
            
            String textColor = workspace.getTextColorColorPicker().getValue().toString();
            textColor = textColor.substring(2,8);
            textColor = "#"+textColor;
            
            String link = workspace.getLinkOfTeamsTextField().getText();
            
            jTPS_Transaction addTeamTransaction = new AddTeam_Transaction(app, name, color, textColor, link);
            jTPS.addTransaction(addTeamTransaction);
        }
    }
    public void handleClearEditTeam()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        workspace.getAddTeamButton().setText(props.getProperty(ADD_TEAM_BUTTON));
        workspace.getAddTeamButton().setOnMouseClicked(e->{});

        // WE'VE CHANGED STUFF
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleDeleteTeam()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean delete = false;
        VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
        verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
        // AND NOW GET THE USER'S SELECTION
        String selection = verificationDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(VerificationDialogSingleton.YES)) 
        {
            delete = true;
        } 
        else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
        {
            delete = false;
        }
        else
        {
            delete = false;
        }         
        
        if(delete == true)
        {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        TableView teamTableView = workspace.getTeamTableView();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA AND REMOVE IT
            Team team = (Team)selectedItem;
            
            String name = team.getTeamName();
            
            String color = team.getTeamColor();
            
            String textColor = team.getTeamTextColor();
            
            String link = team.getTeamLink();
            
            
            
            
            ArrayList<Student> oldStudents = new ArrayList<Student>();
            ObservableList students = data.getStudents();
            for (Object student : students) 
            {
                oldStudents.add((Student) student);
            }
            
            
            
            
            
            
            jTPS_Transaction deleteTeamTransaction = 
                         new DeleteTeam_Transaction(app, name, color, textColor, link, oldStudents);
            
            jTPS.addTransaction(deleteTeamTransaction);
        }  
        }
    }
    public void handleEditTeam()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TableView teamTableView = workspace.getTeamTableView();
        
        Object selectedItem = teamTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) 
        {
            Team team = (Team)selectedItem;
            
            String oldName = team.getTeamName();
            String oldColor = team.getTeamColor();
            String oldTextColor = team.getTeamTextColor();
            String oldLink = team.getTeamLink();
            
            workspace.getNameOfTeamsTextField().setText(oldName);
            workspace.getColorColorPicker().setValue(Color.web(oldColor));
            workspace.getTextColorColorPicker().setValue(Color.web(oldTextColor));
            workspace.getLinkOfTeamsTextField().setText(oldLink);
            
            ArrayList<Student> oldStudents = new ArrayList<Student>();
            ObservableList students = data.getStudents();
            for (Object student : students) 
            {
                oldStudents.add((Student) student);
            }
            
            //-----------NOW WE GET THE OLD NAME AND EMAIL----------------------------------
            
            workspace.getAddTeamButton().setText(props.getProperty(UPDATE_TEAM_BUTTON));

            //AFTER PRESSING THE UPDATE TA BUTTON
            workspace.getAddTeamButton().setOnMouseClicked(e->
            {
                String newName = workspace.getNameOfTeamsTextField().getText();
            
                String newColor = workspace.getColorColorPicker().getValue().toString();
                newColor = newColor.substring(2,8);
                newColor = "#"+ newColor;
            
                String newTextColor = workspace.getTextColorColorPicker().getValue().toString();
                newTextColor = newTextColor.substring(2,8);
                newTextColor = "#"+newTextColor;
            
                String newLink = workspace.getLinkOfTeamsTextField().getText();
            
                ArrayList<Student> newStudents = new ArrayList<Student>();
                for (Student student : oldStudents) 
                {
                    Student newStudent = new Student(student.getFirstName(), student.getLastName(), student.getTeam(), student.getRole());
                    if(student.getTeam().equals(oldName))
                    {
                        newStudent.setTeam(newName);
                    }
                    newStudents.add(newStudent);
                }
                    
                jTPS_Transaction editTeamTransaction = new EditTeam_Transaction
                                                       (app, 
                                                        oldName, oldColor, oldTextColor, oldLink,
                                                        newName, newColor, newTextColor, newLink,
                                                        oldStudents, newStudents);
                
                jTPS.addTransaction(editTeamTransaction);
            });
        }   
    }
    public void handleKeyPressTeam(KeyCode code) 
    {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) 
        {
            handleDeleteTeam();
        }
    }
    
    
    
    
    
    public void handleAddStudent()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        Data data = (Data) app.getDataComponent();
        
        if(workspace.getAddStudentButton().getText().equals(props.getProperty(CourseSiteGeneratorProperties.ADD_STUDENT_BUTTON.toString())))
        {
            String firstName = workspace.getFirstNameTextField().getText();
            String lastName = workspace.getLastNameTextField().getText();
            String team = workspace.getTeamComboBox().getValue().toString();
            String role = workspace.getRoleTextField().getText();
            
            if (firstName.isEmpty() || lastName.isEmpty()) 
            {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(STUDENT_NAME_INVALID_TITLE), props.getProperty(STUDENT_NAME_INVALID_MESSAGE));            
            }
            else if (data.containsStudent(firstName,lastName)) 
            {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(STUDENT_NAME_INVALID_TITLE), props.getProperty(STUDENT_NAME_INVALID_MESSAGE));                                    
            }
            else
            {
                jTPS_Transaction addStudentTransaction = new AddStudent_Transaction(app, firstName, lastName, team, role);
                jTPS.addTransaction(addStudentTransaction);
            }
        }
    }
    public void handleClearEditStudent()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        workspace.getAddStudentButton().setText(props.getProperty(ADD_STUDENT_BUTTON));
        workspace.getAddStudentButton().setOnMouseClicked(e->{});

        // WE'VE CHANGED STUFF
        app.getGUI().getFileController().markAsEdited(app.getGUI()); 
    }
    public void handleDeleteStudent()
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean delete = false;
        VerificationDialogSingleton verificationDialog = VerificationDialogSingleton.getSingleton();
        verificationDialog.show(props.getProperty(VERIFICATION_TO_DELETE_TITLE), props.getProperty(VERIFICATION_TO_DELETE_MESSAGE));
                    
        // AND NOW GET THE USER'S SELECTION
        String selection = verificationDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(VerificationDialogSingleton.YES)) 
        {
            delete = true;
        } 
        else if (selection.equals(VerificationDialogSingleton.CANCEL)) 
        {
            delete = false;
        }
        else
        {
            delete = false;
        }         
        
        if(delete == true)
        {
        // GET THE TABLE
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView studentTableView = workspace.getStudentTableView();
            
        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = studentTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            // GET THE TA AND REMOVE IT
            Student student = (Student)selectedItem;
            
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String team = student.getTeam();
            String role = student.getRole();
            
            jTPS_Transaction deleteStudentTransaction = 
                         new DeleteStudent_Transaction(app, firstName, lastName, team, role);
            
            jTPS.addTransaction(deleteStudentTransaction);
        }  
        }
    }
    public void handleEditStudent()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Data data = (Data) app.getDataComponent();
        TableView studentTableView = workspace.getStudentTableView();
        
        Object selectedItem = studentTableView.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) 
        {
            Student student = (Student)selectedItem;
            
            String oldFirstName = student.getFirstName();
            String oldLastName = student.getLastName();
            String oldTeam = student.getTeam();
            String oldRole = student.getRole();
            
            workspace.getFirstNameTextField().setText(oldFirstName);
            workspace.getLastNameTextField().setText(oldLastName);
            workspace.getTeamComboBox().setValue(oldTeam);
            workspace.getRoleTextField().setText(oldRole);
            
            //-----------NOW WE GET THE OLD NAME AND EMAIL----------------------------------
            
            workspace.getAddStudentButton().setText(props.getProperty(UPDATE_STUDENT_BUTTON));

            //AFTER PRESSING THE UPDATE TA BUTTON
            workspace.getAddStudentButton().setOnMouseClicked(e->
            {
                
                String newFirstName = workspace.getFirstNameTextField().getText();
                String newLastName = workspace.getLastNameTextField().getText();
                String newTeam = workspace.getTeamComboBox().getValue().toString();
                String newRole = workspace.getRoleTextField().getText();
            
                if (newFirstName.isEmpty() || newLastName.isEmpty()) 
                {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(STUDENT_NAME_INVALID_TITLE), props.getProperty(STUDENT_NAME_INVALID_MESSAGE));            
                }
                else if (data.containsOtherStudent(newFirstName, newLastName, oldFirstName, oldLastName)) 
                {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    dialog.show(props.getProperty(STUDENT_NAME_INVALID_TITLE), props.getProperty(STUDENT_NAME_INVALID_MESSAGE));                                    
                }
                else
                {
                    jTPS_Transaction editStudentTransaction = 
                        new EditStudent_Transaction
                        (app, oldFirstName, oldLastName, oldTeam, oldRole,
                              newFirstName, newLastName, newTeam, newRole);
                
                    jTPS.addTransaction(editStudentTransaction);
                }
            });
        }   
    }
    public void handleKeyPressStudent(KeyCode code) 
    {
        // DID THE USER PRESS THE DELETE KEY?
        if (code == KeyCode.DELETE) 
        {
            handleDeleteStudent();
        }
    }
    
    
    
     
    
    
    public void handleUndo() 
    {
        System.out.println(jTPS.toString());
        jTPS.undoTransaction();
    }
    public void handleRedo()
    {
        System.out.println(jTPS.toString());
        jTPS.doTransaction();
    }
    public void handleAbout()
    {
        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        dialog.show(props.getProperty(ABOUT_TITLE), props.getProperty(ABOUT_MESSAGE));
    }
}