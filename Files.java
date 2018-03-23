package tam.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import static djf.settings.AppStartupConstants.PATH_CURRENT_CSS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import tam.CourseSiteGenerator;
import tam.data.Data;
import tam.workspace.Workspace;

import tam.data.SitePage;
import tam.data.TeachingAssistant;
import tam.data.Recitation;
import tam.data.Schedule;
import tam.data.Team;
import tam.data.Student;

/**
 * This class is for saving all the data in the Data to .json files.
 * 
 * @author Jia Chen
 */
public class Files implements AppFileComponent 
{
    CourseSiteGenerator app;
    
    //TAB1
    static final String JSON_COURSE_SUBJECT = "courseSubject";
    static final String JSON_COURSE_NUMBER = "courseNumber";
    static final String JSON_COURSE_SEMESTER = "courseSemester";
    static final String JSON_COURSE_YEAR = "courseYear";
    static final String JSON_COURSE_TITLE = "courseTitle";
    static final String JSON_COURSE_INSTRUCTOR_NAME = "courseInstructorName";
    static final String JSON_COURSE_INSTRUCTOR_HOME = "courseInstructorHome";
    static final String JSON_COURSE_EXPORT_DIRECTORY = "courseExportDirectory";
    
    static final String JSON_COURSE_TEMPLATE_DIRECTORY = "courseTemplateDirectory";
    
    static final String JSON_SITE_PAGES = "sitePages";
    
    static final String JSON_IS_SITE_PAGE_USED = "isSitePageUsed";
    static final String JSON_NAVBAR_TITLE = "navbarTitle";
    static final String JSON_FILE_NAME = "fileName";
    static final String JSON_SCRIPT = "script";
    
    static final String JSON_BANNER_SCHOOL_IMAGE_DIR = "bannerSchoolImageDir";
    static final String JSON_LEFT_FOOTER_IMAGE_DIR = "leftFooterImageDir";
    static final String JSON_RIGHT_FOOTER_IMAGE_DIR = "rightFooterImageDir";
    static final String JSON_STYLE_SHEET_DIR = "styleSheetDir";
    
    //TAB2
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    
    static final String JSON_IS_UNDERGRADUATED = "isUndergraduated";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    
    static final String JSON_OFFICE_HOURS = "officeHours";
    
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    
    //TAB3
    static final String JSON_RECITATIONS = "recitations";
    
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAY_OR_TIME = "dayOrTime";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA1 = "TA1";
    static final String JSON_TA2 = "TA2";
    
    //TAB4
    static final String JSON_STARTING_MONDAY_YEAR = "startingMondayYear";
    static final String JSON_STARTING_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_STARTING_MONDAY_DAY = "startingMondayDay";
    
    static final String JSON_ENDING_FRIDAY_YEAR = "endingFridayYear";
    static final String JSON_ENDING_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_ENDING_FRIDAY_DAY = "endingFridayDay";

    static final String JSON_YEAR_OF_SCHEDULEITEM = "year";
    static final String JSON_MONTH_OF_SCHEDULEITEM = "month";
    static final String JSON_DAY_OF_SCHEDULEITEM = "day";
    
    static final String JSON_TITLE = "title";
    static final String JSON_TOPIC = "topic";
    static final String JSON_LINK = "link";
    
    static final String JSON_TIME_OF_SCHEDULE = "timeOfSchedule";
    static final String JSON_CRITERIA = "criteria";
    

    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_RECITATIONS_OF_SCHEDULE = "recitationsOfSchedule";
    static final String JSON_REFERENCES = "references";
    static final String JSON_HWS = "hws";

    //TAB5
    static final String JSON_TEAMS = "teams";
    
    static final String JSON_TEAM_NAME = "teamName";
    static final String JSON_TEAM_COLOR = "teamColor";
    static final String JSON_TEAM_RED = "red";
    static final String JSON_TEAM_GREEN = "green";
    static final String JSON_TEAM_BLUE = "blue";
    static final String JSON_TEAM_TEXT_COLOR = "teamTextColor";
    static final String JSON_TEAM_LINK = "teamLink";
    
    static final String JSON_STUDENTS = "students";
    
    static final String JSON_STUDENT_FIRSTNAME = "studentFirstName";
    static final String JSON_STUDENT_LASTNAME = "studentLastName";
    static final String JSON_STUDENT_TEAM = "studentTeam";
    static final String JSON_STUDENT_ROLE = "studentRole";
    
    static final String JSON_TEAM_WORKS = "work";
    
    static final String JSON_TEAM_PROJECTS = "projects";
    
    static final String JSON_TEAM_STUDENTS = "students";
    static final String JSON_TEAM_STUDENT = "student";
    
    public Files(CourseSiteGenerator initApp) 
    {
        app = initApp;
        
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException 
    {
	// CLEAR THE OLD DATA OUT
	Data dataManager = (Data)data;

        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
        
        //TAB1
        dataManager.updateCourseInfo
        ( 
            json.getString(JSON_COURSE_SUBJECT), json.getString(JSON_COURSE_NUMBER),
            json.getString(JSON_COURSE_SEMESTER), json.getString(JSON_COURSE_YEAR), 
            json.getString(JSON_COURSE_TITLE), json.getString(JSON_COURSE_INSTRUCTOR_NAME),
            json.getString(JSON_COURSE_INSTRUCTOR_HOME), json.getString(JSON_COURSE_EXPORT_DIRECTORY)
        );
        
        
        dataManager.updateSiteTemplateDirectory(json.getString(JSON_COURSE_TEMPLATE_DIRECTORY));
        
        JsonArray jsonSitePageArray = json.getJsonArray(JSON_SITE_PAGES);
        for (int i = 0; i < jsonSitePageArray.size(); i++) 
        {
            JsonObject jsonSitePage = jsonSitePageArray.getJsonObject(i);
            boolean isSitePageUsed = jsonSitePage.getBoolean(JSON_IS_SITE_PAGE_USED);
            String navbarTitle = jsonSitePage.getString(JSON_NAVBAR_TITLE);
            String fileName = jsonSitePage.getString(JSON_FILE_NAME);
            String script = jsonSitePage.getString(JSON_SCRIPT);
            
            dataManager.addSitePage(isSitePageUsed, navbarTitle, fileName, script);
            dataManager.toggle_Site_Page_Used(navbarTitle, isSitePageUsed);
        }
        
        dataManager.updateBannerSchoolImageDir(json.getString(JSON_BANNER_SCHOOL_IMAGE_DIR));
        dataManager.updateLeftFooterImageDir(json.getString(JSON_LEFT_FOOTER_IMAGE_DIR));
        dataManager.updateRightFooterImageDir(json.getString(JSON_RIGHT_FOOTER_IMAGE_DIR));
        dataManager.updateStyleSheetDir(json.getString(Files.JSON_STYLE_SHEET_DIR));
        
        
        
        
        
        
        
        //TAB2
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        
        dataManager.initHours(startHour, endHour); //IT'S NOT ENOUGH BECAUSE THE START AND END TIME ARE NOT FIXED ANYMORE
        
        int initStartHour = Integer.parseInt(startHour);
        int initEndHour = Integer.parseInt(endHour);
        
        dataManager.setStartHour(initStartHour);
        dataManager.setEndHour(initEndHour);
        
        
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) 
        {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            boolean isUndergraduated = jsonTA.getBoolean(JSON_IS_UNDERGRADUATED);
            
            dataManager.addTA(name, email);
            dataManager.toggle_TA_Undergraduated(name, isUndergraduated);
        }

        //LOADING OFFICEHOURS [1] INTO TIMESLOTS FIRST
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) 
        {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addTimeSlot(day, time, name);
        }
            
        //TAB3
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) 
        {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            
            String section = jsonRecitation.getString(JSON_SECTION);
            String instructor = jsonRecitation.getString(JSON_INSTRUCTOR);
            String dayOrTime = jsonRecitation.getString(JSON_DAY_OR_TIME);
            String location = jsonRecitation.getString(JSON_LOCATION);
            String TA1 = jsonRecitation.getString(JSON_TA1);
            String TA2 = jsonRecitation.getString(JSON_TA2);
            
            dataManager.addRecitation(section, instructor, dayOrTime, location, TA1, TA2);
        }
        
        //TAB4
        dataManager.setStartingMondayMonth(json.getString(Files.JSON_STARTING_MONDAY_MONTH));
        dataManager.setStartingMondayDay(json.getString(Files.JSON_STARTING_MONDAY_DAY));
        dataManager.setEndingFridayMonth(json.getString(Files.JSON_ENDING_FRIDAY_MONTH));
        dataManager.setEndingFridayDay(json.getString(Files.JSON_ENDING_FRIDAY_DAY));
        
        JsonArray jsonHolidaysArray = json.getJsonArray(JSON_HOLIDAYS);
        for (int i = 0; i < jsonHolidaysArray.size(); i++) 
        {
            JsonObject jsonSchedule = jsonHolidaysArray.getJsonObject(i);
            
            String year = jsonSchedule.getString(JSON_YEAR_OF_SCHEDULEITEM);
            String month = jsonSchedule.getString(JSON_MONTH_OF_SCHEDULEITEM);
            String day = jsonSchedule.getString(JSON_DAY_OF_SCHEDULEITEM);
            
            String title = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            
            String time = jsonSchedule.getString(JSON_TIME_OF_SCHEDULE);
            String criteria = jsonSchedule.getString(JSON_CRITERIA);
            
            dataManager.addSchedule("Holiday", year, month, day, time, title, topic, link, criteria);
        }
        
        JsonArray jsonLecturesArray = json.getJsonArray(JSON_LECTURES);
        for (int i = 0; i < jsonLecturesArray.size(); i++) 
        {
            JsonObject jsonSchedule = jsonLecturesArray.getJsonObject(i);
            
            String year = jsonSchedule.getString(JSON_YEAR_OF_SCHEDULEITEM);
            String month = jsonSchedule.getString(JSON_MONTH_OF_SCHEDULEITEM);
            String day = jsonSchedule.getString(JSON_DAY_OF_SCHEDULEITEM);
            
            String title = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            
            String time = jsonSchedule.getString(JSON_TIME_OF_SCHEDULE);
            String criteria = jsonSchedule.getString(JSON_CRITERIA);
            
            dataManager.addSchedule("Lecture", year, month, day, time, title, topic, link, criteria);
        }
        
        JsonArray jsonRecitationsOfScheduleArray = json.getJsonArray(JSON_RECITATIONS_OF_SCHEDULE);
        for (int i = 0; i < jsonRecitationsOfScheduleArray.size(); i++) 
        {
            JsonObject jsonSchedule = jsonRecitationsOfScheduleArray.getJsonObject(i);
            
            String year = jsonSchedule.getString(JSON_YEAR_OF_SCHEDULEITEM);
            String month = jsonSchedule.getString(JSON_MONTH_OF_SCHEDULEITEM);
            String day = jsonSchedule.getString(JSON_DAY_OF_SCHEDULEITEM);
            
            String title = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            
            String time = jsonSchedule.getString(JSON_TIME_OF_SCHEDULE);
            String criteria = jsonSchedule.getString(JSON_CRITERIA);
            
            dataManager.addSchedule("Recitation", year, month, day, time, title, topic, link, criteria);
        }
        
        JsonArray jsonReferencesArray = json.getJsonArray(JSON_REFERENCES);
        for (int i = 0; i < jsonReferencesArray.size(); i++) 
        {
            JsonObject jsonSchedule = jsonReferencesArray.getJsonObject(i);
            
            String year = jsonSchedule.getString(JSON_YEAR_OF_SCHEDULEITEM);
            String month = jsonSchedule.getString(JSON_MONTH_OF_SCHEDULEITEM);
            String day = jsonSchedule.getString(JSON_DAY_OF_SCHEDULEITEM);
            
            String title = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            
            String time = jsonSchedule.getString(JSON_TIME_OF_SCHEDULE);
            String criteria = jsonSchedule.getString(JSON_CRITERIA);
            
            dataManager.addSchedule("Reference", year, month, day, time, title, topic, link, criteria);
        }
        
        JsonArray jsonHwsArray = json.getJsonArray(JSON_HWS);
        for (int i = 0; i < jsonHwsArray.size(); i++) 
        {
            JsonObject jsonSchedule = jsonHwsArray.getJsonObject(i);
            
            String year = jsonSchedule.getString(JSON_YEAR_OF_SCHEDULEITEM);
            String month = jsonSchedule.getString(JSON_MONTH_OF_SCHEDULEITEM);
            String day = jsonSchedule.getString(JSON_DAY_OF_SCHEDULEITEM);
            
            String title = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            
            String time = jsonSchedule.getString(JSON_TIME_OF_SCHEDULE);
            String criteria = jsonSchedule.getString(JSON_CRITERIA);
            
            dataManager.addSchedule("HW", year, month, day, time, title, topic, link, criteria);
        }
        
        //TAB5
        JsonArray jsonTeamArray = json.getJsonArray(JSON_TEAMS);
        for (int i = 0; i < jsonTeamArray.size(); i++) 
        {
            JsonObject jsonTeam = jsonTeamArray.getJsonObject(i);
            
            String teamName = jsonTeam.getString(JSON_TEAM_NAME);
            String teamColor = jsonTeam.getString(JSON_TEAM_COLOR);

            String teamTextColor = jsonTeam.getString(JSON_TEAM_TEXT_COLOR);
            String teamLink = jsonTeam.getString(JSON_TEAM_LINK);
            dataManager.addTeam(teamName, teamColor, teamTextColor, teamLink);
        }
        
       
        JsonArray jsonStudentArray = json.getJsonArray(JSON_STUDENTS);
        for (int i = 0; i < jsonStudentArray.size(); i++) 
        {
            JsonObject jsonStudent = jsonStudentArray.getJsonObject(i);
            
            String studentFirstName = jsonStudent.getString(JSON_STUDENT_FIRSTNAME);
            String studentLastName = jsonStudent.getString(JSON_STUDENT_LASTNAME);
            String studentTeam = jsonStudent.getString(JSON_STUDENT_TEAM);
            String studentRole = jsonStudent.getString(JSON_STUDENT_ROLE);
            
            dataManager.addStudent(studentFirstName, studentLastName, studentTeam, studentRole);
        }
        
        //AFTER LOADING DATA FROM JSON FILE TO DATA, LOAD THEM INTO WORKSPACE, WHICH MEANS FIVE PANES
        //FOR EVERY STUFF RELATED TO THE WORKSPACE, WE NEED TO PUT THEM IN A TRT-CATCH, BECUASE IN THE TEST CASE, WORKSPACE CAN NOT BE INITIALIZED
        try
        {
            //TAB1
            workspace.getSubjectComboBox().setValue(json.getString(JSON_COURSE_SUBJECT));
            workspace.getNumberComboBox().setValue(json.getString(JSON_COURSE_NUMBER));
            workspace.getSemesterComboBox().setValue(json.getString(JSON_COURSE_SEMESTER));
            workspace.getYearComboBox().setValue(json.getString(JSON_COURSE_YEAR));
            workspace.getTitleTextField().setText(json.getString(JSON_COURSE_TITLE));
            workspace.getInstrNameTextField().setText(json.getString(JSON_COURSE_INSTRUCTOR_NAME));
            workspace.getInstrHomeTextField().setText(json.getString(JSON_COURSE_INSTRUCTOR_HOME));
            workspace.getExportDirectoryLabel().setText(json.getString(JSON_COURSE_EXPORT_DIRECTORY));
        
            workspace.getSiteTemplateDirectoryLabel().setText(json.getString(JSON_COURSE_TEMPLATE_DIRECTORY));
            
            
            
            //FOR THE CONTENTS UNDER CSS FOLDER UNDER WORK FOLDER
            File currentCSSFile = new File(PATH_CURRENT_CSS);
            for(String s: currentCSSFile.list())
            {
                workspace.getStyleSheetComboBox().getItems().add(s);
            }
            
            //TAB2
            workspace.updateBannerSchoolImage(json.getString(JSON_BANNER_SCHOOL_IMAGE_DIR));
            workspace.updateLeftFooterImage(json.getString(JSON_LEFT_FOOTER_IMAGE_DIR));
            workspace.updateRightFooterImage(json.getString(JSON_RIGHT_FOOTER_IMAGE_DIR));
            workspace.updateStyleSheetComboBox(json.getString(JSON_STYLE_SHEET_DIR));
            
            workspace.getStartTimeBox().setValue(workspace.getStartTimeBox().getItems().get(Integer.parseInt(json.getString(JSON_START_HOUR))));
            workspace.getEndTimeBox().setValue(workspace.getEndTimeBox().getItems().get(Integer.parseInt(json.getString(JSON_END_HOUR))));
            
            // NOW RELOAD THE WORKSPACE WITH THE LOADED DATA
            app.getWorkspaceComponent().reloadWorkspace(dataManager);
            
            //LOADING OFFICEHOURS [2] INTO OFFICEHOURS SECOND
            for (int i = 0; i < jsonOfficeHoursArray.size(); i++) 
            {
                JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
                String day = jsonOfficeHours.getString(JSON_DAY);
                String time = jsonOfficeHours.getString(JSON_TIME);
                String name = jsonOfficeHours.getString(JSON_NAME);
                dataManager.addOfficeHoursReservation(day, time, name);
            }
            
            //TAB4
            workspace.updateStartingMondayDatePicker(json.getString(Files.JSON_STARTING_MONDAY_YEAR), json.getString(Files.JSON_STARTING_MONDAY_MONTH), json.getString(Files.JSON_STARTING_MONDAY_DAY));
            workspace.updateEndingFridayDatePicker(json.getString(Files.JSON_ENDING_FRIDAY_YEAR), json.getString(Files.JSON_ENDING_FRIDAY_MONTH), json.getString(Files.JSON_ENDING_FRIDAY_DAY));
        }
        catch(NullPointerException e)
        {}
    }
      
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException 
    {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException 
    {
	// GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();

        
        //TAB2
        JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) 
        {	    
	    JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_IS_UNDERGRADUATED, ta.getIsUndergraduated())
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

        
        
        
        
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
        ArrayList<TimeSlot> officeHours =  new ArrayList();
        try
        {
            officeHours = TimeSlot.buildOfficeHoursList(dataManager);
        }
        catch(NullPointerException e)
        {
            officeHours =  dataManager.getTimeSlots();
            System.out.println("TimeSlots For Test When The officeHours HashMap Is Null.");
        }
        
        
	for (TimeSlot ts : officeHours) 
        {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
        //TAB3
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation recitation : recitations) 
        {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(JSON_SECTION, recitation.getSection())
		    .add(JSON_INSTRUCTOR, recitation.getInstructor())
                    .add(JSON_DAY_OR_TIME, recitation.getDayOrTime())
                    .add(JSON_LOCATION, recitation.getLocation())
                    .add(JSON_TA1, recitation.getTA1())
                    .add(JSON_TA2, recitation.getTA2())
                    .build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationArrayBuilder.build();

        //TAB4
        JsonArrayBuilder holidaysArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitationsOfScheduleArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referencesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        
        //JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
	ObservableList<Schedule> schedules = dataManager.getSchedules();
	for (Schedule schedule : schedules) 
        {	    
            if(schedule.getType().equals("Holiday"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_YEAR_OF_SCHEDULEITEM, schedule.getYear())    
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                        
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                        
                    .add(JSON_TIME_OF_SCHEDULE, schedule.getTime())
                    .add(JSON_CRITERIA, schedule.getCriteria())
                           
                    .build();
                holidaysArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Lecture"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_YEAR_OF_SCHEDULEITEM, schedule.getYear())    
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                        
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                        
                    .add(JSON_TIME_OF_SCHEDULE, schedule.getTime())
                    .add(JSON_CRITERIA, schedule.getCriteria())
                    .build();
                lecturesArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Recitation"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_YEAR_OF_SCHEDULEITEM, schedule.getYear())    
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                        
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                        
                    .add(JSON_TIME_OF_SCHEDULE, schedule.getTime())
                    .add(JSON_CRITERIA, schedule.getCriteria())
                    .build();
                recitationsOfScheduleArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Reference"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_YEAR_OF_SCHEDULEITEM, schedule.getYear())    
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                        
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                        
                    .add(JSON_TIME_OF_SCHEDULE, schedule.getTime())
                    .add(JSON_CRITERIA, schedule.getCriteria())
                    .build();
                referencesArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("HW"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_YEAR_OF_SCHEDULEITEM, schedule.getYear())    
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                        
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                        
                    .add(JSON_TIME_OF_SCHEDULE, schedule.getTime())
                    .add(JSON_CRITERIA, schedule.getCriteria())
                    .build();
                hwsArrayBuilder.add(scheduleJson);
            }
	}
        
        JsonArray holidaysArray = holidaysArrayBuilder.build();
        JsonArray lecturesArray = lecturesArrayBuilder.build();
        JsonArray recitationsOfScheduleArray = recitationsOfScheduleArrayBuilder.build();
        JsonArray referencesArray = referencesArrayBuilder.build();
        JsonArray hwsArray = hwsArrayBuilder.build();
        
        
        //TAB5
        JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) 
        {	    
	    JsonObject teamJson = Json.createObjectBuilder()
		    .add(JSON_TEAM_NAME, team.getTeamName())
		    .add(JSON_TEAM_COLOR, team.getTeamColor())
                    .add(JSON_TEAM_TEXT_COLOR, team.getTeamTextColor())
                    .add(JSON_TEAM_LINK, team.getTeamLink())
                    .add(JSON_TEAM_RED, team.getTeamRed())
                    .add(JSON_TEAM_GREEN, team.getTeamGreen())
                    .add(JSON_TEAM_BLUE, team.getTeamBlue())
                    .build();
	    teamArrayBuilder.add(teamJson);
	}
	JsonArray teamsArray = teamArrayBuilder.build();
        
        
        JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student student : students) 
        {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add(JSON_STUDENT_FIRSTNAME, student.getFirstName())
		    .add(JSON_STUDENT_LASTNAME, student.getLastName())
                    .add(JSON_STUDENT_TEAM, student.getTeam())
                    .add(JSON_STUDENT_ROLE, student.getRole())
                    .build();
	    studentArrayBuilder.add(studentJson);
	}
	JsonArray studentsArray = studentArrayBuilder.build();
        
        
        
        
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                //TAB1
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())
                
                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir())
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir())
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir())
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                //TAB2
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                
                //TAB3
                
                .add(JSON_RECITATIONS, recitationsArray)
                
                
                //TAB4
                .add(Files.JSON_STARTING_MONDAY_YEAR, "" + dataManager.getStartingMondayYear())
                .add(Files.JSON_STARTING_MONDAY_MONTH, "" + dataManager.getStartingMondayMonth())
                .add(Files.JSON_STARTING_MONDAY_DAY, "" + dataManager.getStartingMondayDay())
                
                .add(Files.JSON_ENDING_FRIDAY_YEAR, "" + dataManager.getEndingFridayYear())
                .add(Files.JSON_ENDING_FRIDAY_MONTH, "" + dataManager.getEndingFridayMonth())
                .add(Files.JSON_ENDING_FRIDAY_DAY, "" + dataManager.getEndingFridayDay())
                
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_RECITATIONS_OF_SCHEDULE, recitationsOfScheduleArray)
                .add(JSON_REFERENCES, referencesArray)
                .add(JSON_HWS, hwsArray)
                
                
                //TAB5
                .add(JSON_TEAMS, teamsArray)
                
                .add(JSON_STUDENTS, studentsArray)
                
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException 
    {}

    @Override
    public void exportDataForOfficeHours(AppDataComponent data, String filePath) throws IOException 
    {
        // GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();
        
        //TAB2
        JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) 
        {	    
	    JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_IS_UNDERGRADUATED, ta.getIsUndergraduated())
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail())
                    .build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
        ArrayList<TimeSlot> officeHours =  new ArrayList();
        try
        {
            officeHours = TimeSlot.buildOfficeHoursList(dataManager);
        }
        catch(NullPointerException e)
        {
            officeHours =  dataManager.getTimeSlots();
            System.out.println("OfficeHours For Test");
        }
        
        
	for (TimeSlot ts : officeHours) 
        {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                
                //TAB2
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())

                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir().replace("file:", ""))
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir().replace("file:", ""))
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir().replace("file:", ""))
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    @Override
    public void exportDataForProjects(AppDataComponent data, String filePath) throws IOException
    {
        // GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();
        
        //TAB5
        
        //WORKS ARRAY
        JsonArrayBuilder worksArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<Team> works = dataManager.getTeams();
        ObservableList<Student> students = dataManager.getStudents();
        
	for (Team work : works) 
        {	   
            //STUDENTS ARRAY
            JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
            for(Student student: students)
            {
                if(student.getTeam().equals(work.getTeamName()))
                {
                    JsonObject studentsJson = Json.createObjectBuilder()
                        .add(JSON_TEAM_STUDENT, student.getFirstName()+" "+student.getLastName())
                        .build();
            
                    studentsArrayBuilder.add(studentsJson);
                }
            }
            JsonArray studentsArray = studentsArrayBuilder.build();
            //STUDENTS ARRAY
            
            //PROJECTS ARRAY
            JsonArrayBuilder projectsArrayBuilder = Json.createArrayBuilder();
            JsonObject projectJson = Json.createObjectBuilder()
                .add(JSON_TEAM_NAME, work.getTeamName())
                .add(JSON_TEAM_STUDENTS, studentsArray)
                .add(JSON_TEAM_LINK, work.getTeamLink())
                .build();
            projectsArrayBuilder.add(projectJson);
            JsonArray projectsArray = projectsArrayBuilder.build();
            //PROJECTS ARRAY
            
            
            
	    JsonObject workJson = Json.createObjectBuilder()
                    .add(JSON_TEAM_PROJECTS, projectsArray)
                    .add(Files.JSON_COURSE_SEMESTER, dataManager.getCourseSemester()+" "+dataManager.getCourseYear())
                    .build();
	    worksArrayBuilder.add(workJson);
	}
	JsonArray worksArray = worksArrayBuilder.build();
        //WORKS ARRAY
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())
                
                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir().replace("file:", ""))
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir().replace("file:", ""))
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir().replace("file:", ""))
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                .add(JSON_TEAM_WORKS, worksArray)
                .build();
        
        
        
        
        
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    @Override
    public void exportDataForRecitations(AppDataComponent data, String filePath) throws IOException
    {
        // GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();
        
        //TAB3
        JsonArrayBuilder recitationArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation recitation : recitations) 
        {	    
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(JSON_SECTION, recitation.getSection())
		    .add(JSON_INSTRUCTOR, recitation.getInstructor())
                    .add(JSON_DAY_OR_TIME, recitation.getDayOrTime())
                    .add(JSON_LOCATION, recitation.getLocation())
                    .add(JSON_TA1, recitation.getTA1())
                    .add(JSON_TA2, recitation.getTA2())
                    .build();
	    recitationArrayBuilder.add(recitationJson);
	}
	JsonArray recitationsArray = recitationArrayBuilder.build();

	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                
                //TAB3
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())
                
                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir().replace("file:", ""))
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir().replace("file:", ""))
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir().replace("file:", ""))
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                .add(JSON_RECITATIONS, recitationsArray)
                
                
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    @Override
    public void exportDataForSchedule(AppDataComponent data, String filePath) throws IOException
    {
        // GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();
        
        
        //TAB4
        JsonArrayBuilder holidaysArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder recitationsOfScheduleArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder referencesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder hwsArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<Schedule> schedules = dataManager.getSchedules();
	for (Schedule schedule : schedules) 
        {	    
            if(schedule.getType().equals("Holiday"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                        
		    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                    .build();
                holidaysArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Lecture"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                    .build();
                lecturesArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Recitation"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                    .build();
                recitationsOfScheduleArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("Reference"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                    .build();
                referencesArrayBuilder.add(scheduleJson);
            }
            else if(schedule.getType().equals("HW"))
            {
                JsonObject scheduleJson = Json.createObjectBuilder()
                    .add(JSON_MONTH_OF_SCHEDULEITEM, schedule.getMonth())
                    .add(JSON_DAY_OF_SCHEDULEITEM, schedule.getDay())
                    .add(JSON_TITLE, schedule.getTitle())
                    .add(JSON_TOPIC, schedule.getTopic())
                    .add(JSON_LINK, schedule.getLink())
                    .build();
                hwsArrayBuilder.add(scheduleJson);
            }
	}
        
        
        JsonArray holidaysArray = holidaysArrayBuilder.build();
        JsonArray lecturesArray = lecturesArrayBuilder.build();
        JsonArray recitationsOfScheduleArray = recitationsOfScheduleArrayBuilder.build();
        JsonArray referencesArray = referencesArrayBuilder.build();
        JsonArray hwsArray = hwsArrayBuilder.build();
        
        
        
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                
                //TAB4
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())
                
                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir().replace("file:", ""))
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir().replace("file:", ""))
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir().replace("file:", ""))
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                .add(Files.JSON_STARTING_MONDAY_YEAR, "" + dataManager.getStartingMondayYear())
                .add(Files.JSON_STARTING_MONDAY_MONTH, "" + dataManager.getStartingMondayMonth())
                .add(Files.JSON_STARTING_MONDAY_DAY, "" + dataManager.getStartingMondayDay())
                
                .add(Files.JSON_ENDING_FRIDAY_YEAR, "" + dataManager.getEndingFridayYear())
                .add(Files.JSON_ENDING_FRIDAY_MONTH, "" + dataManager.getEndingFridayMonth())
                .add(Files.JSON_ENDING_FRIDAY_DAY, "" + dataManager.getEndingFridayDay())
                
                .add(JSON_HOLIDAYS, holidaysArray)
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_RECITATIONS_OF_SCHEDULE, recitationsOfScheduleArray)
                .add(JSON_REFERENCES, referencesArray)
                .add(JSON_HWS, hwsArray)
                
                
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    @Override
    public void exportDataForTeams(AppDataComponent data, String filePath) throws IOException
    {
        // GET THE DATA
	Data dataManager = (Data)data;
        
        //TAB1
        JsonArrayBuilder sitePageArrayBuilder = Json.createArrayBuilder();
        
	ObservableList<SitePage> sitePages = dataManager.getSitePages();
	for (SitePage sitePage : sitePages) 
        {	    
	    JsonObject sitePageJson = Json.createObjectBuilder()
		    .add(JSON_IS_SITE_PAGE_USED, sitePage.getIsUsed())
		    .add(JSON_NAVBAR_TITLE, sitePage.getNavbarTitle())
                    .add(JSON_FILE_NAME, sitePage.getFileName())
                    .add(JSON_SCRIPT, sitePage.getScript()).build();
	    sitePageArrayBuilder.add(sitePageJson);
	}
	JsonArray sitePagesArray = sitePageArrayBuilder.build();
        
        //TAB5
        JsonArrayBuilder teamArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) 
        {	    
	    JsonObject teamJson = Json.createObjectBuilder()
		    .add(JSON_TEAM_NAME, team.getTeamName())
		    .add(JSON_TEAM_COLOR, team.getTeamColor())
                    .add(JSON_TEAM_TEXT_COLOR, team.getTeamTextColor())
                    .add(JSON_TEAM_LINK, team.getTeamLink())
                    
                    .add(JSON_TEAM_RED, team.getTeamRed())
                    .add(JSON_TEAM_GREEN, team.getTeamGreen())
                    .add(JSON_TEAM_BLUE, team.getTeamBlue())
                    .build();
	    teamArrayBuilder.add(teamJson);
	}
	JsonArray teamsArray = teamArrayBuilder.build();
        
        
        
        JsonArrayBuilder studentArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student student : students) 
        {	    
	    JsonObject studentJson = Json.createObjectBuilder()
		    .add(JSON_STUDENT_FIRSTNAME, student.getFirstName())
		    .add(JSON_STUDENT_LASTNAME, student.getLastName())
                    .add(JSON_STUDENT_TEAM, student.getTeam())
                    .add(JSON_STUDENT_ROLE, student.getRole())
                    .build();
	    studentArrayBuilder.add(studentJson);
	}
	JsonArray studentsArray = studentArrayBuilder.build();
        
        
        
        
        
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                
                //TAB5
                .add(JSON_COURSE_SUBJECT, "" + dataManager.getCourseSubject())
		.add(JSON_COURSE_NUMBER, "" + dataManager.getCourseNumber())
                .add(JSON_COURSE_SEMESTER, "" + dataManager.getCourseSemester())
		.add(JSON_COURSE_YEAR, "" + dataManager.getCourseYear())
                .add(JSON_COURSE_TITLE, "" + dataManager.getCourseTitle())
		.add(JSON_COURSE_INSTRUCTOR_NAME, "" + dataManager.getCourseInstructorName())
                .add(JSON_COURSE_INSTRUCTOR_HOME, "" + dataManager.getCourseInstructorHome())
		.add(JSON_COURSE_EXPORT_DIRECTORY, "" + dataManager.getCourseExportDirectory())
                
                .add(JSON_COURSE_TEMPLATE_DIRECTORY, "" + dataManager.getCourseTemplateDirectory())
                
                .add(JSON_SITE_PAGES, sitePagesArray)
                
                .add(JSON_BANNER_SCHOOL_IMAGE_DIR, "" + dataManager.getBannerSchoolImageDir().replace("file:", ""))
                .add(JSON_LEFT_FOOTER_IMAGE_DIR, "" + dataManager.getLeftFooterImageDir().replace("file:", ""))
                .add(JSON_RIGHT_FOOTER_IMAGE_DIR, "" + dataManager.getRightFooterImageDir().replace("file:", ""))
                .add(JSON_STYLE_SHEET_DIR, "" + dataManager.getStyleSheetDir())
                
                .add(JSON_TEAMS, teamsArray)
                
                .add(JSON_STUDENTS, studentsArray)
                
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

}    
    