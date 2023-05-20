package edu.imdadia.employees.controller;

import edu.imdadia.employees.config.StageManager;
import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.entity.EmployeeEntity;
import edu.imdadia.employees.enumuration.FxmlView;
import edu.imdadia.employees.repository.AdminRepo;
import edu.imdadia.employees.repository.EmployeeRepo;
import edu.imdadia.employees.services.EmployeeService;
import edu.imdadia.employees.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class EmployeesController implements Initializable {

    private final StageManager stageManager;
    private final EmployeeService employeeService;
    @FXML
    private TableView<EmployeeEntity> employeesTable;
    @FXML
    private TableColumn<EmployeeEntity, String> idColum;
    @FXML
    private TableColumn<EmployeeEntity, String> nameColum;
    @FXML
    private TableColumn<EmployeeEntity, String> fatherNameColum;
    @FXML
    private TableColumn<EmployeeEntity, String> addressColum;
    @FXML
    private TableColumn<EmployeeEntity, String> phoneNumberColum;
    @FXML
    private TableColumn<EmployeeEntity, String> salaryColum;
    @FXML
    private TableColumn<EmployeeEntity, String> idCardColum;

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField confirmPasswordTxt;
    @FXML
    private CheckBox showPasswordBox;
    @FXML
    private TextField address;
    @FXML
    private TextField salary;
    @FXML
    private TextField idCard;
    private final EmployeeRepo employeeRepo;
    private final AdminRepo adminRepo;

    @FXML
    public void addButton() {
       if (id.getText().equals("")||name.getText().equals("")||fatherName.getText().equals("")||passwordField.getText().equals("")||confirmPasswordField.getText().equals("")||phoneNumber.getText().equals("")||salary.getText().equals("")){
           JavaFXUtils.showWarningMessage("Any field of the following fields should not be null ID  , Name , Father Name , IDCard number , Password , Confirm password ,phone Number , salary");
       }else{
           EmployeeEntity employee = new EmployeeEntity();
           employee.setEmployeeId(Integer.valueOf(id.getText()));
           employee.setEmployeeName(name.getText());
           employee.setFatherName(fatherName.getText());
           employee.setAddress(address.getText());
           employee.setIdCardNumber(idCard.getText());
           employee.setSalary(Double.valueOf(salary.getText()));
           employee.setPhoneNumber(phoneNumber.getText());
           employee.setPassword(passwordField.getText());
           Optional<EmployeeEntity> employeeEntity = employeeService.findByIdCardNumber(Integer.valueOf(idCard.getText()));
           if (employeeEntity != null) {
               employeeService.save(employee);
               JavaFXUtils.showSuccessMessage("Employee added successfully");
               this.clearFields();
           } else {
               JavaFXUtils.showError("Employee with Id card number " + idCard.getText() + " already added");
           }
           setUpTable();
       }
    }


    @FXML
    public void editButton() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeId(Integer.valueOf(id.getText()));
        employee.setEmployeeName(name.getText());
        employee.setFatherName(fatherName.getText());
        employee.setAddress(address.getText());
        employee.setIdCardNumber(idCard.getText());
        employee.setSalary(Double.valueOf(salary.getText()));
        employee.setPhoneNumber(phoneNumber.getText());
        employeeService.save(employee);
        addButton();
    }

    public EmployeesController(@Lazy StageManager stageManager, EmployeeService employeeService, EmployeeRepo employeeRepo, AdminRepo adminRepo) {
        this.stageManager = stageManager;
        this.employeeService = employeeService;
        this.employeeRepo = employeeRepo;
        this.adminRepo = adminRepo;
    }


    public void clearFields() {
        id.clear();
        name.clear();
        fatherName.clear();
        idCard.clear();
        address.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        phoneNumber.clear();
        salary.clear();
        passwordText.clear();
        confirmPasswordTxt.clear();
    }


    @FXML
    public void back() {
        stageManager.switchScene(FxmlView.HOME);

    }

    @FXML
    private void showPasswordBox() {
        if (showPasswordBox.isSelected()) {
            confirmPasswordTxt.setText(confirmPasswordField.getText());
            confirmPasswordField.setVisible(false);
            confirmPasswordTxt.setVisible(true);
            passwordText.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordText.setVisible(true);
            return;
        } else {
            confirmPasswordTxt.setVisible(false);
            confirmPasswordField.setVisible(true);
            passwordText.setVisible(false);
            passwordField.setVisible(true);
        }
    }

    @FXML
    public void logOut() {
        LoginController.adminEntity = null;
        stageManager.switchScene(FxmlView.LOGIN);
    }

    private void setUpTable() {
        ObservableList<EmployeeEntity> employeeEntityObservableList = FXCollections.observableArrayList(employeeRepo.findAll());
        employeesTable.setItems(employeeEntityObservableList);
        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEmployeeId())));
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeName()));
        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
        this.addressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress())));
        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
        this.salaryColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalary())));
        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
    }

    public void removeButton() {
        employeeRepo.deleteById(Integer.valueOf(id.getText()));
        JavaFXUtils.showSuccessMessage("Employee With " + id.getText() + " Deleted Successfully");
        setUpTable();
    }


    @FXML
    public void searchByIdButton() {
        Optional<EmployeeEntity> s = employeeRepo.findByEmployeeId(Integer.valueOf(id.getText()));
        EmployeeEntity employeeEntity = s.orElse(null);
        if (employeeEntity != null) {
            id.setText(String.valueOf(employeeEntity.getEmployeeId()));
            name.setText(employeeEntity.getEmployeeName());
            fatherName.setText(employeeEntity.getFatherName());
            phoneNumber.setText(String.valueOf(employeeEntity.getPhoneNumber()));
            address.setText(employeeEntity.getAddress());
            salary.setText(String.valueOf(employeeEntity.getSalary()));
            idCard.setText(String.valueOf(employeeEntity.getIdCardNumber()));
        } else {
            JavaFXUtils.showError("Employee with Id " + id.getText() + " does not exist");
            clearFields();
        }
    }

    @FXML
    public void searchByNameButton() {
        Optional<EmployeeEntity> s = employeeRepo.findByEmployeeNameIgnoreCase(name.getText());
        EmployeeEntity employeeEntity = s.orElse(null);
        if (employeeEntity != null) {
            name.setText(String.valueOf(employeeEntity.getEmployeeName()));
            id.setText(String.valueOf(employeeEntity.getEmployeeId()));
            fatherName.setText(employeeEntity.getFatherName());
            phoneNumber.setText(String.valueOf(employeeEntity.getPhoneNumber()));
            address.setText(employeeEntity.getAddress());
            salary.setText(String.valueOf(employeeEntity.getSalary()));
            idCard.setText(String.valueOf(employeeEntity.getIdCardNumber()));
        } else {
            JavaFXUtils.showError("Employee with Name " + name.getText() + " does not exist");
            clearFields();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTable();
    }
    @FXML
    public void currentAdmin(){
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }
}

