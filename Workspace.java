package tam.workspace;

import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import static djf.settings.AppStartupConstants.FILE_PROTOCOL;
import static djf.settings.AppStartupConstants.PATH_IMAGES;
import djf.ui.AppGUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import tam.CourseSiteGenerator;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import tam.CourseSiteGeneratorProperties;
import tam.style.Style;
import tam.data.Data;

import javafx.scene.control.ComboBox;//for task 4
import javafx.scene.input.KeyCode;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;

//TAB1
import tam.data.SitePage;

//TAB2
import tam.data.TeachingAssistant;

//TAB3
import tam.data.Recitation;

//TAB4
import tam.data.Schedule;

//TAB5
import tam.data.Team;
import tam.data.Student;

public class Workspace extends AppWorkspaceComponent 
{
    CourseSiteGenerator app;
    Controller controller;
    
    //TAB PANE
    TabPane tabPane;
    
    //TAB1
    Tab tab1;
    VBox courseDetailsVBox;
    
    //FIRST SUB PANE
    GridPane courseInfoGridPane;
    Label courseInfoLabel;
    Label subjectLabel;
    ComboBox subjectComboBox;
    Label numberLabel;
    ComboBox numberComboBox;
    Label semesterLabel;
    ComboBox semesterComboBox;
    Label yearLabel;
    ComboBox yearComboBox;
    Label titleLabel;
    TextField titleTextField;
    Label instrName;
    TextField instrNameTextField;
    Label instrHome;
    TextField instrHomeTextField;
    Label exportDirLabel;
    Button changeExportDirButton;
    Label exportDirectoryLabel;
        
    //SECOND SUB PANE
    GridPane siteTemplateGridPane;
    Label siteTemplate;
    Label siteTemplateNoteLabel;
    Label siteTemplateDirectoryLabel;
    Button siteTemplateDirectoryButton;
    Label sitePages;
    TableView<SitePage> sitePageTableView;
    TableColumn useTableColumn;
    TableColumn navBarTitleTableColumn;
    TableColumn fileNameTableColumn;
    TableColumn scriptTableColumn;
        
    //THIRD SUB PANE
    VBox pageStyleVBox;
    Label pageStyle;
    
    HBox bannerSchoolHBox;
    Label bannerSchoolLabel;
    String bannerSchoolImageDir;
    ImageView bannerSchoolImageView;
    Button changeBannerSchoolImageButton;
    
    HBox leftFooterHBox;
    Label leftFooterLabel;
    String leftFooterImageDir;
    ImageView leftFooterImageView;
    Button changeLeftFooterImageButton; 
    
    HBox rightFooterHBox;
    Label rightFooterLabel;
    String rightFooterImageDir;
    ImageView rightFooterImageView;
    Button changeRightFooterImageButton;
    
    Label styleSheetLabel;
    ComboBox styleSheetComboBox;
    Label styleSheetNoteLabel;
            
    
    
    //TAB2
    Tab tab2;
    HBox TADataHBox;
    
    Label TALabel;
    Button deleteTAButton;
    HBox TAHBox;
       
    //LEFT PANE
    VBox leftPane = new VBox();
    HBox OfficeHoursHBox;
    
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;

    
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button clearButton;
    Button submitButton;
    ComboBox startTimeBox;
    ComboBox endTimeBox;
    
    //RIGHT PANE
    VBox rightPane;
    ScrollPane rightScrollPane;
    
    Label startTimeLabel;
    Label endTimeLabel;
            
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;

    //TAB3   
    Tab tab3;
    VBox recitationsVBox;
    
    //1.
    HBox recitationsHBox;
    Label recitationsLabel;
    Button deleteRecitationButton;
    
    //2.
    TableView recitationTableView;
    TableColumn sectionTableColumn;
    TableColumn instructorTableColumn;
    TableColumn dayOrTimeTableColumn;
    TableColumn locationTableColumn;
    TableColumn TA1TableColumn;
    TableColumn TA2TableColumn;
        
    //3.
    VBox addOrEditVBox;
    Label addOrEditLabel;
    
    HBox sectionHBox;
    Label sectionLabel;
    TextField sectionTextField;
    
    HBox instructorHBox;
    Label instructorLabel;
    TextField instructorTextField;
        
    HBox dayOrTimeHBox;
    Label dayOrTimeLabel;
    TextField dayOrTimeTextField;
       
    HBox locationHBox;
    Label locationLabel;
    TextField locationTextField;
        
    HBox TA1HBox;
    Label TA1Label;
    ComboBox TA1ComboBox;
        
    HBox TA2HBox;
    Label TA2Label;
    ComboBox TA2ComboBox;
      
    HBox addRecitationHBox;
    Button addRecitationButton;
    Button clearRecitationButton;
        
    //TAB4
    Tab tab4;
    VBox scheduleVBox;
    //1.
    Label scheduleLabel;
    
    //2.
    VBox calendarVBox;
    
    HBox startingMondayHBox;
    Label calendarLabel;
    
    Label startingMondayLabel;
    DatePicker startingDatePicker;
    Label endingFridayLabel;
    DatePicker endingDatePicker;
    //3.
    VBox scheduleItemsVBox;
    
    HBox scheduleItemsHBox;
    Label scheduleItemsLabel;
    Button deleteScheduleButton;
    
    HBox scheduleTableViewHBox;
    TableView scheduleTableView;
    TableColumn typeTableColumn;
    TableColumn dateTableColumn;
    TableColumn titleTableColumn;
    TableColumn topicTableColumn;
        
    HBox addOrEditScheduleHBox;
    Label addOrEditScheduleLabel;
        
    HBox typeOfScheduleHBox;
    Label typeOfScheduleLabel;
    ComboBox typeOfScheduleComboBox;
    
    HBox dateOfScheduleHBox;
    Label dateOfScheduleLabel;
    DatePicker dateOfScheduleDatePicker;
    
    HBox timeOfScheduleHBox;
    Label timeOfScheduleLabel;
    TextField timeOfScheduleTextField;
    
    HBox titleOfScheduleHBox;
    Label titleOfScheduleLabel;
    TextField titleOfScheduleTextField;
        
    HBox topicOfScheduleHBox;
    Label topicOfScheduleLabel;
    TextField topicOfScheduleTextField;
        
    HBox linkOfSchedule;
    Label linkOfScheduleLabel;
    TextField linkOfScheduleTextField;
        
    HBox criteriaOfSchedule;
    Label criteriaOfScheduleLabel;
    TextField criteriaOfScheduleTextField;
        
    HBox addScheduleHBox;
    Button addScheduleButton;
    Button clearScheduleButton;
        
    //TAB5
    Tab tab5;
    VBox projectsVBox;
    //1.
    Label projectsLabel;
            
    //2.
    VBox teamsVBox;
    HBox teamsLabelHBox;
    Label teamsLabel;
    Button deleteTeamButton;
    
    HBox teamTableViewHBox;
    TableView teamTableView;
    TableColumn<Team, String> nameTableColumn;
    TableColumn<Team, String> colorTableColumn;
    TableColumn<Team, String> textColorTableColumn;
    TableColumn<Team, String> linkTableColumn;
    
    HBox addOrEditOfTeamsLabelHBox;
    Label addOrEditOfTeamsLabel;
    
    HBox nameOfTeamsLabelHBox;
    Label nameOfTeamsLabel;
    TextField nameOfTeamsTextField;
    
    HBox colorOfTeamsLabelHBox;
    Label colorOfTeamsLabel;
    ColorPicker colorColorPicker;
    Label textColorOfTeamsLabel;
    ColorPicker textColorColorPicker;
    
    HBox linkOfTeamsLabelHBox;
    Label linkOfTeamsLabel;
    TextField linkOfTeamsTextField;
    
    HBox addTeamButtonHBox;
    Button addTeamButton;
    Button clearTeamButton;
    //3.
    VBox studentsVBox;
   
    HBox studentsLabelHBox;
    Label studentsLabel;
    Button deleteStudentButton;
    
    HBox studentTableViewHBox;
    TableView studentTableView;
    TableColumn<Student, String> firstNameTableColumn; 
    TableColumn<Student, String> lastNameTableColumn; 
    TableColumn<Student, String> teamTableColumn; 
    TableColumn<Student, String> roleTableColumn; 

    HBox addOrEditStudentsLabelHBox;
    Label addOrEditStudentsLabel;
     
    HBox firstNameLabelHBox;
    Label firstNameLabel; 
    TextField firstNameTextField; 
    
    HBox lastNameLabelHBox;   
    Label lastNameLabel; 
    TextField lastNameTextField;
    
    HBox teamLabelHBox; 
    Label teamLabel; 
    ComboBox teamComboBox; 
    
    HBox roleLabelHBox;
    Label roleLabel; 
    TextField roleTextField;
    
    HBox addStudentButtonHBox;
    Button addStudentButton; 
    Button clearStudentButton;
        
    public Workspace(CourseSiteGenerator initApp) 
    {
        workspace = new BorderPane();
        app = initApp;
        controller = new Controller(app);
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        //////////////////////////////////////////////////////////////////////////////
        tabPane = new TabPane();
        
                
        //TAB1===================================================
        tab1 = new Tab();
        
        tab1.setText(props.getProperty(CourseSiteGeneratorProperties.TAB1_LABEL.toString()));
        tab1.setClosable(false);
        courseDetailsVBox= new VBox(); //only 1 VBox in this pane
        
        //FIRST SUB PANE
        courseInfoGridPane= new GridPane(); 
        
        //FIRST line in first sub box
        courseInfoLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.COURSE_INFO_LABEL.toString()));
        
        subjectLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SUBJECT_LABEL.toString()));
        subjectLabel.setPrefWidth(200);
        
        subjectComboBox = new ComboBox();
        subjectComboBox.setEditable(true);
        subjectComboBox.setPrefWidth(100);
        subjectComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.SUBJECT_COMBOBOX_DEFAULT_VALUE.toString()));
        
        numberLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.NUMBER_LABEL.toString()));
        numberLabel.setPrefWidth(200);
        
        numberComboBox = new ComboBox();
        numberComboBox.setEditable(true);
        numberComboBox.setPrefWidth(100);
        numberComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.NUMBER_COMBOBOX_DEFAULT_VALUE.toString()));
        
        HBox subjectHBox = new HBox(subjectLabel, subjectComboBox, numberLabel, numberComboBox);
        subjectHBox.setSpacing(50);
        
        //SECOND line in first sub box
        semesterLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SEMESTER_LABEL.toString()));
        semesterLabel.setPrefWidth(200);
        
        semesterComboBox = new ComboBox();
        semesterComboBox.setEditable(true);
        semesterComboBox.setPrefWidth(100);
        semesterComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.SEMESTER_COMBOBOX_DEFAULT_VALUE.toString()));
        
        yearLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.YEAR_LABEL.toString()));
        yearLabel.setPrefWidth(200);
        
        yearComboBox = new ComboBox();
        yearComboBox.setEditable(true);
        yearComboBox.setPrefWidth(100);
        yearComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.YEAR_COMBOBOX_DEFAULT_VALUE.toString()));
        
        HBox semesterHBox = new HBox(semesterLabel, semesterComboBox, yearLabel, yearComboBox);
        semesterHBox.setSpacing(50);
        
        //Third line in first sub box
        titleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TITLELABEL.toString()));
        titleLabel.setPrefWidth(200);
        
        titleTextField = new TextField();
        titleTextField.setPrefWidth(500);
        titleTextField.setPromptText(props.getProperty(CourseSiteGeneratorProperties.TITLE_TEXTFIELD_PROPMPT_TEXT.toString()));
        
        HBox titleHBox = new HBox(titleLabel, titleTextField);
        titleHBox.setSpacing(50);
        
        //Forth line in first sub box
        instrName = new Label(props.getProperty(CourseSiteGeneratorProperties.INSTR_NAME_LABEL.toString()));
        instrName.setPrefWidth(200);
        
        instrNameTextField = new TextField();
        instrNameTextField.setPrefWidth(500);
        instrNameTextField.setPromptText(props.getProperty(CourseSiteGeneratorProperties.INSTR_NAME_TEXTFIELD_PROMPT_TEXT.toString()));

        HBox instrNameHBox = new HBox(instrName, instrNameTextField);
        instrNameHBox.setSpacing(50);
        
        //Fifth line in first sub box
        instrHome = new Label(props.getProperty(CourseSiteGeneratorProperties.INSTR_HOME_LABEL.toString()));
        instrHome.setPrefWidth(200);
        
        instrHomeTextField = new TextField();
        instrHomeTextField.setPrefWidth(500);
        instrHomeTextField.setPromptText(props.getProperty(CourseSiteGeneratorProperties.INSTR_HOME_TEXTFIELD_PROMPT_TEXT.toString()));

        HBox instrHomeHBox = new HBox(instrHome, instrHomeTextField);
        instrHomeHBox.setSpacing(50);
        
        //Sixth line in first sub box
        exportDirLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.EXPORT_DIR_LABEL.toString()));
        exportDirLabel.setPrefWidth(200);
        
        exportDirectoryLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.EXPORT_DIRECTORY_LABEL.toString()));
        exportDirectoryLabel.setPrefWidth(350);
        
        changeExportDirButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CHANGE_EXPORT_DIR_BUTTON.toString()));
        changeExportDirButton.setPrefWidth(100);
        
        HBox exportDirectoryHBox = new HBox(exportDirLabel, exportDirectoryLabel, changeExportDirButton);
        exportDirectoryHBox.setSpacing(50);
        
        GridPane.setConstraints(courseInfoLabel, 0, 0);
        GridPane.setConstraints(subjectHBox, 0, 1);
        GridPane.setConstraints(semesterHBox, 0, 2);
        GridPane.setConstraints(titleHBox, 0, 3);
        GridPane.setConstraints(instrNameHBox, 0, 4);
        GridPane.setConstraints(instrHomeHBox, 0, 5);
        GridPane.setConstraints(exportDirectoryHBox, 0, 6);
        
        courseInfoGridPane.getChildren().addAll(courseInfoLabel, subjectHBox, semesterHBox, titleHBox, instrNameHBox, instrHomeHBox, exportDirectoryHBox);
        courseInfoGridPane.setVgap(5);
        
        //SECOND SUB VBOX
        siteTemplateGridPane = new GridPane(); 
        
        siteTemplate = new Label(props.getProperty(CourseSiteGeneratorProperties.SITE_TEMPLATE_LABEL.toString()));
        
        siteTemplateNoteLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SITE_TEMPLATE_NOTE_LABEL.toString()));
        
        siteTemplateDirectoryLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SITE_TEMPLATE_DIRECTORY_LABEL.toString()));
        
        siteTemplateDirectoryButton = new Button(props.getProperty(CourseSiteGeneratorProperties.SITE_TEMPLATE_DIRECTORY_BUTTON.toString()));
        
        sitePages = new Label(props.getProperty(CourseSiteGeneratorProperties.SITE_PAGES_LABEL.toString()));
        
        //TAB1 SITE_PAGES TABLEVIEW
        Data data = (Data) app.getDataComponent();
        
        ObservableList<SitePage> sitePageData = data.getSitePages();
        
        
        sitePageTableView = new TableView();
        sitePageTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        sitePageTableView.setMinSize(500, 100);
        
        sitePageTableView.setItems(sitePageData);
        
        
        useTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.USE_TABLECOLUMN.toString()));
        useTableColumn.prefWidthProperty().bind(sitePageTableView.widthProperty().multiply(0.1));
        useTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SitePage, CheckBox>, ObservableValue<CheckBox>>() 
        {
            public ObservableValue<CheckBox> call
            (
                TableColumn.CellDataFeatures<SitePage, CheckBox> arg0) 
                {
                    SitePage sitePage = arg0.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.selectedProperty().setValue(sitePage.getIsUsed());
                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() 
                    {
                        public void changed(ObservableValue<? extends Boolean> ov,Boolean old_val, Boolean new_val) 
                        {
                            sitePage.setIsUsed(new_val);
                            AppGUI gui = app.getGUI();
                            gui.getFileController().markAsEdited(gui);
                        }
                    });
                    return new SimpleObjectProperty<CheckBox>(checkBox);
                }
        });
        
        navBarTitleTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.NAVBAR_TITLE_TABLECOLUMN.toString()));
        navBarTitleTableColumn.prefWidthProperty().bind(sitePageTableView.widthProperty().multiply(0.3));
        navBarTitleTableColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("navbarTitle"));
        
        fileNameTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.FILENAME_TABLECOLUMN.toString()));
        fileNameTableColumn.prefWidthProperty().bind(sitePageTableView.widthProperty().multiply(0.3));
        fileNameTableColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("fileName"));
        
        scriptTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.SCRIPT_TABLECOLUMN.toString()));
        scriptTableColumn.prefWidthProperty().bind(sitePageTableView.widthProperty().multiply(0.3));
        scriptTableColumn.setCellValueFactory(new PropertyValueFactory<SitePage, String>("script"));
        
        
        sitePageTableView.getColumns().addAll(useTableColumn, navBarTitleTableColumn, fileNameTableColumn, scriptTableColumn);
        
        GridPane.setConstraints(siteTemplate, 0, 0);
        GridPane.setConstraints(siteTemplateNoteLabel, 0, 1);
        GridPane.setConstraints(siteTemplateDirectoryLabel, 0, 2);
        GridPane.setConstraints(siteTemplateDirectoryButton, 0, 3);
        GridPane.setConstraints(sitePages, 0, 4);
        GridPane.setConstraints(sitePageTableView, 0, 5);
        
        siteTemplateGridPane.getChildren().addAll(siteTemplate, siteTemplateNoteLabel, siteTemplateDirectoryLabel, siteTemplateDirectoryButton,
                                                  sitePages, sitePageTableView);
        siteTemplateGridPane.setVgap(5);
        
        //THIRD SUB VBOX
        pageStyleVBox= new VBox(); 
        
        //1.
        pageStyle = new Label(props.getProperty(CourseSiteGeneratorProperties.PAGE_STYLE_LABEL.toString()));
        
        //2.
        bannerSchoolHBox = new HBox();
        bannerSchoolHBox.setSpacing(30);
        
        bannerSchoolLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.BANNER_SCHOOL_LABEL.toString()));
        bannerSchoolLabel.setPrefWidth(200);
        
        bannerSchoolImageDir = props.getProperty(CourseSiteGeneratorProperties.SBU_LOGO_IMAGE_DIR.toString());
        Image bannerSchoolImage = new Image(bannerSchoolImageDir);
        
        bannerSchoolImageView = new ImageView();
        bannerSchoolImageView.setImage(bannerSchoolImage);
        bannerSchoolImageView.setFitWidth(200);
        bannerSchoolImageView.setFitHeight(50);
        //bannerSchoolImageView.setPreserveRatio(true);
        
        changeBannerSchoolImageButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CHANGE_BANNER_SCHOOL_IMAGE_BUTTON.toString()));
        changeBannerSchoolImageButton.setPrefWidth(100);
        
        bannerSchoolHBox.getChildren().addAll(bannerSchoolLabel, bannerSchoolImageView, changeBannerSchoolImageButton);
        
        //3.
        leftFooterHBox = new HBox();
        leftFooterHBox.setSpacing(30);
        
        leftFooterLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.LEFT_FOOTER_LABEL.toString()));
        leftFooterLabel.setPrefWidth(200);
        
        leftFooterImageView = new ImageView();
        leftFooterImageView.setImage(bannerSchoolImage);
        leftFooterImageView.setFitWidth(200);
        leftFooterImageView.setFitHeight(50);
        //leftFooterImageView.setPreserveRatio(true);
        
        changeLeftFooterImageButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CHANGE_LEFT_FOOTER_IMAGE_BUTTON.toString()));
        changeLeftFooterImageButton.setPrefWidth(100);
        
        leftFooterHBox.getChildren().addAll(leftFooterLabel, leftFooterImageView, changeLeftFooterImageButton);
        
        //4.
        rightFooterHBox = new HBox();
        rightFooterHBox.setSpacing(30);
        
        rightFooterLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.RIGHT_FOOTER_LABEL.toString()));
        rightFooterLabel.setPrefWidth(200);
        
        rightFooterImageView = new ImageView();
        rightFooterImageView.setImage(bannerSchoolImage);
        rightFooterImageView.setFitWidth(200);
        rightFooterImageView.setFitHeight(50);
        //rightFooterImageView.setPreserveRatio(true);
        
        changeRightFooterImageButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CHANGE_RIGHT_FOOTER_IMAGE_BUTTON.toString()));
        changeRightFooterImageButton.setPrefWidth(100);
        
        rightFooterHBox.getChildren().addAll(rightFooterLabel, rightFooterImageView, changeRightFooterImageButton);
        
        //5.
        HBox styleSheetHBox = new HBox();
        styleSheetHBox.setSpacing(10);
        
        styleSheetLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.STYLE_SHEET_LABEL.toString()));
        
        styleSheetComboBox = new ComboBox();
        styleSheetHBox.getChildren().addAll(styleSheetLabel, styleSheetComboBox);
        
        //6.
        styleSheetNoteLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.STYLE_SHEET_NOTE_LABEL.toString()));
        
        pageStyleVBox.getChildren().addAll( pageStyle,
                                            bannerSchoolHBox,
                                            leftFooterHBox,
                                            rightFooterHBox,
                                            styleSheetHBox,
                                            styleSheetNoteLabel
                                            );
        pageStyleVBox.setSpacing(10);
        
        courseDetailsVBox.getChildren().addAll(courseInfoGridPane, siteTemplateGridPane, pageStyleVBox);
        
        tab1.setContent(courseDetailsVBox);
        //TAB1================================================
        
        //TAB2================================================
        tab2 = new Tab();
        tab2.setText(props.getProperty(CourseSiteGeneratorProperties.TADATA_LABEL.toString()));
        
        TADataHBox = new HBox();
        
        //LEFT PANE
        leftPane = new VBox();
        
        //FOLLOWING 2 NOT USE
        tasHeaderBox = new HBox();
        tasHeaderLabel = new Label();
        
        TALabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TEACHING_ASSISTANT_LABEL.toString()));
        
        Image deleteTAButtonIcon = new Image(FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProperties.DELETE_ICON.toString()));
	ImageView deleteTAButtonImageView = new ImageView(deleteTAButtonIcon);
        
        deleteTAButtonImageView.setFitWidth(100);
        deleteTAButtonImageView.setFitHeight(30);
        deleteTAButtonImageView.setPreserveRatio(true);
        
        deleteTAButton = new Button();
        deleteTAButton.setGraphic(deleteTAButtonImageView);
        deleteTAButton.setPrefSize(100, 30); 
        deleteTAButton.setTooltip(new Tooltip(props.getProperty(CourseSiteGeneratorProperties.DELETE_TA_TOOLTIP.toString())));
        
        TAHBox = new HBox(TALabel, deleteTAButton);
        TAHBox.setSpacing(20);
        
        // MAKE THE TABLE AND SETUP THE DATA MODEL
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        
        taTable = new TableView();
        taTable.setPrefHeight(2000);
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        taTable.setItems(tableData);
        
        nameColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.NAME_COLUMN_TEXT.toString()));
        nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(0.3));
        nameColumn.setCellValueFactory
        (
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        
        emailColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.EMAIL_COLUMN_TEXT.toString()));
        emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(0.5));
        emailColumn.setCellValueFactory
        (
                new PropertyValueFactory<TeachingAssistant, String>(props.getProperty(CourseSiteGeneratorProperties.EMAIL_COLUMN.toString()))
        );
        
        TableColumn isUndergraduated = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.UNDERGRADUATE_COLUMN.toString()));
        isUndergraduated.prefWidthProperty().bind(taTable.widthProperty().multiply(0.2));
        isUndergraduated.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TeachingAssistant, CheckBox>, ObservableValue<CheckBox>>() 
        {
            public ObservableValue<CheckBox> call
            (
                TableColumn.CellDataFeatures<TeachingAssistant, CheckBox> arg0) 
                {
                    TeachingAssistant TA = arg0.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.selectedProperty().setValue(TA.getIsUndergraduated());
                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() 
                    {
                        public void changed(ObservableValue<? extends Boolean> ov,Boolean old_val, Boolean new_val) 
                        {
                            TA.setIsUndergraduated(new_val);
                            AppGUI gui = app.getGUI();
                            gui.getFileController().markAsEdited(gui);
                        }
                    });
                    return new SimpleObjectProperty<CheckBox>(checkBox);
                }
        });
        taTable.getColumns().addAll(isUndergraduated);
        
        taTable.getColumns().add(nameColumn);
        taTable.getColumns().add(emailColumn);
        
        nameTextField = new TextField();
        emailTextField = new TextField();
        nameTextField.setPromptText(props.getProperty(CourseSiteGeneratorProperties.NAME_PROMPT_TEXT.toString()));
        emailTextField.setPromptText(props.getProperty(CourseSiteGeneratorProperties.EMAIL_PROMPT_TEXT.toString()));
        
        addButton = new Button(props.getProperty(CourseSiteGeneratorProperties.ADD_BUTTON_TEXT.toString()));
        addButton.setPrefWidth(100);
        
        clearButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CLEAR_BUTTON_TEXT.toString()));
        clearButton.setPrefWidth(100);
        
        submitButton = new Button(props.getProperty(CourseSiteGeneratorProperties.SUBMIT_BUTTON_TEXT.toString()));
        submitButton.setPrefWidth(100);
        
        addBox = new HBox();
        addBox.setSpacing(10);
        addBox.setPadding(new Insets(10, 10, 40, 10));
        
        addBox.getChildren().addAll(nameTextField, emailTextField, addButton, clearButton);

        leftPane.getChildren().add(TAHBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        
        //RIGHT PANE   
        rightPane = new VBox();
        
        //FOLLOWING 3 LINES NOT USE
        officeHoursHeaderBox = new HBox();
        officeHoursHeaderLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.OFFICE_HOURS_SUBHEADER.toString()));
        officeHoursHeaderBox.getChildren().addAll(officeHoursHeaderLabel);
        
        OfficeHoursHBox = new HBox();
        OfficeHoursHBox.setPadding(new Insets(1, 10, 1, 10));
        OfficeHoursHBox.getChildren().addAll(officeHoursHeaderLabel);
        OfficeHoursHBox.setSpacing(20);
        
        startTimeLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.START_TIME_LABEL_TEXT.toString()));
        startTimeBox = new ComboBox();
        startTimeBox.setEditable(true);
        startTimeBox.getItems().addAll(
        props.getProperty(CourseSiteGeneratorProperties.OPTION_0.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_1.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_2.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_3.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_4.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_5.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_6.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_7.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_8.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_9.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_10.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_11.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_12.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_13.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_14.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_15.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_16.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_17.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_18.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_19.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_20.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_21.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_22.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_23.toString())           
        );
        
        endTimeLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.END_TIME_LABEL_TEXT.toString()));
        endTimeBox = new ComboBox();
        endTimeBox.setEditable(true);
        endTimeBox.getItems().addAll(
        props.getProperty(CourseSiteGeneratorProperties.OPTION_0.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_1.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_2.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_3.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_4.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_5.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_6.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_7.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_8.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_9.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_10.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_11.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_12.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_13.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_14.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_15.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_16.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_17.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_18.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_19.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_20.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_21.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_22.toString()),
        props.getProperty(CourseSiteGeneratorProperties.OPTION_23.toString())        
        );
        
        OfficeHoursHBox.getChildren().addAll(startTimeLabel, startTimeBox, endTimeLabel, endTimeBox, submitButton);

        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();

        
        rightPane.getChildren().add(OfficeHoursHBox);
        rightPane.getChildren().add(officeHoursGridPane);
        
        
        rightScrollPane = new ScrollPane(rightPane);
        
        TADataHBox.getChildren().addAll(leftPane, rightScrollPane);
        tab2.setClosable(false);
        tab2.setContent(TADataHBox);
        //TAB2================================================

        //TAB3================================================
        tab3 = new Tab();
        tab3.setText(props.getProperty(CourseSiteGeneratorProperties.RECITATION_DATA.toString()));
        tab3.setClosable(false);
        
        recitationsVBox = new VBox();
        
        //first HBox
        recitationsHBox = new HBox();
        recitationsHBox.setSpacing(50);
        recitationsHBox.setPadding(new Insets(5, 5, 5, 5));
        
        recitationsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.RECITATION_LABEL.toString()));
        
        Image deleteRecitationButtonIcon = new Image(FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProperties.DELETE_ICON.toString()));
	ImageView deleteRecitationButtonImageView = new ImageView(deleteRecitationButtonIcon);
        
        deleteRecitationButtonImageView.setFitWidth(100);
        deleteRecitationButtonImageView.setFitHeight(30);
        deleteRecitationButtonImageView.setPreserveRatio(true);
        
        deleteRecitationButton = new Button();
        deleteRecitationButton.setGraphic(deleteRecitationButtonImageView);
        deleteRecitationButton.setPrefSize(100, 30); 
        deleteRecitationButton.setTooltip(new Tooltip(props.getProperty(CourseSiteGeneratorProperties.DELETE_RECITATION_TOOLTIP.toString())));
        
        recitationsHBox.getChildren().addAll(recitationsLabel, deleteRecitationButton);
        //second : tableView
        ObservableList<Recitation> recitationData = data.getRecitations();
        
        recitationTableView = new TableView();
        recitationTableView.setPrefSize(600, 300);
        recitationTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        recitationTableView.setItems(recitationData);
        
        sectionTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.SECTION_TABLE_COLUMN.toString()));
        sectionTableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.16));
        sectionTableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("section"));  
        
        instructorTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.INSTRUCTOR_TABLE_COLUMN.toString()));
        instructorTableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.16));
        instructorTableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("instructor"));   
        
        dayOrTimeTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.DAY_OR_TIME_TABLE_COLUMN.toString()));
        dayOrTimeTableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.17));
        dayOrTimeTableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("dayOrTime")); 
        
        locationTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.LOCATION_TABLE_COLUMN.toString()));
        locationTableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.16));
        locationTableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("location")); 
        
        TA1TableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TA1_TABLE_COLUMN.toString()));
        TA1TableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.16));
        TA1TableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("TA1")); 
        
        TA2TableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TA2_TABLE_COLUMN.toString()));
        TA2TableColumn.prefWidthProperty().bind(recitationTableView.widthProperty().multiply(0.16));
        TA2TableColumn.setCellValueFactory(new PropertyValueFactory<Recitation, String>("TA2")); 
        
        
        recitationTableView.getColumns().addAll(sectionTableColumn, instructorTableColumn, dayOrTimeTableColumn,
                                                locationTableColumn, TA1TableColumn, TA2TableColumn);
        
        //Third: Add/Edit Pane
        addOrEditVBox = new VBox();
        addOrEditVBox.setSpacing(20);
                
        addOrEditLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ADD_OR_EDIT_LABEL.toString()));
        //1.
        sectionLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SECTION_LABEL.toString()));
        sectionLabel.setPrefWidth(200);
        
        sectionTextField = new TextField();
        sectionTextField.setText(props.getProperty(CourseSiteGeneratorProperties.SECTION_TEXT_FIELD.toString()));
        sectionTextField.setPrefWidth(300);
        
        sectionHBox = new HBox(sectionLabel, sectionTextField);
        
        //2.
        instructorLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.INSTRUCTOR_LABEL.toString()));
        instructorLabel.setPrefWidth(200);
        
        instructorTextField = new TextField();
        instructorTextField.setText(props.getProperty(CourseSiteGeneratorProperties.INSTRUCTOR_TEXTFIELD.toString()));
        instructorTextField.setPrefWidth(300);
        
        instructorHBox = new HBox(instructorLabel, instructorTextField);
        
        //3.
        dayOrTimeLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.DAY_OR_TIME_LABEL.toString()));
        dayOrTimeLabel.setPrefWidth(200);
        
        dayOrTimeTextField = new TextField();
        dayOrTimeTextField.setText(props.getProperty(CourseSiteGeneratorProperties.DAY_OR_TIME_TEXTFIELD.toString()));
        dayOrTimeTextField.setPrefWidth(300);
        
        dayOrTimeHBox = new HBox(dayOrTimeLabel, dayOrTimeTextField);
        
        //4.
        locationLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.LOCATION_LABEL.toString()));
        locationLabel.setPrefWidth(200);
        
        locationTextField = new TextField();
        locationTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LOCATION_TEXTFIELD.toString()));
        locationTextField.setPrefWidth(300);
        
        locationHBox = new HBox(locationLabel, locationTextField);
        
        //5.
        TA1Label = new Label(props.getProperty(CourseSiteGeneratorProperties.TA1_LABEL.toString()));
        TA1Label.setPrefWidth(200);
        
        ObservableList<String> recitationTAs = data.getRecitationTAs();
        
        TA1ComboBox = new ComboBox(recitationTAs);
        //TA1ComboBox.setEditable(true);
        TA1ComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.TA1_COMBOBOX.toString()));
        TA1ComboBox.setPrefWidth(300);
        
        TA1HBox = new HBox(TA1Label, TA1ComboBox);
        
        //6.
        TA2Label = new Label(props.getProperty(CourseSiteGeneratorProperties.TA2_LABEL.toString()));
        TA2Label.setPrefWidth(200);
        
        TA2ComboBox = new ComboBox(recitationTAs);
        //TA2ComboBox.setEditable(true);
        TA2ComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.TA2_COMBOBOX.toString()));
        TA2ComboBox.setPrefWidth(300);
        
        TA2HBox = new HBox(TA2Label, TA2ComboBox);
        
        //7.
        addRecitationButton = new Button(props.getProperty(CourseSiteGeneratorProperties.ADD_RECITATION_BUTTON.toString()));
        addRecitationButton.setPrefWidth(150);
        
        clearRecitationButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CLEAR_RECITATION_BUTTON.toString()));
        clearRecitationButton.setPrefWidth(150);
        
        addRecitationHBox = new HBox(addRecitationButton, clearRecitationButton);
        addRecitationHBox.setSpacing(50);
        addOrEditVBox.getChildren().addAll(addOrEditLabel,
                                               sectionHBox,
                                               instructorHBox,
                                               dayOrTimeHBox,
                                               locationHBox,
                                               TA1HBox,
                                               TA2HBox,
                                               addRecitationHBox
                                               );
        
        recitationsVBox.getChildren().addAll(recitationsHBox, recitationTableView, addOrEditVBox);
        tab3.setContent(recitationsVBox);
        //TAB3================================================
        
        //TAB4================================================
        tab4 = new Tab();
        tab4.setText(props.getProperty(CourseSiteGeneratorProperties.SCHEDULE_DATA.toString()));
        tab4.setClosable(false);
        
        scheduleVBox = new VBox();
        
        //first
        scheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SCHEDULE_LABEL.toString()));
        
        //second
        calendarVBox = new VBox();
        //1. 
        calendarLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.CALENDAR_LABEL.toString()));
        
        //2.
        startingMondayHBox = new HBox();
        startingMondayHBox.setSpacing(60);
        
        startingMondayLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.STARTING_MONDAY_LABEL.toString()));
        
        startingDatePicker = new DatePicker();
        
        endingFridayLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ENDING_FRIDAY_LABEL.toString()));
        
        endingDatePicker = new DatePicker();
        
        startingMondayHBox.getChildren().addAll(startingMondayLabel, startingDatePicker, endingFridayLabel,  endingDatePicker);
        
        calendarVBox.getChildren().addAll(calendarLabel,
                                          startingMondayHBox);
        
        //third
        scheduleItemsVBox = new VBox();
        scheduleItemsVBox.setSpacing(60);
        
        //1.
        scheduleItemsHBox = new HBox();
        scheduleItemsHBox.setSpacing(60);
        
        scheduleItemsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.SCHEDULE_ITEMS_LABEL.toString()));
        
        Image deleteScheduleButtonIcon = new Image(FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProperties.DELETE_ICON.toString()));
	ImageView deleteScheduleButtonImageView = new ImageView(deleteScheduleButtonIcon);
        
        deleteScheduleButtonImageView.setFitWidth(100);
        deleteScheduleButtonImageView.setFitHeight(30);
        deleteScheduleButtonImageView.setPreserveRatio(true);
        
        deleteScheduleButton = new Button();
        deleteScheduleButton.setGraphic(deleteScheduleButtonImageView);
        deleteScheduleButton.setPrefSize(100, 30); 
        deleteScheduleButton.setTooltip(new Tooltip(props.getProperty(CourseSiteGeneratorProperties.DELETE_SCHEDULE_ITEM_TOOLTIP.toString())));
        
        scheduleItemsHBox.getChildren().addAll(scheduleItemsLabel, deleteScheduleButton);
        
        //2.
        scheduleTableViewHBox = new HBox();
        scheduleTableViewHBox.setSpacing(60);
        
        //SCHEDULE TABLEVIEW 
        ObservableList<Schedule> scheduleData = data.getSchedules();
        
        scheduleTableView = new TableView();
        scheduleTableView.setPrefSize(1000, 400);
        scheduleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        scheduleTableView.setItems(scheduleData);
        
        typeTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TYPE_TABLECOLUMN.toString()));
        typeTableColumn.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.25));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("type"));  
        
        dateTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.DATE_TABLECOLUMN.toString()));
        dateTableColumn.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.25));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("date"));  
        
        titleTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TITLE_TABLECOLUMN.toString()));
        titleTableColumn.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.25));
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("title"));  
        
        topicTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TOPIC_TABLECOLUMN.toString()));
        topicTableColumn.prefWidthProperty().bind(scheduleTableView.widthProperty().multiply(0.25));
        topicTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("topic"));  
        
        
        scheduleTableView.getColumns().addAll(typeTableColumn, dateTableColumn, titleTableColumn, topicTableColumn);
        
        scheduleTableViewHBox.getChildren().addAll(scheduleTableView);
        
        //3.
        addOrEditScheduleHBox = new HBox();
        addOrEditScheduleHBox.setSpacing(60);
        
        addOrEditScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ADD_OR_EDIT_SCHEDULE_LABEL.toString()));
        
        addOrEditScheduleHBox.getChildren().addAll(addOrEditScheduleLabel);
        
        //4.
        typeOfScheduleHBox = new HBox();
        typeOfScheduleHBox.setSpacing(60);
        
        typeOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TYPE_OF_SCHEDULE_LABEL.toString()));
        typeOfScheduleLabel.setPrefWidth(120);
        
        typeOfScheduleComboBox = new ComboBox();
        typeOfScheduleComboBox.getItems().addAll("Holiday", "Lecture", "Recitation", "Reference", "HW");
        //typeOfScheduleComboBox.setEditable(true);
        typeOfScheduleComboBox.setPrefWidth(300);
        typeOfScheduleComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.TYPE_OF_SCHEDULE_COMBOBOX.toString()));
        
        typeOfScheduleHBox.getChildren().addAll(typeOfScheduleLabel, typeOfScheduleComboBox);
        
        //5.
        dateOfScheduleHBox = new HBox();
        dateOfScheduleHBox.setSpacing(60);
        
        dateOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.DATE_OF_SCHEDULE_LABEL.toString()));
        dateOfScheduleLabel.setPrefWidth(120);
        
        dateOfScheduleDatePicker = new DatePicker();
        dateOfScheduleDatePicker.setValue(LocalDate.now());
        
        dateOfScheduleHBox.getChildren().addAll(dateOfScheduleLabel, dateOfScheduleDatePicker);
        
        //6.
        timeOfScheduleHBox = new HBox();
        timeOfScheduleHBox.setSpacing(60);
        
        timeOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TIME_OF_SCHEDULE_LABLE.toString()));
        timeOfScheduleLabel.setPrefWidth(120);
        
        timeOfScheduleTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.TIME_OF_SCHEDULE_TEXTFIELD.toString()));
        timeOfScheduleTextField.setPrefWidth(300);
        
        timeOfScheduleHBox.getChildren().addAll(timeOfScheduleLabel, timeOfScheduleTextField);
        
        //7.
        titleOfScheduleHBox = new HBox();
        titleOfScheduleHBox.setSpacing(60);
        
        titleOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TITLE_OF_SCHEDULE_LABEL.toString()));
        titleOfScheduleLabel.setPrefWidth(120);
        
        titleOfScheduleTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.TITLE_OF_SCHEDULE_TEXTFIELD.toString()));
        titleOfScheduleTextField.setPrefWidth(600);
        
        titleOfScheduleHBox.getChildren().addAll(titleOfScheduleLabel, titleOfScheduleTextField);
        
        //8.
        topicOfScheduleHBox = new HBox();
        topicOfScheduleHBox.setSpacing(60);
        
        topicOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TOPIC_OF_SCHEDULE_LABLE.toString()));
        topicOfScheduleLabel.setPrefWidth(120);
        
        topicOfScheduleTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.TOPIC_OF_SCHEDULE_TEXTFIELD.toString()));
        topicOfScheduleTextField.setPrefWidth(600);
        
        topicOfScheduleHBox.getChildren().addAll(topicOfScheduleLabel, topicOfScheduleTextField);
        
        //9.
        linkOfSchedule = new HBox();
        linkOfSchedule.setSpacing(60);
        
        linkOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_SCHEDULE_LABEL.toString()));
        linkOfScheduleLabel.setPrefWidth(120);
        
        linkOfScheduleTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_SCHEDULE_TEXTFIELD.toString()));
        linkOfScheduleTextField.setPrefWidth(600);
        
        linkOfSchedule.getChildren().addAll(linkOfScheduleLabel, linkOfScheduleTextField);
        
        //10.
        criteriaOfSchedule = new HBox();
        criteriaOfSchedule.setSpacing(60);
        
        criteriaOfScheduleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.CRITERIA_OF_SCHEDULE_LABEL.toString()));
        criteriaOfScheduleLabel.setPrefWidth(120);

        criteriaOfScheduleTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.CRITERIA_OF_SCHEDULE_TEXTFIELD.toString()));
        criteriaOfScheduleTextField.setPrefWidth(600);
        
        criteriaOfSchedule.getChildren().addAll(criteriaOfScheduleLabel, criteriaOfScheduleTextField);
        
        //11.
        addScheduleHBox = new HBox();
        addScheduleHBox.setSpacing(60);
        
        addScheduleButton = new Button(props.getProperty(CourseSiteGeneratorProperties.ADD_SCHEDULE_BUTTON.toString()));
        addScheduleButton.setPrefWidth(120);
        
        clearScheduleButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CLEAR_SCHEDULE_BUTTON.toString()));
        clearScheduleButton.setPrefWidth(120);
        
        addScheduleHBox.getChildren().addAll(addScheduleButton, clearScheduleButton);
        
        scheduleItemsVBox.getChildren().addAll( scheduleItemsHBox,
                                                scheduleTableViewHBox, 
                                                addOrEditScheduleHBox, 
                                                typeOfScheduleHBox,
                                                dateOfScheduleHBox,
                                                timeOfScheduleHBox,
                                                titleOfScheduleHBox,
                                                topicOfScheduleHBox,
                                                linkOfSchedule,
                                                criteriaOfSchedule,
                                                addScheduleHBox
                                                );
        
        scheduleVBox.getChildren().addAll(scheduleLabel, calendarVBox, scheduleItemsVBox);
        tab4.setContent(scheduleVBox);
        //TAB4================================================
        
        //TAB5================================================
        tab5 = new Tab();
        tab5.setText(props.getProperty(CourseSiteGeneratorProperties.PROJECT_DATA.toString()));
        tab5.setClosable(false);
        
        projectsVBox = new VBox();
        
        //1.
        projectsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.PROJECTS_LABEL.toString()));
        
        //2.
        teamsVBox = new VBox();
        
        //1.
        teamsLabelHBox = new HBox();
        teamsLabelHBox.setSpacing(10);
        
        teamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TEAMS_LABEL.toString()));
        
        Image deleteTeamButtonIcon = new Image(FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProperties.DELETE_ICON.toString()));
	ImageView deleteTeamButtonImageView = new ImageView(deleteTeamButtonIcon);
        
        deleteTeamButtonImageView.setFitWidth(20);
        deleteTeamButtonImageView.setFitHeight(20);
        deleteTeamButtonImageView.setPreserveRatio(true);
        
        deleteTeamButton = new Button();
        deleteTeamButton.setGraphic(deleteTeamButtonImageView);
        deleteTeamButton.setMaxSize(10, 10); 
        deleteTeamButton.setTooltip(new Tooltip(props.getProperty(CourseSiteGeneratorProperties.DELETE_TEAM_TOOLTIP.toString())));
        
        teamsLabelHBox.getChildren().addAll(teamsLabel, deleteTeamButton);
        
        //2.
        teamTableViewHBox = new HBox();
        
        //TEAM TABLE_VIEW
        ObservableList<Team> teamData = data.getTeams();
        
        teamTableView = new TableView();
        teamTableView.setPrefSize(800, 300);
        teamTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        teamTableView.setItems(teamData);
        
        nameTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.NAME_TABLECOLUMN.toString()));
        nameTableColumn.prefWidthProperty().bind(teamTableView.widthProperty().multiply(0.25));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamName"));  
        
        colorTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.COLOR_TABLECOLUMN.toString()));
        colorTableColumn.prefWidthProperty().bind(teamTableView.widthProperty().multiply(0.25));
        colorTableColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamColor"));  
        
        textColorTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TEXT_COLOR_TABLECOLUMN.toString()));
        textColorTableColumn.prefWidthProperty().bind(teamTableView.widthProperty().multiply(0.25));
        textColorTableColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamTextColor"));  
        
        linkTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.LINK_TABLECOLUMN.toString()));
        linkTableColumn.prefWidthProperty().bind(teamTableView.widthProperty().multiply(0.25));
        linkTableColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("teamLink"));  
        
        
        teamTableView.getColumns().addAll(nameTableColumn, colorTableColumn, textColorTableColumn, linkTableColumn);
        
        teamTableViewHBox.getChildren().addAll(teamTableView);
        
        //3.
        addOrEditOfTeamsLabelHBox = new HBox();
        addOrEditOfTeamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ADD_OR_EDIT_TEAMS_LABEL.toString()));
        
        addOrEditOfTeamsLabelHBox.getChildren().addAll(addOrEditOfTeamsLabel);
        
        //4.
        nameOfTeamsLabelHBox = new HBox();
        
        nameOfTeamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.NAME_OF_TEAMS_LABEL.toString()));
        nameOfTeamsLabel.setPrefWidth(130);
        
        nameOfTeamsTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.NAME_OF_TEAMS_TEXTFIELD.toString()));
        nameOfTeamsTextField.setPrefWidth(200);
        
        nameOfTeamsLabelHBox.getChildren().addAll(nameOfTeamsLabel, nameOfTeamsTextField);
        
        //5.
        colorOfTeamsLabelHBox = new HBox();
        colorOfTeamsLabelHBox.setSpacing(10);
        
        colorOfTeamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.COLOR_OF_TEAMS_LABEL.toString()));
        colorOfTeamsLabel.setPrefWidth(120);
        
        colorColorPicker = new ColorPicker();
        colorColorPicker.setValue(Color.BLUE);
        colorColorPicker.setPrefWidth(200);
        colorColorPicker.setMinHeight(30);
        
        textColorOfTeamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TEXT_COLOR_OF_TEAMS_LABLE.toString()));
        textColorOfTeamsLabel.setPrefWidth(120);
        
        textColorColorPicker = new ColorPicker();
        textColorColorPicker.setPrefWidth(200);
        textColorColorPicker.setMinHeight(30);
        
        colorOfTeamsLabelHBox.getChildren().addAll(colorOfTeamsLabel, colorColorPicker, textColorOfTeamsLabel, textColorColorPicker);
        
        //6.
        linkOfTeamsLabelHBox = new HBox();
        
        linkOfTeamsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_TEAMS_LABEL.toString()));
        linkOfTeamsLabel.setPrefWidth(130);
        
        linkOfTeamsTextField = new TextField(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_TEAMS_TEXTFIELD.toString()));
        linkOfTeamsTextField.setPrefWidth(400);
        
        linkOfTeamsLabelHBox.getChildren().addAll(linkOfTeamsLabel, linkOfTeamsTextField);
        
        //7.
        addTeamButtonHBox = new HBox();
        addTeamButtonHBox.setSpacing(10);
        
        addTeamButton = new Button(props.getProperty(CourseSiteGeneratorProperties.ADD_TEAM_BUTTON.toString()));
        addTeamButton.setPrefWidth(120);
        
        clearTeamButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CLEAR_TEAM_BUTTON.toString()));
        clearTeamButton.setPrefWidth(120);
        
        addTeamButtonHBox.getChildren().addAll(addTeamButton, clearTeamButton);
        
        teamsVBox.getChildren().addAll( teamsLabelHBox,
                                        teamTableViewHBox,
                                        addOrEditOfTeamsLabelHBox,
                                        nameOfTeamsLabelHBox,
                                        colorOfTeamsLabelHBox,
                                        linkOfTeamsLabelHBox,
                                        addTeamButtonHBox
                                        );
                
        //3.
        studentsVBox = new VBox();
        
        //1.
        studentsLabelHBox = new HBox();
        studentsLabelHBox.setSpacing(10);
        studentsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.STUDENTS_LABEL.toString()));
        
        Image deleteStudentButtonIcon = new Image(FILE_PROTOCOL + PATH_IMAGES + props.getProperty(CourseSiteGeneratorProperties.DELETE_ICON.toString()));
	ImageView deleteStudentButtonImageView = new ImageView(deleteStudentButtonIcon);
        
        deleteStudentButtonImageView.setFitWidth(20);
        deleteStudentButtonImageView.setFitHeight(20);
        deleteStudentButtonImageView.setPreserveRatio(true);
        
        deleteStudentButton = new Button();
        deleteStudentButton.setGraphic(deleteStudentButtonImageView);
        deleteStudentButton.setPrefSize(10, 10); 
        deleteStudentButton.setTooltip(new Tooltip(props.getProperty(CourseSiteGeneratorProperties.DELETE_STUDENT_TOOLTIP.toString())));
        
        studentsLabelHBox.getChildren().addAll(studentsLabel, deleteStudentButton);
        
        //2.
        studentTableViewHBox = new HBox();
        
        //STUDENT TABLE_VIEW
        ObservableList<Student> studentData = data.getStudents();
        
        studentTableView = new TableView();
        studentTableView.setPrefSize(800, 300);
        studentTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        studentTableView.setItems(studentData);
        
        firstNameTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.FIRST_NAME_TABLECOLUMN.toString()));
        firstNameTableColumn.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.25));
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));  
        
        lastNameTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.LAST_NAME_TABLECOLUMN.toString()));
        lastNameTableColumn.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.25));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));  
        
        teamTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.TEAM_TABLECOLUMN.toString()));
        teamTableColumn.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.25));
        teamTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("team"));  
        
        roleTableColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProperties.ROLE_TABLECOLUMN.toString()));
        roleTableColumn.prefWidthProperty().bind(studentTableView.widthProperty().multiply(0.25));
        roleTableColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("role"));  
        
        
        studentTableView.getColumns().addAll(firstNameTableColumn, lastNameTableColumn, teamTableColumn, roleTableColumn);
        
        studentTableViewHBox.getChildren().addAll(studentTableView);
        
        //3.
        addOrEditStudentsLabelHBox = new HBox();
        addOrEditStudentsLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ADD_OR_EDIT_STUDENTS_LABEL.toString()));
        
        addOrEditStudentsLabelHBox.getChildren().addAll(addOrEditStudentsLabel);
        
        //4.
        firstNameLabelHBox = new HBox();
        
        firstNameLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.FIRST_NAME_LABEL.toString()));
        firstNameLabel.setPrefWidth(130);
        
        firstNameTextField = new TextField();
        firstNameTextField.setPrefWidth(200);
        firstNameTextField.setText(props.getProperty(CourseSiteGeneratorProperties.FIRST_NAME_TEXTFIELD.toString()));
        
        firstNameLabelHBox.getChildren().addAll(firstNameLabel, firstNameTextField);
        
        //5.a
        lastNameLabelHBox = new HBox();
        
        lastNameLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.LAST_NAME_LABEL.toString()));
        lastNameLabel.setPrefWidth(130);
        
        lastNameTextField = new TextField();
        lastNameTextField.setPrefWidth(200);
        lastNameTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LAST_NAME_TEXTFIELD.toString()));
        
        lastNameLabelHBox.getChildren().addAll(lastNameLabel, lastNameTextField);
        
        //6.
        teamLabelHBox = new HBox();
        
        teamLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.TEAM_LABEL.toString()));
        teamLabel.setPrefWidth(130);
        
        ObservableList<String> teamsOfStudents = data.getTeamsOfStudents();
        
        teamComboBox = new ComboBox(teamsOfStudents);
        //teamComboBox.setEditable(true);
        teamComboBox.setPrefWidth(200);
        //teamComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.TEAM_COMBOBOX.toString()));
        
        teamLabelHBox.getChildren().addAll(teamLabel, teamComboBox);
        
        //7.
        roleLabelHBox = new HBox();
        
        roleLabel = new Label(props.getProperty(CourseSiteGeneratorProperties.ROLE_LABEL.toString()));
        roleLabel.setPrefWidth(130);
        
        roleTextField = new TextField();
        roleTextField.setPrefWidth(200);
        roleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.ROLE_TEXTFIELD.toString()));
        
        roleLabelHBox.getChildren().addAll(roleLabel, roleTextField);
        
        //8.
        addStudentButtonHBox = new HBox();
        addStudentButtonHBox.setSpacing(10);
        
        addStudentButton = new Button(props.getProperty(CourseSiteGeneratorProperties.ADD_STUDENT_BUTTON.toString()));
        addStudentButton.setPrefWidth(120);
        
        clearStudentButton = new Button(props.getProperty(CourseSiteGeneratorProperties.CLEAR_STUDENT_BUTTON.toString()));
        clearStudentButton.setPrefWidth(120);
        
        addStudentButtonHBox.getChildren().addAll(addStudentButton, clearStudentButton);
        
        studentsVBox.getChildren().addAll(  studentsLabelHBox,
                                            studentTableViewHBox,
                                            addOrEditStudentsLabelHBox, 
                                            firstNameLabelHBox,
                                            lastNameLabelHBox,
                                            teamLabelHBox,
                                            roleLabelHBox,
                                            addStudentButtonHBox
                                            );
                
        projectsVBox.getChildren().addAll(projectsLabel, teamsVBox, studentsVBox);
        tab5.setContent(projectsVBox);
        //TAB5================================================
        
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);
        //////////////////////////////////////////////////////////////////////////////
        
        ((BorderPane) workspace).setCenter(tabPane);
        
        //GET APP TOOLBAR FROM APP'S GUI
        app.getGUI().getUndoButton().setOnAction(e -> {controller.handleUndo();});
        app.getGUI().getRedoButton().setOnAction(e -> {controller.handleRedo();});	
        app.getGUI().getAboutButton().setOnAction(e -> {controller.handleAbout();});
        
        //TAB1
        subjectComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeSubjectComboBox(newValue.toString());});
        numberComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeNumberComboBox(newValue.toString());});
        semesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeSemesterComboBox(newValue.toString());});
        yearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeYearComboBox(newValue.toString());});
        titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeTitleTextField(newValue.toString());});
        instrNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeInstrNameTextField(newValue.toString());});
        instrHomeTextField.textProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeInstrHomeTextField(newValue.toString());});
       
        changeExportDirButton.setOnMouseClicked(e-> {controller.handleChangeExportDir();});
        
        
        siteTemplateDirectoryButton.setOnMouseClicked(e-> {controller.handleChangeTemplateDir();});
        
        changeBannerSchoolImageButton.setOnMouseClicked(e-> {controller.handleChangeBannerSchoolImage();});
        changeLeftFooterImageButton.setOnMouseClicked(e-> {controller.handleChangeLeftFooterImage();});
        changeRightFooterImageButton.setOnMouseClicked(e-> {controller.handleChangeRightFooterImage();});
        

        styleSheetComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleChangeStyleSheet(newValue.toString());});

        
        
        //TAB2
        
        // CONTROLS FOR ADDING TAs
        nameTextField.setOnAction(e -> {controller.handleAddTA();});
        emailTextField.setOnAction(e -> {controller.handleAddTA();});
        
        addButton.setOnAction(e -> {controller.handleAddTA();});
        clearButton.setOnAction(e -> {controller.handleClear();});
        deleteTAButton.setOnMouseClicked(e -> controller.handleDeleteTA());
        taTable.setOnKeyPressed(e -> {controller.handleKeyPress(e.getCode());});
        taTable.setOnMouseClicked(e ->{controller.handleEditTA();});
        taTable.setFocusTraversable(true);
        
        submitButton.setOnAction(e->{controller.handleEditTime();});
        
        
        
        //TAB3
        addRecitationButton.setOnAction(e -> {controller.handleAddRecitation();});
        clearRecitationButton.setOnAction(e -> {controller.handleClearEditRecitation();});
        deleteRecitationButton.setOnMouseClicked(e -> controller.handleDeleteRecitation());
        recitationTableView.setOnMouseClicked(e ->{controller.handleEditRecitation();});
        recitationTableView.setOnKeyPressed(e -> {controller.handleKeyPressRecitation(e.getCode());});
        recitationTableView.setFocusTraversable(true);
        
        //TAB4
        startingDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleEditStartingMonday(oldValue, newValue);});
        endingDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {controller.handleEditEndingFriday(oldValue, newValue);});

        
        addScheduleButton.setOnAction(e -> {controller.handleAddSchedule();});
        clearScheduleButton.setOnAction(e -> {controller.handleClearEditSchedule();});
        deleteScheduleButton.setOnMouseClicked(e -> controller.handleDeleteSchedule());
        scheduleTableView.setOnMouseClicked(e ->{controller.handleEditSchedule();});
        scheduleTableView.setOnKeyPressed(e -> {controller.handleKeyPressSchedule(e.getCode());});
        scheduleTableView.setFocusTraversable(true);
        
        
        //TAB5
        addTeamButton.setOnAction(e -> {controller.handleAddTeam();});
        clearTeamButton.setOnAction(e -> {controller.handleClearEditTeam();});
        deleteTeamButton.setOnMouseClicked(e -> controller.handleDeleteTeam());
        teamTableView.setOnMouseClicked(e ->{controller.handleEditTeam();});
        teamTableView.setOnKeyPressed(e -> {controller.handleKeyPressTeam(e.getCode());});
        teamTableView.setFocusTraversable(true);
        
        
        addStudentButton.setOnAction(e -> {controller.handleAddStudent();});
        clearStudentButton.setOnAction(e -> {controller.handleClearEditStudent();});
        deleteStudentButton.setOnMouseClicked(e -> controller.handleDeleteStudent());
        studentTableView.setOnMouseClicked(e ->{controller.handleEditStudent();});
        studentTableView.setOnKeyPressed(e -> {controller.handleKeyPressStudent(e.getCode());});
        studentTableView.setFocusTraversable(true);
        
        
        
        
        
        workspace.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.Z && e.isControlDown()) 
            {
                controller.handleUndo();
            }
            else if(e.getCode() == KeyCode.Y && e.isControlDown()) 
            {
                controller.handleRedo();
            }
        });
    }
    
    public TabPane getTabPane(){ return tabPane;}
    
    //TAB1
    public Tab getTab1(){ return tab1;}  
    public VBox getCourseDetailsVBox() {return courseDetailsVBox;}
    //FIRST SUB PANE
    public GridPane getCourseInfoGridPane() {return courseInfoGridPane;}
    public Label getCourseInfoLabel() {return courseInfoLabel;}
    public Label getSubjectLabel() {return subjectLabel;}
    public ComboBox getSubjectComboBox() {return subjectComboBox;}
    public Label getNumberLabel() {return numberLabel;}
    public ComboBox getNumberComboBox() {return numberComboBox;}
    public Label getSemesterLabel() {return semesterLabel;}
    public ComboBox getSemesterComboBox() {return semesterComboBox;}
    public Label getYearLabel() {return yearLabel;}
    public ComboBox getYearComboBox() {return yearComboBox;}
    public Label getTitleLabel() {return titleLabel;}
    public TextField getTitleTextField() {return titleTextField;}
    public Label getInstrNameLabel() {return instrName;}
    public TextField getInstrNameTextField() {return instrNameTextField;}
    public Label getInstrHomeLabel() {return instrHome;}
    public TextField getInstrHomeTextField() {return instrHomeTextField;}
    public Label getExportDirLabel() {return exportDirLabel;}
    public Button getChangeExportDirButton() {return changeExportDirButton;}
    public Label getExportDirectoryLabel() {return exportDirectoryLabel;}
    
    //SECOND SUB PANE
    public GridPane getSiteTemplateGridPane() {return siteTemplateGridPane;}
    public Label getSiteTemplateLabel() {return siteTemplate;}
    public Label getSiteTemplateNoteLabel() {return siteTemplateNoteLabel;}
    public Label getSiteTemplateDirectoryLabel() {return siteTemplateDirectoryLabel;}
    public Button getSiteTemplateDirectoryButton() {return siteTemplateDirectoryButton;}
    public Label getSitePagesLabel() {return sitePages;}
    public TableView getSitePageTableView() {return sitePageTableView;}
    public TableColumn getUseTableColumn() {return useTableColumn;}
    public TableColumn getNavBarTitleTableColumn() {return navBarTitleTableColumn;}
    public TableColumn getFileNameTableColumn() {return fileNameTableColumn;}
    public TableColumn getscriptTableColumn() {return scriptTableColumn;}
    
    //THIRD SUB PANE
    public VBox getPageStyleVBox() {return pageStyleVBox;}
    public Label getPageStyleLabel() {return pageStyle;}
    
    public Label getBannerSchoolLabel() {return bannerSchoolLabel;}
    public void setBannerSchoolImageDir(String newDir){bannerSchoolImageDir = newDir;}
    public String getBannerSchoolImageDir(){return bannerSchoolImageDir;}
    public ImageView getBannerSchoolImageView() {return bannerSchoolImageView;}
    public Button getChangeBannerSchoolImageButton() {return changeBannerSchoolImageButton;}
    
    public Label getLeftFooterLabel() {return leftFooterLabel;}
    public void setLeftFooterImageDir(String newDir){leftFooterImageDir = newDir;}
    public String getLeftFooterImageDir(){return leftFooterImageDir;}
    public ImageView getLeftFooterImageView() {return leftFooterImageView;}
    public Button getChangeLeftFooterImageButton() {return changeLeftFooterImageButton;}
    
    public Label getRightFooterLabel() {return rightFooterLabel;}
    public void setRightFooterImageDir(String newDir){rightFooterImageDir = newDir;}
    public String getrightFooterImageDir(){return rightFooterImageDir;}
    public ImageView getRightFooterImageView() {return rightFooterImageView;}
    public Button getChangeRightFooterImageButton() {return changeRightFooterImageButton;}
    
    public Label getStyleSheetLabel() {return styleSheetLabel;}
    public ComboBox getStyleSheetComboBox(){return styleSheetComboBox;}
    public Label getStyleSheetNoteLabel() {return styleSheetNoteLabel;}
    
    public void updateBannerSchoolImage(String newDir)
    {
        bannerSchoolImageDir = newDir;
        Image bannerSchoolImage = new Image(bannerSchoolImageDir);
            
        bannerSchoolImageView.setImage(bannerSchoolImage);
    }
    
    public void updateLeftFooterImage(String newDir)
    {
            leftFooterImageDir = newDir;
            Image leftFooterImage = new Image(leftFooterImageDir);
            
            leftFooterImageView.setImage(leftFooterImage);
    }
    
    public void updateRightFooterImage(String newDir)
    {
            rightFooterImageDir = newDir;
            Image rightFooterImage = new Image(rightFooterImageDir);
            
            rightFooterImageView.setImage(rightFooterImage);
    }
    
    public void updateStyleSheetComboBox(String s){styleSheetComboBox.setValue(s);}
    
    
    //TAB2
    public Tab getTab2(){ return tab2;}  
    
    //LEFT PANE
    public VBox getLeftPane() {return leftPane;}
    public Label getTALabel() {return TALabel;}
    public Button getDeleteTAButton() {return deleteTAButton;}
    public HBox getTAHBox() {return TAHBox;}
    
    public HBox getTADataHbox() {return TADataHBox;}
    public HBox getTAsHeaderBox() {return tasHeaderBox;}
    public Label getTAsHeaderLabel() {return tasHeaderLabel;}
    public TableView getTATable() {return taTable;}
    public HBox getAddBox() {return addBox;}
    public TextField getNameTextField() {return nameTextField;}
    public TextField getEmailTextField() {return emailTextField;}
    public Button getAddButton() {return addButton;}
    public Button getClearButton() {return clearButton;}
    public HBox getOfficeHoursSubheaderBox() {return officeHoursHeaderBox;}
    public ComboBox getStartTimeBox() {return startTimeBox;}
    public ComboBox getEndTimeBox(){return endTimeBox;}
    
    //RIGHT PANE
    public VBox getRightPane() {return rightPane;}
    public HBox getOfficeHoursHBox() {return OfficeHoursHBox;}
    public ScrollPane getRightScrollPane() {return rightScrollPane;}
    
    public Label getStartTimeLabel() {return startTimeLabel;}
    public Label getEndTimeLabel() {return endTimeLabel;}
    
    public Label getOfficeHoursSubheaderLabel() {return officeHoursHeaderLabel;}
    public GridPane getOfficeHoursGridPane() {return officeHoursGridPane;}
    public HashMap<String, Pane> getOfficeHoursGridTimeHeaderPanes() {return officeHoursGridTimeHeaderPanes;}
    public HashMap<String, Label> getOfficeHoursGridTimeHeaderLabels() {return officeHoursGridTimeHeaderLabels;}
    public HashMap<String, Pane> getOfficeHoursGridDayHeaderPanes() {return officeHoursGridDayHeaderPanes;}
    public HashMap<String, Label> getOfficeHoursGridDayHeaderLabels() {return officeHoursGridDayHeaderLabels;}
    public HashMap<String, Pane> getOfficeHoursGridTimeCellPanes() {return officeHoursGridTimeCellPanes;}
    public HashMap<String, Label> getOfficeHoursGridTimeCellLabels() {return officeHoursGridTimeCellLabels;}
    public HashMap<String, Pane> getOfficeHoursGridTACellPanes() {return officeHoursGridTACellPanes;}
    public HashMap<String, Label> getOfficeHoursGridTACellLabels() {return officeHoursGridTACellLabels;}
    
    //TAB3
    public Tab getTab3(){ return tab3;}
    public VBox getRecitationsVBox() {return recitationsVBox;}
    public TableView getRecitationTableView() {return recitationTableView;}
    //1.
    public HBox getRecitationsHBox() {return recitationsHBox;}
    public Label getRecitationLabel() {return recitationsLabel;}
    public Button getDeleteRecitationButton() {return deleteRecitationButton;}
    
    //3.
    public VBox getAddOrEditVBox() {return addOrEditVBox;}
    public Label getAddOrEditLabel() {return addOrEditLabel;}
    
    public HBox getSectionHBox() {return sectionHBox;}
    public Label getSectionLabel() {return sectionLabel;}
    public TextField getSectionTextField() {return sectionTextField;}
    
    public HBox getInstructorHBox() {return instructorHBox;}
    public Label getInstructorLabel() {return instructorLabel;}
    public TextField getInstructorTextField() {return instructorTextField;}
    
    public HBox getDayOrTimeHBox() {return dayOrTimeHBox;}
    public Label getdayOrTimeLabel() {return dayOrTimeLabel;}
    public TextField getDayOrTimeTextField() {return dayOrTimeTextField;}
    
    public HBox getLocationHBox() {return locationHBox;}
    public Label getLocationLabel() {return locationLabel;}
    public TextField getLocationTextField() {return locationTextField;}
    
    public HBox getTA1HBox() {return TA1HBox;}
    public Label getTA1Label() {return TA1Label;}
    public ComboBox getTA1ComboBox() {return TA1ComboBox;}
    
    public HBox getTA2HBox() {return TA2HBox;}
    public Label getTA2Label() {return TA2Label;}
    public ComboBox getTA2ComboBox() {return TA2ComboBox;}
    
    public HBox getAddRecitationHBox() {return addRecitationHBox;}
    public Button getAddRecitationButton() {return addRecitationButton;}
    public Button getClearRecitationButton() {return clearRecitationButton;}
    
    //TAB4
    public Tab getTab4(){ return tab4;}   
    public VBox getScheduleVBox() {return scheduleVBox;}
    
    //1.
    public Label getScheduleLabel() {return scheduleLabel;}
    
    //2.
    public VBox getCalendarVBox() {return calendarVBox;}
    public HBox getStartingMondayHBox() {return startingMondayHBox;}
    public Label getCalendarLabel() {return calendarLabel;}
    public Label getStartingMondayLabel() {return startingMondayLabel;}
    public DatePicker getStartingDatePicker() {return startingDatePicker;}
    public Label getEndingFridayLabel() {return endingFridayLabel;}
    public DatePicker getEndingDatePicker() {return endingDatePicker;}
    
    public void updateStartingMondayDatePicker(String y, String m, String d)
    {
        try
        {
            int year = Integer.parseInt(y);
            int month = Integer.parseInt(m);
            int day = Integer.parseInt(d);
            startingDatePicker.setValue(LocalDate.of(year, month, day));
        }
        catch(Exception e)
        {
            startingDatePicker.setValue(LocalDate.of(2017, 1, 2));
        }
    }
    
    public void updateEndingFridayDatePicker(String y, String m, String d)
    {
        try
        {
            int year = Integer.parseInt(y);
            int month = Integer.parseInt(m);
            int day = Integer.parseInt(d);
            endingDatePicker.setValue(LocalDate.of(year, month, day));
        }
        catch(Exception e)
        {
            endingDatePicker.setValue(LocalDate.of(2017, 5, 5));
        }
    }
    //3.
    public VBox getScheduleItemsVBox() {return scheduleItemsVBox;}
    
    public HBox getScheduleItemsHBox() {return scheduleItemsHBox;}
    public Label getScheduleItemsLabel() {return scheduleItemsLabel;}
    public Button getDeleteScheduleButton() {return deleteScheduleButton;}
    
    public HBox getScheduleTableViewHBox() {return scheduleTableViewHBox;}
    public TableView getSchduleTableView() {return scheduleTableView;}
    public TableColumn getTypeTableColumn() {return typeTableColumn;}
    public TableColumn getDateTableColumn() {return dateTableColumn;}
    public TableColumn getTitleTableColumn() {return titleTableColumn;}
    public TableColumn getTopicTableColumn() {return topicTableColumn;}
    
    public HBox getAddOrEditScheduleHBox() {return addOrEditScheduleHBox;}
    public Label getAddOrEditScheduleLabel() {return addOrEditScheduleLabel;}
    
    public HBox getTypeOfScheduleHBox() {return typeOfScheduleHBox;}
    public Label getTypeOfScheduleLabel() {return typeOfScheduleLabel;}
    public ComboBox getTypeOfScheduleComboBox() {return typeOfScheduleComboBox;}
    
    public HBox getDateOfScheduleHBox() {return dateOfScheduleHBox;}
    public Label getDateOfScheduleLabel() {return dateOfScheduleLabel;}
    public DatePicker getDateOfScheduleDatePicker() {return dateOfScheduleDatePicker;}
    
    public HBox getTimeOfScheduleHBox() {return timeOfScheduleHBox;}
    public Label getTimeOfScheduleLabel() {return timeOfScheduleLabel;}
    public TextField getTimeOfScheduleTextField() {return timeOfScheduleTextField;}
    
    public HBox getTitleOfScheduleHBox() {return titleOfScheduleHBox;}
    public Label getTitleOfScheduleLabel() {return titleOfScheduleLabel;}
    public TextField getTitleOfScheduleTextField() {return titleOfScheduleTextField;}
    
    public HBox getTopicOfScheduleHBox() {return topicOfScheduleHBox;}
    public Label getTopicOfScheduleLabel() {return topicOfScheduleLabel;}
    public TextField getTopicOfScheduleTextField() {return topicOfScheduleTextField;}
    
    public HBox getLinkOfSchedule() {return linkOfSchedule;}
    public Label getLinkOfScheduleLabel() {return linkOfScheduleLabel;}
    public TextField getLinkOfScheduleTextField() {return linkOfScheduleTextField;}
    
    public HBox getCriteriaOfSchedule() {return criteriaOfSchedule;}
    public Label getCriteriaOfScheduleLabel() {return criteriaOfScheduleLabel;}
    public TextField getCriteriaOfScheduleTextField() {return criteriaOfScheduleTextField;}
    
    public HBox getAddScheduleHBox() {return addScheduleHBox;}
    public Button getAddScheduleButton() {return addScheduleButton;}
    public Button getClearScheduleButton() {return clearScheduleButton;}
    
    //TAB5
    public Tab getTab5(){ return tab5;}   
    public VBox getProjectsVBox() {return projectsVBox;}
    
    public VBox getTeamsVBox() {return teamsVBox;}
    public VBox getStudentsVBox() {return studentsVBox;}

    //1.
    public Label getProjectsLabel() {return projectsLabel;}
 
    //2.
    public Label getTeamsLabel() {return teamsLabel;}
    public Button getDeleteTeamButton() {return deleteTeamButton;}
 
    public TableView getTeamTableView() {return teamTableView;}
    public TableColumn getNameTableColumn() {return nameTableColumn;}
    public TableColumn getColorTableColumn() {return colorTableColumn;}
    public TableColumn getTextColorTableColumn() {return textColorTableColumn;}
    public TableColumn getLinkTableColumn() {return linkTableColumn;}
 
    public Label getAddOrEditOfTeamsLabel() {return addOrEditOfTeamsLabel;}
    
    public Label getNameOfTeamsLabel() {return nameOfTeamsLabel;}
    public TextField getNameOfTeamsTextField() {return nameOfTeamsTextField;}
    
    public Label getColorOfTeamsLabel() {return colorOfTeamsLabel;}
    public ColorPicker getColorColorPicker() {return colorColorPicker;}
    public Label getTextColorOfTeamsLabel() {return textColorOfTeamsLabel;}
    public ColorPicker getTextColorColorPicker() {return textColorColorPicker;}
    
    public Label getLinkOfTeamsLabel() {return linkOfTeamsLabel;}
    public TextField getLinkOfTeamsTextField() {return linkOfTeamsTextField;}
    
    public Button getAddTeamButton() {return addTeamButton;}
    public Button getClearTeamButton() {return clearTeamButton;}
    
    //3.
    public Label getStudentsLabel() {return studentsLabel;}
    public Button getDeleteStudentButton() {return deleteStudentButton;}
    
    public TableView getStudentTableView() {return studentTableView;}
    public TableColumn getFirstNameTableColumn() {return firstNameTableColumn;}
    public TableColumn getLastNameTableColumn() {return lastNameTableColumn;}
    public TableColumn getTeamTableColumn() {return teamTableColumn;}
    public TableColumn getRoleTableColumn() {return roleTableColumn;}
    
    public Label getAddOrEditStudentsLabel() {return addOrEditStudentsLabel;}
    
    public Label getFirstNameLabel() {return firstNameLabel;}
    public TextField getFirstNameTextField() {return firstNameTextField;}
    
    public Label getLastNameLabel() {return lastNameLabel;}
    public TextField getLastNameTextField() {return lastNameTextField;}
    
    public Label getTeamLabel() {return teamLabel;}
    public ComboBox getTeamComboBox() {return teamComboBox;}
    
    public Label getRoleLabel() {return roleLabel;}
    public TextField getRoleTextField() {return roleTextField;}
    
    public Button getAddStudentButton() {return addStudentButton;}
    public Button getClearStudentButton() {return clearStudentButton;}
    
    
    public String getCellKey(Pane testPane) {
        for (String key : officeHoursGridTACellLabels.keySet()) {
            if (officeHoursGridTACellPanes.get(key) == testPane) {
                return key;
            }
        }
        return null;
    }

    public Label getTACellLabel(String cellKey) {
        return officeHoursGridTACellLabels.get(cellKey);
    }

    public Pane getTACellPane(String cellPane) {
        return officeHoursGridTACellPanes.get(cellPane);
    }

    public String buildCellKey(int col, int row) {
        return "" + col + "_" + row;
    }

    public String buildCellText(int militaryHour, String minutes) {
        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutes;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    } 

    @Override
    public void resetWorkspace() 
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        //TAB1
        workspace.subjectComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.SUBJECT_COMBOBOX_DEFAULT_VALUE.toString()));
        workspace.numberComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.NUMBER_COMBOBOX_DEFAULT_VALUE.toString()));
        workspace.semesterComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.SEMESTER_COMBOBOX_DEFAULT_VALUE.toString()));
        workspace.yearComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.YEAR_COMBOBOX_DEFAULT_VALUE.toString()));
        workspace.titleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.TITLE_TEXTFIELD_PROPMPT_TEXT.toString()));
        workspace.instrNameTextField.setText(props.getProperty(CourseSiteGeneratorProperties.INSTR_NAME_TEXTFIELD_PROMPT_TEXT.toString()));
        workspace.instrHomeTextField.setText(props.getProperty(CourseSiteGeneratorProperties.INSTR_HOME_TEXTFIELD_PROMPT_TEXT.toString()));
        workspace.exportDirectoryLabel.setText(props.getProperty(CourseSiteGeneratorProperties.EXPORT_DIRECTORY_LABEL.toString()));
        
        workspace.siteTemplateDirectoryLabel.setText(props.getProperty(CourseSiteGeneratorProperties.SITE_TEMPLATE_DIRECTORY_LABEL.toString()));
        
        workspace.updateBannerSchoolImage(props.getProperty(CourseSiteGeneratorProperties.BANNER_SCHOOL_IMAGE_DIR.toString()));
        workspace.updateLeftFooterImage(props.getProperty(CourseSiteGeneratorProperties.LEFT_FOOTER_IMAGE_DIR.toString()));
        workspace.updateRightFooterImage(props.getProperty(CourseSiteGeneratorProperties.RIGHT_FOOTER_IMAGE_DIR.toString()));
        
        
        workspace.styleSheetComboBox.getItems().clear();
        //workspace.styleSheetComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.STYLE_SHEET_COMBOBOX.toString()));
        
        //TAB2
        controller.handleClear();
        
        //initialize the startTime and endTime
        workspace.getStartTimeBox().setValue(workspace.getStartTimeBox().getItems().get(9));
        workspace.getEndTimeBox().setValue(workspace.getEndTimeBox().getItems().get(20));
                
        // CLEAR OUT THE GRID PANE
        officeHoursGridPane.getChildren().clear();
        
        // AND THEN ALL THE GRID PANES AND LABELS
        officeHoursGridTimeHeaderPanes.clear();
        officeHoursGridTimeHeaderLabels.clear();
        officeHoursGridDayHeaderPanes.clear();
        officeHoursGridDayHeaderLabels.clear();
        officeHoursGridTimeCellPanes.clear();
        officeHoursGridTimeCellLabels.clear();
        officeHoursGridTACellPanes.clear();
        officeHoursGridTACellLabels.clear();
        
        //TAB3
        workspace.sectionTextField.setText(props.getProperty(CourseSiteGeneratorProperties.SECTION_TEXT_FIELD.toString()));
        workspace.instructorTextField.setText(props.getProperty(CourseSiteGeneratorProperties.INSTRUCTOR_TEXTFIELD.toString()));
        workspace.dayOrTimeTextField.setText(props.getProperty(CourseSiteGeneratorProperties.DAY_OR_TIME_TEXTFIELD.toString()));
        workspace.locationTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LOCATION_TEXTFIELD.toString()));
        workspace.TA1ComboBox.setValue(/*props.getProperty(CourseSiteGeneratorProperties.TA1_COMBOBOX.toString())*/"");
        workspace.TA2ComboBox.setValue(/*props.getProperty(CourseSiteGeneratorProperties.TA2_COMBOBOX.toString())*/"");
        
        
        //TAB4
        workspace.startingDatePicker.setValue(LocalDate.of(2017, 1, 2));
        workspace.endingDatePicker.setValue(LocalDate.of(2017, 5, 19));  
        
       
        workspace.typeOfScheduleComboBox.setValue(props.getProperty(CourseSiteGeneratorProperties.SCHEDULE_ITEMTYPE_HOLIDAY.toString()));
        workspace.dateOfScheduleDatePicker.setValue(LocalDate.of(2017, 1, 1));
        workspace.timeOfScheduleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.TIME_OF_SCHEDULE_TEXTFIELD.toString()));
        workspace.titleOfScheduleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.TITLE_OF_SCHEDULE_TEXTFIELD.toString()));
        workspace.topicOfScheduleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.TOPIC_OF_SCHEDULE_TEXTFIELD.toString()));
        workspace.linkOfScheduleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_SCHEDULE_TEXTFIELD.toString()));
        workspace.criteriaOfScheduleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.CRITERIA_OF_SCHEDULE_TEXTFIELD.toString()));
        
        
        //TAB5
        workspace.nameOfTeamsTextField.setText(props.getProperty(CourseSiteGeneratorProperties.NAME_OF_TEAMS_TEXTFIELD.toString()));
        workspace.colorColorPicker.setValue(Color.BLUE);
        workspace.textColorColorPicker.setValue(Color.WHITE);
        workspace.linkOfTeamsTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LINK_OF_TEAMS_TEXTFIELD.toString()));
        
        workspace.firstNameTextField.setText(props.getProperty(CourseSiteGeneratorProperties.FIRST_NAME_TEXTFIELD.toString()));
        workspace.lastNameTextField.setText(props.getProperty(CourseSiteGeneratorProperties.LAST_NAME_TEXTFIELD.toString()));
        
        
        
        workspace.teamComboBox.getItems().clear();
        //workspace.teamComboBox.setValue("");
        
        workspace.roleTextField.setText(props.getProperty(CourseSiteGeneratorProperties.ROLE_TEXTFIELD.toString()));
    }
    
    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) 
    {
        Data taData = (Data)dataComponent;
        reloadOfficeHoursGrid(taData);
    }

    public void reloadOfficeHoursGrid(Data dataComponent) {        
        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        // THEN THE TIME AND TA CELLS
        int row = 1;
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) 
        {
            // START TIME COLUMN
            int col = 0;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            col++;

            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) 
            {
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row);
                addCellToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1);
                col++;
            }
            row += 2;
        }

        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        Style taStyle = (Style)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();
    }
    
    public void addCellToGrid(Data dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row) {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
    
    public void addTAToGrid(Data dataComponent, HashMap<String, Pane> panes, HashMap<String, Label> labels, int col, int row, int difference) 
    {       
        // MAKE THE LABEL IN A PANE
        Label cellLabel = new Label("");
        HBox cellPane = new HBox();
        cellPane.setAlignment(Pos.CENTER);
        cellPane.getChildren().add(cellLabel);

        // BUILD A KEY TO EASILY UNIQUELY IDENTIFY THE CELL
        String cellKey = dataComponent.getCellKey(col, row);
        cellPane.setId(cellKey);
        cellLabel.setId(cellKey);
        
        // NOW PUT THE CELL IN THE WORKSPACE GRID
        officeHoursGridPane.add(cellPane, col, row);
        
        // AND ALSO KEEP IN IN CASE WE NEED TO STYLIZE IT
        panes.put(cellKey, cellPane);
        labels.put(cellKey, cellLabel);
        
        // AND FINALLY, GIVE THE TEXT PROPERTY TO THE DATA MANAGER
        // SO IT CAN MANAGE ALL CHANGES
        //the old officeHours HashMap is destroyed from here
        dataComponent.setCellProperty(col, row, cellLabel.textProperty());        
    }
    
    public void updateOfficeHoursGrid(Data dataComponent, int difference, int startTime, int endTime) 
    {
        // THEN THE TIME AND TA CELLS
        int row = 1;
        
        //make a new updated officeHours based on the current officeHours
        HashMap<String, StringProperty> newOfficeHours = dataComponent.getNewOfficeHours(difference, startTime, endTime);
        
        officeHoursGridPane.getChildren().clear(); //clear the entire officeHoursGridPane first

        ArrayList<String> gridHeaders = dataComponent.getGridHeaders();

        // ADD THE TIME HEADERS
        for (int i = 0; i < 2; i++) {
            addCellToGrid(dataComponent, officeHoursGridTimeHeaderPanes, officeHoursGridTimeHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));
        }
        
        // THEN THE DAY OF WEEK HEADERS
        for (int i = 2; i < 7; i++) {
            addCellToGrid(dataComponent, officeHoursGridDayHeaderPanes, officeHoursGridDayHeaderLabels, i, 0);
            dataComponent.getCellTextProperty(i, 0).set(gridHeaders.get(i));            
        }
        
        //iterate endHour - startHour times
        for (int i = dataComponent.getStartHour(); i < dataComponent.getEndHour(); i++) 
        {
            // START TIME COLUMN
            int col = 0;
            //first row
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(i, "00"));//set the time on the pane
            //second row
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(i, "30"));

            // END TIME COLUMN
            col++;
            int endHour = i;
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row);
            dataComponent.getCellTextProperty(col, row).set(buildCellText(endHour, "30"));
            
            addCellToGrid(dataComponent, officeHoursGridTimeCellPanes, officeHoursGridTimeCellLabels, col, row+1);
            dataComponent.getCellTextProperty(col, row+1).set(buildCellText(endHour+1, "00"));
            
            //Monday column to Friday column
            col++;
            // AND NOW ALL THE TA TOGGLE CELLS
            while (col < 7) 
            {
                //ADD MODIFIED TA TO GRID
                addTAToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row, difference);
                //dataComponent.getCellTextProperty(col, row).set(dataComponent.getCellKey(col, row));
                addTAToGrid(dataComponent, officeHoursGridTACellPanes, officeHoursGridTACellLabels, col, row+1, difference);
                //dataComponent.getCellTextProperty(col, row+1).set(dataComponent.getCellKey(col, row+1));
                col++;
            }
            row += 2;
        }
        
        //go through the whole newOfficeHours and put them all in the gridPane
        for(String key : newOfficeHours.keySet())
        {
            dataComponent.getOfficeHours().get(key).set(newOfficeHours.get(key).getValue());
        }
        
        // CONTROLS FOR TOGGLING TA OFFICE HOURS
        for (Pane p : officeHoursGridTACellPanes.values()) {
            p.setFocusTraversable(true);
            p.setOnKeyPressed(e -> {
                controller.handleKeyPress(e.getCode());
            });
            p.setOnMouseClicked(e -> {
                controller.handleCellToggle((Pane) e.getSource());
            });
            p.setOnMouseExited(e -> {
                controller.handleGridCellMouseExited((Pane) e.getSource());
            });
            p.setOnMouseEntered(e -> {
                controller.handleGridCellMouseEntered((Pane) e.getSource());
            });
        }
        
        // AND MAKE SURE ALL THE COMPONENTS HAVE THE PROPER STYLE
        Style taStyle = (Style)app.getStyleComponent();
        taStyle.initOfficeHoursGridStyle();
        
        
    }
}
