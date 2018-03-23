package tam.style;

import djf.AppTemplate;
import djf.components.AppStyleComponent;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import tam.data.TeachingAssistant;
import tam.workspace.Workspace;

/**
 * This class manages all CSS style for this application.
 * 
 * @author Richard McKenna
 * @version 1.0
 */
public class Style extends AppStyleComponent {
    // FIRST WE SHOULD DECLARE ALL OF THE STYLE TYPES WE PLAN TO USE
    
    // WE'LL USE THIS FOR ORGANIZING LEFT AND RIGHT CONTROLS
    public static String CLASS_PLAIN_PANE = "plain_pane";
    
    // THESE ARE THE HEADERS FOR EACH SIDE
    public static String CLASS_HEADER_PANE = "header_pane";
    public static String CLASS_HEADER_LABEL = "header_label";

    // ON THE LEFT WE HAVE THE TA ENTRY
    public static String CLASS_TA_TABLE = "ta_table";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String CLASS_ADD_TA_PANE = "add_ta_pane";
    public static String CLASS_ADD_TA_TEXT_FIELD = "add_ta_text_field";
    public static String CLASS_ADD_TA_BUTTON = "add_ta_button";
    public static String CLASS_CLEAR_BUTTON = "clear_button";
    
    // ON THE RIGHT WE HAVE THE OFFICE HOURS GRID
    public static String CLASS_OFFICE_HOURS_GRID = "office_hours_grid";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE = "office_hours_grid_time_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL = "office_hours_grid_time_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE = "office_hours_grid_day_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL = "office_hours_grid_day_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE = "office_hours_grid_time_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL = "office_hours_grid_time_cell_label";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE = "office_hours_grid_ta_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL = "office_hours_grid_ta_cell_label";

    // FOR HIGHLIGHTING CELLS, COLUMNS, AND ROWS
    public static String CLASS_HIGHLIGHTED_GRID_CELL = "highlighted_grid_cell";
    public static String CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN = "highlighted_grid_row_or_column";
    
    //FOR TAB
    public static String CLASS_WORK_SPACE = "work_space";
    public static String CLASS_TABS = "tabs";
    public static String CLASS_TAB_PANE = "tab_pane";
    public static String CLASS_COURSE_DETAILS = "course_details";
    public static String CLASS_COURSE_INFO = "course_info";
    public static String CLASS_BIG_LABEL = "big_label";
    public static String CLASS_SMALL_LABEL = "small_label";
    
    public static String CLASS_HEAVY_SMALL_LABEL = "heavy_small_label";
    public static String CLASS_HEAVY_MIDDLE_LABEL = "heavy_middle_label";
    public static String CLASS_HEAVY_BIG_LABEL = "heavy_big_label";
    
    public static String CLASS_RIGHT_PANE = "right_pane";
    public static String CLASS_RIGHT_SCROLLPANE = "right_scroll_pane";
    
    
    // THIS PROVIDES ACCESS TO OTHER COMPONENTS
    private AppTemplate app;
    
    /**
     * This constructor initializes all style for the application.
     * 
     * @param initApp The application to be stylized.
     */
    public Style(AppTemplate initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // LET'S USE THE DEFAULT STYLESHEET SETUP
        super.initStylesheet(app);

        // INIT THE STYLE FOR THE FILE TOOLBAR
        app.getGUI().initFileToolbarStyle();

        // AND NOW OUR WORKSPACE STYLE
        initTAWorkspaceStyle();
    }

    /**
     * This function specifies all the style classes for
     * all user interface controls in the workspace.
     */
    private void initTAWorkspaceStyle() 
    {
        // LEFT SIDE - THE HEADER
        Workspace workspaceComponent = (Workspace)app.getWorkspaceComponent();
        
        workspaceComponent.getWorkspace().getStyleClass().add(CLASS_WORK_SPACE);
        
        workspaceComponent.getTabPane().getStyleClass().add(CLASS_TAB_PANE);
        //TAB1
        workspaceComponent.getTab1().getStyleClass().add(CLASS_TABS);
        workspaceComponent.getCourseDetailsVBox().getStyleClass().add(CLASS_COURSE_DETAILS);
        //1.1
        workspaceComponent.getCourseInfoGridPane().getStyleClass().add(CLASS_COURSE_INFO);
        workspaceComponent.getCourseInfoLabel().getStyleClass().add(CLASS_BIG_LABEL);
        
        workspaceComponent.getSubjectLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getNumberLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getSemesterLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getYearLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTitleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getInstrNameLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getInstrHomeLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getExportDirLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getExportDirectoryLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getSitePagesLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        //1.2 
        workspaceComponent.getSiteTemplateGridPane().getStyleClass().add(CLASS_COURSE_INFO);
        workspaceComponent.getSiteTemplateLabel().getStyleClass().add(CLASS_BIG_LABEL);
        workspaceComponent.getSiteTemplateDirectoryLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        //1.3
        workspaceComponent.getPageStyleVBox().getStyleClass().add(CLASS_COURSE_INFO);
        workspaceComponent.getPageStyleLabel().getStyleClass().add(CLASS_BIG_LABEL);
        workspaceComponent.getBannerSchoolLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getLeftFooterLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getRightFooterLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getStyleSheetLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
                
        //TAB2
        workspaceComponent.getTab2().getStyleClass().add(CLASS_TABS);
        workspaceComponent.getTADataHbox().getStyleClass().add(CLASS_COURSE_DETAILS);
        
        //LEFT PANE
        workspaceComponent.getTALabel().getStyleClass().add(CLASS_HEAVY_BIG_LABEL);
        
        //RIGHT PANE
        workspaceComponent.getOfficeHoursSubheaderLabel().getStyleClass().add(CLASS_HEAVY_BIG_LABEL);
        
        workspaceComponent.getStartTimeLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getEndTimeLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        
        workspaceComponent.getRightPane().getStyleClass().add(CLASS_RIGHT_PANE);
        workspaceComponent.getRightScrollPane().getStyleClass().add(CLASS_RIGHT_SCROLLPANE);
        
        //TAB3
        workspaceComponent.getTab3().getStyleClass().add(CLASS_TABS);
        workspaceComponent.getRecitationsVBox().getStyleClass().add(CLASS_COURSE_DETAILS);
        
        
        workspaceComponent.getRecitationLabel().getStyleClass().add(CLASS_HEAVY_BIG_LABEL);
        workspaceComponent.getAddOrEditVBox().getStyleClass().add(CLASS_COURSE_INFO);
        
        //1.
        workspaceComponent.getAddOrEditLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        workspaceComponent.getSectionLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getInstructorLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getdayOrTimeLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getLocationLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTA1Label().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTA2Label().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        //TAB4
        workspaceComponent.getTab4().getStyleClass().add(CLASS_TABS);
        workspaceComponent.getScheduleVBox().getStyleClass().add(CLASS_COURSE_DETAILS);
        
        workspaceComponent.getScheduleLabel().getStyleClass().add(CLASS_HEAVY_BIG_LABEL);
        
        workspaceComponent.getCalendarLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        
        workspaceComponent.getStartingMondayLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getEndingFridayLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getScheduleItemsLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        
        workspaceComponent.getAddOrEditScheduleLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        
        workspaceComponent.getTypeOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getDateOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTimeOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTitleOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTopicOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getLinkOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getCriteriaOfScheduleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getCalendarVBox().getStyleClass().add(CLASS_COURSE_INFO);
        workspaceComponent.getScheduleItemsVBox().getStyleClass().add(CLASS_COURSE_INFO);
        //TAB5
        workspaceComponent.getTab5().getStyleClass().add(CLASS_TABS);
        workspaceComponent.getProjectsVBox().getStyleClass().add(CLASS_COURSE_DETAILS);
        workspaceComponent.getTeamsVBox().getStyleClass().add(CLASS_COURSE_INFO);
        workspaceComponent.getStudentsVBox().getStyleClass().add(CLASS_COURSE_INFO);
        
        workspaceComponent.getTAsHeaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getTAsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
        
        workspaceComponent.getProjectsLabel().getStyleClass().add(CLASS_HEAVY_BIG_LABEL);
        workspaceComponent.getTeamsLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        workspaceComponent.getAddOrEditOfTeamsLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        workspaceComponent.getStudentsLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        workspaceComponent.getAddOrEditStudentsLabel().getStyleClass().add(CLASS_HEAVY_MIDDLE_LABEL);
        
        workspaceComponent.getNameOfTeamsLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getColorOfTeamsLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTextColorOfTeamsLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getLinkOfTeamsLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        
        workspaceComponent.getFirstNameLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getLastNameLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getTeamLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);
        workspaceComponent.getRoleLabel().getStyleClass().add(CLASS_HEAVY_SMALL_LABEL);

    }
    
    /**
     * This method initializes the style for all UI components in
     * the office hours grid. Note that this should be called every
     * time a new TA Office Hours Grid is created or loaded.
     */
    public void initOfficeHoursGridStyle() {
        // RIGHT SIDE - THE OFFICE HOURS GRID TIME HEADERS
        Workspace workspaceComponent = (Workspace)app.getWorkspaceComponent();
        workspaceComponent.getOfficeHoursGridPane().getStyleClass().add(CLASS_OFFICE_HOURS_GRID);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderPanes(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeHeaderLabels(), CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderPanes(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridDayHeaderLabels(), CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellPanes(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTimeCellLabels(), CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellPanes(), CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        setStyleClassOnAll(workspaceComponent.getOfficeHoursGridTACellLabels(), CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL);
    }
    
    /**
     * This helper method initializes the style of all the nodes in the nodes
     * map to a common style, styleClass.
     */
    private void setStyleClassOnAll(HashMap nodes, String styleClass) {
        for (Object nodeObject : nodes.values()) {
            Node n = (Node)nodeObject;
            n.getStyleClass().add(styleClass);
        }
    }
}