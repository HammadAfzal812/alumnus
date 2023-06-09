package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.SpringFXMLLoader;
import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AdminEntity;
import edu.imdadia.alumnus.enumuration.FxmlView;
import edu.imdadia.alumnus.repository.AdminRepo;
import edu.imdadia.alumnus.services.AdminService;
import edu.imdadia.alumnus.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class AdminController implements Initializable {

    private final StageManager stageManager;
    private final AdminService adminService;
    @FXML
    private TableView<AdminEntity> adminsTable;
    @FXML
    private TableColumn<AdminEntity, String> idColum;
    @FXML
    private TableColumn<AdminEntity, String> nameColum;
    @FXML
    private TableColumn<AdminEntity, String> fatherNameColum;
    @FXML
    private TableColumn<AdminEntity, String> addressColum;
    @FXML
    private TableColumn<AdminEntity, String> phoneNumberColum;
    @FXML
    private TableColumn<AdminEntity, String> idCardColum;
    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private TextField name;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTxt;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField confirmPasswordTxt;
    @FXML
    private TextField id;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private CheckBox showConfirmPasswordBox;
    @FXML
    private TextField address;
    @FXML
    private TextField idCard;
    private final AdminRepo adminRepo;
    private AdminEntity searchedAdmin;
    @FXML
    private final SpringFXMLLoader springFXMLLoader;


    @FXML
    public void addButton() {
        if (id.getText().equals("") || name.getText().equals("") || fatherName.getText().equals("") || idCard.getText().equals("") || passwordField.getText().equals("") || confirmPasswordField.getText().equals("") || phoneNumber.getText().equals("")) {
            JavaFXUtils.showWarningMessage("Any field of the following fields should not be null ID  , Name , Father Name , IDCard number , Password , Confirm password ,phone number ");
        } else {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setAdminId(Integer.valueOf(id.getText()));
                adminEntity.setAdminName(name.getText());
                adminEntity.setFatherName(fatherName.getText());
                adminEntity.setAddress(address.getText());
                adminEntity.setPassword(passwordField.getText());
                adminEntity.setPhoneNumber(phoneNumber.getText());
                adminEntity.setIdCardNumber(idCard.getText());
                adminService.save(adminEntity);
                JavaFXUtils.showSuccessMessage("Admin added successfully");
                this.clearFields();
                setUpTable();
            } else {
                JavaFXUtils.showError("password confirm password not match");
            }
        }
    }

    @FXML
    public void searchByIdButton() {
        Optional<AdminEntity> s = adminRepo.findByAdminId(Integer.valueOf(id.getText()));
        AdminEntity adminEntity = s.orElse(null);
        if (adminEntity != null) {
            searchedAdmin = adminEntity;
            id.setText(String.valueOf(searchedAdmin.getAdminId()));
            name.setText(searchedAdmin.getAdminName());
            fatherName.setText(searchedAdmin.getFatherName());
            phoneNumber.setText(String.valueOf(searchedAdmin.getPhoneNumber()));
            address.setText(searchedAdmin.getAddress());
            idCard.setText(String.valueOf(searchedAdmin.getIdCardNumber()));
        } else {
            JavaFXUtils.showError("Admin with Id " + id.getText() + " does not exist");
            clearFields();
        }
    }

    @FXML
    public void searchByNameButton() {
        Optional<AdminEntity> s = adminRepo.findByAdminNameIgnoreCase(name.getText());
        AdminEntity adminEntity = s.orElse(null);
        if (adminEntity != null) {
            searchedAdmin = adminEntity;
            name.setText(String.valueOf(searchedAdmin.getAdminName()));
            id.setText(String.valueOf(searchedAdmin.getAdminId()));
            fatherName.setText(searchedAdmin.getFatherName());
            phoneNumber.setText(String.valueOf(searchedAdmin.getPhoneNumber()));
            address.setText(searchedAdmin.getAddress());
            idCard.setText(String.valueOf(searchedAdmin.getIdCardNumber()));
        } else {
            JavaFXUtils.showError("Admin with Name " + name.getText() + " does not exist");
            clearFields();
        }
    }


    public AdminController(@Lazy StageManager stageManager, AdminService adminService, AdminRepo adminRepo, SpringFXMLLoader springFXMLLoader) {
        this.stageManager = stageManager;
        this.adminService = adminService;
        this.adminRepo = adminRepo;
        this.springFXMLLoader = springFXMLLoader;
        this.searchedAdmin = searchedAdmin;
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
        passwordTxt.clear();
        confirmPasswordTxt.clear();
    }


    @FXML
    public void back() {
        stageManager.switchScene(FxmlView.HOME);
    }

    @FXML
    public void showConfirmPasswordBox() {
        if (showConfirmPasswordBox.isSelected()) {
            confirmPasswordTxt.setText(confirmPasswordField.getText());
            confirmPasswordField.setVisible(false);
            confirmPasswordTxt.setVisible(true);
            passwordTxt.setText(passwordField.getText());
            passwordField.setVisible(false);
            passwordTxt.setVisible(true);
            return;
        }
        confirmPasswordTxt.setVisible(false);
        confirmPasswordField.setVisible(true);
        passwordTxt.setVisible(false);
        passwordField.setVisible(true);
    }

    @FXML
    public void logOut() {
        LoginController.adminEntity = null;
        stageManager.switchScene(FxmlView.LOGIN);
    }

    private void setUpTable() {
        ObservableList<AdminEntity> adminEntityList = FXCollections.observableArrayList(adminRepo.findAll());
        adminEntityList.stream().sorted();
        adminsTable.setItems(adminEntityList);
        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAdminId())));
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdminName()));
        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
        this.addressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress())));
        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
    }

    @FXML
    public void removeButton() {
        adminService.deleteById(Integer.valueOf(id.getText()));
        JavaFXUtils.showSuccessMessage("Admin With " + id.getText() + " Deleted Successfully");
        setUpTable();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTable();
    }

    @FXML
    public void currentAdmin() {
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }
}
