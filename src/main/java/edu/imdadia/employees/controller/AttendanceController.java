package edu.imdadia.employees.controller;

import edu.imdadia.employees.config.StageManager;
import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.AttendanceEntity;
import edu.imdadia.employees.entity.EmployeeEntity;
import edu.imdadia.employees.enumuration.FxmlView;
import edu.imdadia.employees.repository.AttendanceRepo;
import edu.imdadia.employees.services.*;
import edu.imdadia.employees.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class AttendanceController implements Initializable {

    private final AttendanceService attendanceService;
    private final StageManager stageManager;

    @FXML
    private TableView<AttendanceEntity> attendanceTable;

    @FXML
    private TableColumn<AttendanceEntity, String> idColum;

    @FXML
    private TableColumn<AttendanceEntity, String> nameColum;

    @FXML
    private TableColumn<AttendanceEntity, String> typeColum;

    @FXML
    private TableColumn<AttendanceEntity, String> conditionColum;

    @FXML
    private TableColumn<AttendanceEntity, String> dateColum;


    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<String> choiceType;

    @FXML
    private ChoiceBox<String> choiceCondition;
    private final AttendanceRepo attendanceRepo;
    private final AdminServiceImpl adminService;
    private final EmployeeServiceImpl employeeService;

    public AttendanceController(AttendanceService attendanceService, @Lazy StageManager stageManager, AttendanceRepo attendanceRepo, AdminServiceImpl adminService, EmployeeServiceImpl employeeService) {
        this.attendanceService = attendanceService;
        this.stageManager = stageManager;
        this.attendanceRepo = attendanceRepo;

        this.adminService = adminService;
        this.employeeService = employeeService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final List<String> choiceTypeList = new ArrayList<>();
        choiceTypeList.add("Employee");
        choiceTypeList.add("Admin");

        final List<String> choiceConditionList = new ArrayList<>();
        choiceConditionList.add("Present");
        choiceConditionList.add("Leave");
        choiceConditionList.add("Absent");

        choiceType.getItems().addAll(choiceTypeList);
        choiceCondition.getItems().addAll(choiceConditionList);
        setUpTable();

    }

    private void setUpTable() {
        ObservableList<AttendanceEntity> adminEntityList = FXCollections.observableArrayList(attendanceRepo.findAll());
        attendanceTable.setItems(adminEntityList);
        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getWorkerId())));
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkerName()));
        this.typeColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getWorkerType())));
        this.conditionColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAttendanceCondition())));
//        this.dateColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate())));
    }

    @FXML
    public void save() {
        if (nameField.getText().equals("")||idField.getText().equals("")||choiceType.getValue().equals("")||choiceCondition.getValue().equals("")||date.getValue().equals("")){
            JavaFXUtils.showError("Any field should not be null");
        }else{
            if (choiceType.getValue().equalsIgnoreCase("Admin")){
                AttendanceEntity attendanceEntity = new AttendanceEntity();
                Optional<AdminEntity> adminEntity1 = adminService.findByAdminId(Integer.valueOf(idField.getText()));
               AdminEntity adminEntity =adminEntity1.orElse(null);
                if (adminEntity!=null){
                    if (adminEntity.getAdminName().equals(nameField.getText())){
                        attendanceEntity.setWorkerName(adminEntity.getAdminName());
                        attendanceEntity.setWorkerId(adminEntity.getAdminId());
                        attendanceEntity.setWorkerType(choiceType.getValue());
                        attendanceEntity.setAttendanceCondition(choiceCondition.getValue());
                        attendanceService.save(attendanceEntity);
                        JavaFXUtils.showSuccessMessage("attendance added successfully");
                        setUpTable();
                        clear();
                        String date1=String.valueOf(date.getValue());
                        String[] dateArray=date1.split("-");
                        attendanceEntity.setYear(Integer.valueOf(dateArray[0]));
                        attendanceEntity.setMonth(Integer.valueOf(dateArray[1]));
                        attendanceEntity.setDay(Integer.valueOf(dateArray[2]));
                    }else{
                        JavaFXUtils.showError("Admin not found or "+nameField.getText()+" not at id "+idField.getText());
                    }
                }else{
                    JavaFXUtils.showError("There is no Admin with id "+idField.getText());
                }

            } else if (choiceType.getValue().equalsIgnoreCase("employee")) {
                AttendanceEntity attendanceEntity = new AttendanceEntity();
                Optional<EmployeeEntity> employeeEntity1 = employeeService.findByEmployeeId(Integer.valueOf(idField.getText()));
                EmployeeEntity employeeEntity = employeeEntity1.orElse(null);
                if (employeeEntity!=null){
                 if (employeeEntity.getEmployeeName().equals(nameField.getText())){
                     attendanceEntity.setWorkerName(employeeEntity.getEmployeeName());
                     attendanceEntity.setWorkerId(employeeEntity.getEmployeeId());
                     attendanceEntity.setWorkerType(choiceType.getValue());
                     attendanceEntity.setAttendanceCondition(choiceCondition.getValue());
                     attendanceService.save(attendanceEntity);
                     JavaFXUtils.showSuccessMessage("attendance added successfully");
                     setUpTable();
                     clear();
                     String date1=String.valueOf(date.getValue());
                     String[] dateArray=date1.split("-");
                     attendanceEntity.setYear(Integer.valueOf(dateArray[0]));
                     attendanceEntity.setMonth(Integer.valueOf(dateArray[1]));
                     attendanceEntity.setDay(Integer.valueOf(dateArray[2]));
                 }else{
                     JavaFXUtils.showError("Employee not found or "+nameField.getText()+" not at id "+idField.getText());
                 }
             }else{
                 JavaFXUtils.showError("There is no employee with id "+idField.getText());
             }

            }else {
                JavaFXUtils.showError("Employee not found");
            }

        }



    }

    @FXML
    public void back() {
        stageManager.switchScene(FxmlView.HOME);
    }

    @FXML
    public void clear() {
        idField.clear();
        nameField.clear();
    }
    @FXML
    public void currentAdmin(){
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }
}
