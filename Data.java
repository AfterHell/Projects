package tam.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import djf.components.AppDataComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.beans.property.StringProperty;

import properties_manager.PropertiesManager;
import tam.CourseSiteGenerator;
import tam.CourseSiteGeneratorProperties;
import tam.workspace.Workspace;
import tam.file.TimeSlot;

/**
 * This class is for holding all the data required to be saved.
 *
 * @author Jia Chen
 */
public class Data implements AppDataComponent {

    CourseSiteGenerator app;

    //TAB1
    //1.1 COURSE INFO
    String courseSubject;
    String courseNumber;
    String courseSemester;
    String courseYear;
    String courseTitle;
    String courseInstructorName;
    String courseInstructorHome;
    String courseExportDirectory;

    //1.2 SITE TEMPLATE
    String courseTemplateDirectory;
    ObservableList<SitePage> sitePages;

    //1.3 PAGE STYLE
    String bannerImageDir;
    String leftImageDir;
    String rightImageDir;
    String styleSheetDir;
    
    ObservableList<String> styleSheets;
    //TAB2
    ObservableList<TeachingAssistant> teachingAssistants;

    HashMap<String, StringProperty> officeHours;

    ArrayList<TimeSlot> timeSlots;

    ArrayList<String> gridHeaders;

    int startHour;
    int endHour;

    //for the getNewOfficeHours method to know what's the lastest lastRowIndex
    int lastRowIndex = Integer.MAX_VALUE;

    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 20;

    //TAB3
    ObservableList<Recitation> recitations;
    ArrayList<Recitation> oldRecitations;
    ObservableList<String> recitationTAs;
    
    //TAB4
    String startingMondayYear;
    String startingMondayMonth;
    String startingMondayDay;
    
    String endingFridayYear;
    String endingFridayMonth;
    String endingFridayDay;
    
    ObservableList<Schedule> schedules;

    //TAB5
    ObservableList<Team> teams;

    ObservableList<Student> students;
    ObservableList<String> teamsOfStudents;

    /**
     * This constructor will setup the required data structures for use, but
     * will have to wait on the office hours grid, since it receives the
     * StringProperty objects from the Workspace.
     *
     * @param initApp The application this data manager belongs to.
     */
    public Data(CourseSiteGenerator initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        timeSlots = new ArrayList<TimeSlot>();

        //TAB1
        //1.1 COURSE INFO
        courseSubject = "";
        courseNumber = "";
        courseSemester = "";
        courseYear = "";
        courseTitle = "";
        courseInstructorName = "";
        courseInstructorHome = "";
        courseExportDirectory = "";

        //1.2 SITE TEMPLATE
        courseTemplateDirectory = "";
        sitePages = FXCollections.observableArrayList();

        //1.3 PAGE STYLE
        bannerImageDir = "";
        leftImageDir = "";
        rightImageDir = "";
        styleSheetDir = "";

        styleSheets = FXCollections.observableArrayList();
        
        //TAB2
        teachingAssistants = FXCollections.observableArrayList();

        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;

        officeHours = new HashMap();

        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProperties.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProperties.DAYS_OF_WEEK);

        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);

        //TAB3
        recitations = FXCollections.observableArrayList();
        oldRecitations = new ArrayList<Recitation>();
        recitationTAs = FXCollections.observableArrayList();
        
        //TAB4
        startingMondayMonth = "";
        startingMondayDay = "";
        endingFridayMonth = "";
        endingFridayDay = "";
        
        schedules = FXCollections.observableArrayList();

        //TAB5
        teams = FXCollections.observableArrayList();
        
        students = FXCollections.observableArrayList();
        teamsOfStudents = FXCollections.observableArrayList();
    }

    /**
     * Called each time new work is created or loaded, it resets all data and
     * data structures such that they can be used for new values.
     */
    @Override
    public void resetData() {
        //TAB1
        sitePages.clear();
        styleSheets.clear();
        
        //TAB2
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();

        //TAB3
        recitations.clear();
        oldRecitations.clear();
        recitationTAs.clear();;
        
        //TAB4
        schedules.clear();

        //TAB5
        teams.clear();
        students.clear();
    }

    //TAB1
    public String getCourseSubject() {
        return courseSubject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    public String getCourseInstructorHome() {
        return courseInstructorHome;
    }

    public String getCourseExportDirectory() {
        return courseExportDirectory;
    }
    
    
    public void setCourseSubject(String s) {courseSubject = s;}

    public void setCourseNumber(String s) {courseNumber = s;}

    public void setCourseSemester(String s) {courseSemester = s;}

    public void setCourseYear(String s) {courseYear = s;}

    public void setCourseTitle(String s) {courseTitle = s;}

    public void setCourseInstructorName(String s) {courseInstructorName = s;}

    public void setCourseInstructorHome(String s) {courseInstructorHome = s;}

    public void setCourseExportDirectory(String s) {courseExportDirectory = s;}

    public void updateCourseInfo(String courseSubject, String courseNumber, String courseSemester, String courseYear,
            String courseTitle, String courseInstructorName, String courseInstructorHome, String courseExportDirectory) {
        this.courseSubject = courseSubject;
        this.courseNumber = courseNumber;
        this.courseSemester = courseSemester;
        this.courseYear = courseYear;
        this.courseYear = courseYear;
        this.courseTitle = courseTitle;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorHome = courseInstructorHome;
        this.courseExportDirectory = courseExportDirectory;
    }

    public void updateSiteTemplateDirectory(String courseTemplateDirectory) {
        this.courseTemplateDirectory = courseTemplateDirectory;
    }

    public String getCourseTemplateDirectory() {return courseTemplateDirectory;}

    public ObservableList getSitePages() {
        return sitePages;
    }

    public String getBannerSchoolImageDir() {
        return bannerImageDir;
    }

    public String getLeftFooterImageDir() {
        return leftImageDir;
    }

    public String getRightFooterImageDir() {
        return rightImageDir;
    }

    public String getStyleSheetDir() {
        return styleSheetDir;
    }

    public void updateBannerSchoolImageDir(String dir) {
        bannerImageDir = dir;
    }

    public void updateLeftFooterImageDir(String dir) {
        leftImageDir = dir;
    }

    public void updateRightFooterImageDir(String dir) {
        rightImageDir = dir;
    }

    public void updateStyleSheetDir(String dir) {
        styleSheetDir = dir;
    }

    public void addSitePage(boolean isUsed, String navbarTitle, String fileName, String script) 
    {
        // MAKE THE TA
        SitePage sitePage = new SitePage(isUsed, navbarTitle, fileName, script);

        sitePages.add(sitePage);
        app.getGUI().getFileController().markAsEdited(app.getGUI());

        // SORT THE TAS
        Collections.sort(sitePages);
    }

    public void toggle_Site_Page_Used(String navbarTitle, boolean isSitePageUsed) {
        for (SitePage sitePage : sitePages) {
            if (navbarTitle.equals(sitePage.getNavbarTitle())) {
                sitePage.setIsUsed(isSitePageUsed);
            }
        }
    }

    public SitePage getSitePage(String navBarTitle) 
    {
        for (SitePage sitePage : sitePages) 
        {
            if (sitePage.getNavbarTitle().equals(navBarTitle)) 
            {
                return sitePage;
            }
        }
        return null;
    }
    
    public ObservableList getStyleSheets() {return styleSheets;}
    
    //TAB2
    public void addTA(String initName, String initEmail) 
    {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail, true);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) 
        {
            teachingAssistants.add(ta);
            recitationTAs.add(initName);
        }
        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }

    //settor methods
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    // ACCESSOR METHODS
    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }

    public ObservableList getTeachingAssistants() {
        return teachingAssistants;
    }

    public String getCellKey(int col, int row) {
        return col + "_" + row;
    }

    public StringProperty getCellTextProperty(int col, int row) {
        String cellKey = getCellKey(col, row);
        return officeHours.get(cellKey);
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }

    public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }

    public String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }

    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));//hour part
        int milHour = hour;
        if (/*hour < startHour*/time.contains("pm") && hour != 12) {
            milHour += 12;
        }
        row += (milHour - startHour) * 2;
        if (time.contains("_30")) {
            row += 1;
        }
        return getCellKey(col, row);
    }

    public TeachingAssistant getTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return ta;
            }
        }
        return null;
    }

    /**
     * This method is for giving this data manager the string property for a
     * given cell.
     */
    public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, prop);
    }

    /**
     * This method is for setting the string property for a given cell.
     */
    public void setGridProperty(ArrayList<ArrayList<StringProperty>> grid,
            int column, int row, StringProperty prop) {
        grid.get(row).set(column, prop);
    }

    private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;

        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();

        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        workspaceComponent.reloadOfficeHoursGrid(this);
    }

    //used to update the fixed startTime and endTime
    public void updateStartTime(int newStartHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        int difference = newStartHour - startHour;
        this.startHour = newStartHour;

        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        workspaceComponent.updateOfficeHoursGrid(this, difference, startHour, endHour);
        workspaceComponent.getStartTimeBox().setValue(workspaceComponent.getStartTimeBox().getItems().get(startHour));
    }

    //used to update the fixed startTime and endTime
    public void updateEndTime(int newEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        this.endHour = newEndHour;

        Workspace workspaceComponent = (Workspace) app.getWorkspaceComponent();
        workspaceComponent.updateOfficeHoursGrid(this, 0, startHour, endHour);//no difference
        workspaceComponent.getEndTimeBox().setValue(workspaceComponent.getEndTimeBox().getItems().get(endHour));

    }

    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }

    public boolean containsTA(String testName, String testEmail) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
            if (ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOtherTA(String oldName, String oldEmail, String newName, String newEmail) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(newName)) {
                if (!ta.getName().equals(oldName))//if the newName is different from the old TA's name, which means it's the same as other TA's name
                {
                    return true;
                }
            }
            if (ta.getEmail().equals(newEmail)) {
                if (!ta.getEmail().equals(oldEmail))//if the newEmail is different from the old TA's email, which means it's the same as other TA's email
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void toggle_TA_Undergraduated(String name, boolean isUndergraduated) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (name.equals(ta.getName())) {
                ta.setIsUndergraduated(isUndergraduated);
            }
        }
    }

    public void updateTA(String oldName, String newName, String newEmail) 
    {
        // make a updated TA instance
        TeachingAssistant updatedTA = new TeachingAssistant(newName, newEmail, true);

        //go through the ObserviableList to find the existingTA we want to update
        for (TeachingAssistant existingTA : teachingAssistants) 
        {
            if (existingTA.getName().equals(oldName))//we find it and we must find it
            {
                //set the ta in the index of existing TA to a new ta
                
                teachingAssistants.set(teachingAssistants.indexOf(existingTA), updatedTA);
                recitationTAs.set(recitationTAs.indexOf(oldName), newName);
                
                
                //go through the whole HashMap
                for (StringProperty cellProp : officeHours.values()) 
                {
                    String cellText = cellProp.getValue();
                    // IF IT ALREADY HAS THE TA, update IT
                    if (cellText.contains(oldName)) 
                    {
                        updateTAInCell(cellProp, oldName, newName);
                    }
                }
            }
        }
        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }

    public void removeTA(String name) 
    {
        for (TeachingAssistant ta : teachingAssistants) 
        {
            if (name.equals(ta.getName())) 
            {
                teachingAssistants.remove(ta);
                recitationTAs.remove(ta.getName());
                
                return;
            }
        }
    }
    
    
    

    public void addOfficeHoursReservation(String day, String time, String taName) 
    {
        try
        {
            String cellKey = getCellKey(day, time);
            toggleTAOfficeHours(cellKey, taName);
        }
        //FILESTEST: LOAD DATA FROM JSON TO A OFFICEHOURS FOR TEST ONLY
        //TESTSAVE: SAVE DATA FROM FILES TO OFFICEHOURS FOR TEST ONLY
        catch(NullPointerException e)
        {
           
            this.addTimeSlot(day, time, taName);
        }
    }

    /**
     * This function toggles the taName in the cell represented by cellKey.
     * Toggle means if it's there it removes it, if it's not there it adds it.
     */
    public void toggleTAOfficeHours(String cellKey, String taName) {

        // StringProperty cellProp = officeHours.get(cellKey);
        StringProperty cellProp = officeHours.get(cellKey);

        String cellText = cellProp.getValue();

        // IF IT ALREADY HAS THE TA, REMOVE IT
        if (cellText.contains(taName)) {
            removeTAFromCell(cellProp, taName);
        } // OTHERWISE ADD IT
        else if (cellText.length() == 0) {
            cellProp.setValue(taName);
        } else {
            cellProp.setValue(cellText + "\n" + taName);
        }
    }

    /**
     * This method removes taName from the office grid cell represented by
     * cellProp.
     */
    public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        } // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        } // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText);
        } // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }

    public void updateTAInCell(StringProperty cellProp, String oldName, String newName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        cellText = cellText.replace(oldName, newName);
        cellProp.setValue(cellText);
    }

    public HashMap<String, StringProperty> getNewOfficeHours(int difference, int startTime, int endTime) {
        int indexOfLastRow = (endTime - startTime) * 2;
        HashMap<String, StringProperty> newOfficeHours = new HashMap();
        for (String oldKey : officeHours.keySet()) {
            StringProperty oldProp = officeHours.get(oldKey);

            //make a new key for it and put them together into a new HashMap
            String[] temp = oldKey.split("_");
            int col = Integer.parseInt(temp[0]);
            int row = Integer.parseInt(temp[1]); //get row in col_row and change it to int
            if (col > 1 && row > 0)//dont include headers
            {
                row -= difference * 2;  //new start time - old start time = difference
                //if newLastRowIndex > oldLastRowIndex, don't make mapping for them
                //the row of new index cannot be larger than the last one
                if (row < 1 || row > indexOfLastRow || ((difference == 0) && (row > lastRowIndex))) {
                    //don't make this key
                } else //the key is valid
                {
                    String newKey = col + "_" + row;

                    if (!officeHours.get(oldKey).getValue().equals("")) {
                        System.out.println(newKey);
                    }

                    newOfficeHours.put(newKey, oldProp);
                }
            }

        }
        lastRowIndex = indexOfLastRow;
        return newOfficeHours;
    }

    /*
    only returns the current officeHours without headers' info into a new HashMap
     */
    public HashMap<String, StringProperty> getCurrentOfficeHours() {
        HashMap<String, StringProperty> newOfficeHours = new HashMap();
        for (String oldKey : officeHours.keySet()) {
            StringProperty oldProp = officeHours.get(oldKey);

            //make a old key for it and put them together into a new HashMap
            String[] temp = oldKey.split("_");
            int col = Integer.parseInt(temp[0]);
            int row = Integer.parseInt(temp[1]); //get row in col_row and change it to int
            //dont include those empty props
            if (col > 1 && row > 0 && !oldProp.getValue().equals(""))//dont include headers, start from col2 and row1
            {
                String newKey = col + "_" + row;
                newOfficeHours.put(newKey, oldProp);
            }
        }
        return newOfficeHours;
    }

    //TAB3
    public ObservableList getRecitations() {return recitations;}
    public ArrayList<Recitation> getOldRecitations() {return oldRecitations;}
    
    
    public void addRecitation(String section, String instructor, String dayOrTime, String location, String TA1, String TA2) 
    {
        Recitation recitation = new Recitation(section, instructor, dayOrTime, location, TA1, TA2);

        recitations.add(recitation);
        oldRecitations.add(recitation);
        Collections.sort(recitations);
    }
    
    public void updateRecitation(String oldSection, String newSection, String newInstructor, String newDayOrTime, String newLocation, String newTA1, String newTA2)
    {
        Recitation updatedRecitation = new Recitation(newSection, newInstructor, newDayOrTime, newLocation, newTA1, newTA2);

        for (Recitation existingRecitation : recitations) 
        {
            if (existingRecitation.getSection().equals(oldSection))//we find it and we must find it
            {
                recitations.set(recitations.indexOf(existingRecitation), updatedRecitation);
                oldRecitations.set(oldRecitations.indexOf(existingRecitation), updatedRecitation);
            }
        }
        // SORT THE TAS
        Collections.sort(recitations);
    }
    
    public void deleteRecitation(String section)
    {
        for (Recitation recitation : recitations) 
        {
            if (section.equals(recitation.getSection())) 
            {
                recitations.remove(recitation);
                oldRecitations.remove(recitation);
                return;
            }
        }
    }

    public ObservableList getRecitationTAs(){ return recitationTAs;}
    
    public void deleteTAFromRecitationsByName(String name)
    {
        ArrayList<Recitation> oldRecitations = new ArrayList<Recitation>();
        
        for(Recitation recitation : recitations)
        {
            Recitation newRecitation = new Recitation(recitation.getSection(), recitation.getInstructor(), recitation.getDayOrTime(),recitation.getLocation(),
                                                      recitation.getTA1(), recitation.getTA2());
            oldRecitations.add(recitation);//SAVE A COPY 
            
            if(recitation.getTA1().equals(name))
            {
                newRecitation.setTA1(""); 
            }
            if(recitation.getTA2().equals(name))
            {
                newRecitation.setTA2("");
            }
            recitations.set(recitations.indexOf(recitation), newRecitation);
        }
    }
    
    //TAB4
    public String getStartingMondayYear(){return startingMondayYear;}
    public String getStartingMondayMonth(){return startingMondayMonth;}
    public String getStartingMondayDay(){return startingMondayDay;}  
    
    public String getEndingFridayYear(){return endingFridayYear;}
    public String getEndingFridayMonth(){return endingFridayMonth;}
    public String getEndingFridayDay(){return endingFridayDay;}

    public void setStartingMondayYear(String i){startingMondayYear = i;}
    public void setStartingMondayMonth(String i){startingMondayMonth = i;}
    public void setStartingMondayDay(String i){startingMondayDay = i;}   
    
    public void setEndingFridayYear(String i){endingFridayYear = i;}
    public void setEndingFridayMonth(String i){endingFridayMonth = i;}
    public void setEndingFridayDay(String i){endingFridayDay = i;}


    public ObservableList getSchedules() {return schedules;}

    
    public void addSchedule(String type, String year, String month, String day, String time, String title, String topic, String link, String criteria) 
    {
        Schedule schedule = new Schedule(type, year, month, day, time, title, topic, link, criteria);
        schedules.add(schedule);

        Collections.sort(schedules);
    }

    public void updateSchedule( String oldScheduleString, 
                                String newType, String newYear, String newMonth, String newDay, 
                                String newTime, String newTitle, String newTopic, String newLink, String newCriteria)
    {
        Schedule updatedSchedule = new Schedule(newType, newYear, newMonth, newDay, newTime, newTitle, newTopic, newLink, newCriteria);

        for (Schedule existingSchedule : schedules) 
        {
            if (existingSchedule.toString().equals(oldScheduleString))
            {
                schedules.set(schedules.indexOf(existingSchedule), updatedSchedule);
            }
        }
        // SORT THE TAS
        Collections.sort(schedules);
    }
    
    public void deleteSchedule(String scheduleString)
    {
        for (Schedule schedule : schedules) 
        {
            if (scheduleString.equals(schedule.toString())) 
            {
                schedules.remove(schedule);
                return;
            }
        }
    }
    
    public Schedule getSchedule(String title) 
    {
        for (Schedule schedule : schedules) 
        {
            if (schedule.getTitle().equals(title)) 
            {
                return schedule;
            }
        }
        return null;
    }
    
    //TAB5
    public ObservableList getTeams() {return teams;}

    public void addTeam(String name, String color, String textColor, String link) 
    {
        Team team = new Team(name, color, textColor, link);
        
        teams.add(team);
        teamsOfStudents.add(name);
        
        Collections.sort(teams);
    }

    public void updateTeam(String oldTeamToString,
                           String newName, String newColor, String newTextColor, String newLink)
    {
        Team updatedTeam = new Team(newName, newColor, newTextColor, newLink);

        for (Team existingTeam : teams) 
        {
            if (existingTeam.toString().equals(oldTeamToString))
            {
                teams.set(teams.indexOf(existingTeam), updatedTeam);
                
                String oldName = oldTeamToString.split(",")[0];
                teamsOfStudents.set(teamsOfStudents.indexOf(oldName), newName);
            }
        }
        // SORT THE TAS
        Collections.sort(teams);
    }
    
    public void deleteTeam(String teamToString)
    {
        for (Team team : teams) 
        {
            if (teamToString.equals(team.toString())) 
            {
                teams.remove(team);
                
                String name = teamToString.split(",")[0];
                teamsOfStudents.remove(name);
                return;
            }
        }
    }
    
    public Team getTeam(String name) 
    {
        for (Team team : teams) 
        {
            if (team.getTeamName().equals(name)) 
            {
                return team;
            }
        }
        return null;
    }
    
    public ObservableList getStudents() {
        return students;
    }

    public void addStudent(String firstName, String lastName, String team, String role) {
        Student student = new Student(firstName, lastName, team, role);

        students.add(student);


        Collections.sort(students);
    }
    
    public void updateStudent(String oldStudentToString,
                              String newFirstName, String newLastName, String newTeam, String newRole)
    {
        Student updatedStudent = new Student(newFirstName, newLastName, newTeam, newRole);

        for (Student existingStudent : students) 
        {
            if (existingStudent.toString().equals(oldStudentToString))
            {
                students.set(students.indexOf(existingStudent), updatedStudent);
            }
        }
        // SORT THE TAS
        Collections.sort(students);
    }
    
    public void deleteStudent(String studentToString)
    {
        for (Student student : students) 
        {
            if (studentToString.equals(student.toString())) 
            {
                students.remove(student);
                return;
            }
        }
    }
    
    
    public Student getStudent(String firstName, String lastName) 
    {
        for (Student student : students) 
        {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) 
            {
                return student;
            }
        }
        return null;
    }
    
    public ObservableList getTeamsOfStudents(){ return teamsOfStudents;}
    
    
    public boolean containsStudent(String firstName, String lastName) 
    {
        for (Student student : students) 
        {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) 
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsOtherStudent(String newFirstName, String newLastName, String oldFirstName, String oldLastName) 
    {
        for (Student student : students) 
        {
            if (student.getFirstName().equals(newFirstName) && student.getLastName().equals(newLastName)) 
            {
                if (!student.getFirstName().equals(oldFirstName) && student.getLastName().equals(oldLastName))//if the newName is different from the old TA's name, which means it's the same as other TA's name
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void deleteTeamFromStudentsByName(String name)
    {
        for(Student student : students)
        {
            Student newStudent = new Student(student.getFirstName(), student.getLastName(), student.getTeam(),student.getRole());

            if(student.getTeam().equals(name))
            {
                newStudent.setTeam(""); 
            }
            students.set(students.indexOf(student), newStudent);
        }
    }
    
    
    
    
    
    
    
    
    
    
    

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void addTimeSlot(String day, String time, String taName) 
    {
        TimeSlot newTimeSlot = new TimeSlot(day, time, taName);
        timeSlots.add(newTimeSlot);
        
    }
    
    public TimeSlot getTimeSlot(String key) 
    {
        for (TimeSlot timeSlot : timeSlots) 
        {
            if ((timeSlot.getKey()).equals(key)) 
            {
                return timeSlot;
            }
        }
        return null;
    }

}
