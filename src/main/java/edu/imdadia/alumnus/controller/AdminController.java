package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.SpringFXMLLoader;
import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AdminEntity;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import edu.imdadia.alumnus.enumuration.FxmlView;
import edu.imdadia.alumnus.repository.AdminRepo;
import edu.imdadia.alumnus.services.AdminService;
import edu.imdadia.alumnus.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
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
    private TableColumn<AdminEntity, String> userNameColum;
    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private TextField name;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTxt;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField confirmPasswordTxt;
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
        if ( name.getText().equals("") || fatherName.getText().equals("") || idCard.getText().equals("") || phoneNumber.getText().equals("") || address.getText().equals("")|| userName.getText().equals("") )  {
            JavaFXUtils.showWarningMessage("Any field of the following fields should not be null ID  , Name , Father Name , IDCard number , Password , Confirm password ,phone number ");
        } else if (adminService.findByUserName(userName.getText()).orElse(null)!=null||adminService.findByIdCardNumber(idCard.getText()).orElse(null)!=null){
            JavaFXUtils.showError("User name already defined please use any other user name or Id card number already defiene");
            userName.requestFocus();
        }else if (showConfirmPasswordBox.isSelected()) {
            if (passwordTxt.equals("") || confirmPasswordTxt.equals("")) {
                JavaFXUtils.showWarningMessage("password and confirm password required");
            } else if (passwordTxt.getText().equals(confirmPasswordTxt.getText())) {
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setAdminName(name.getText());
                adminEntity.setFatherName(fatherName.getText());
                adminEntity.setAddress(address.getText());
                adminEntity.setPassword(passwordField.getText());
                adminEntity.setPhoneNumber(phoneNumber.getText());
                adminEntity.setUserName(userName.getText());
//                System.out.printf(adminEntity.getUserName());
                adminEntity.setIdCardNumber((idCard.getText()));
                adminService.save(adminEntity);
                JavaFXUtils.showSuccessMessage("Admin added successfully");
                this.clearFields();
                setUpTable();
            }
        } else if (!showConfirmPasswordBox.isSelected()) {
            if (passwordField.equals("") || confirmPasswordField.equals("")) {
                JavaFXUtils.showWarningMessage("password and confirm password required");
            } else if (passwordField.getText().equals(confirmPasswordField.getText())) {
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setAdminName(name.getText());
                adminEntity.setFatherName(fatherName.getText());
                adminEntity.setAddress(address.getText());
                adminEntity.setPassword(passwordField.getText());
                adminEntity.setPhoneNumber(phoneNumber.getText());
                adminEntity.setIdCardNumber(idCard.getText());
                adminEntity.setUserName(userName.getText());
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
    public void searchByNameButton() {
        Optional<AdminEntity> s = adminRepo.findByAdminNameIgnoreCase(userName.getText());
        AdminEntity adminEntity = s.orElse(null);
        if (adminEntity != null) {
            searchedAdmin = adminEntity;
            name.setText(String.valueOf(searchedAdmin.getAdminName()));
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
        name.clear();
        fatherName.clear();
        idCard.clear();
        address.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        phoneNumber.clear();
        passwordTxt.clear();
        confirmPasswordTxt.clear();
        userName.clear();
    }


    @FXML
    public void back() {
        stageManager.switchScene(FxmlView.HOME);
    }

    @FXML
    public void showConfirmPasswordBox() {
        if (showConfirmPasswordBox.isSelected()) {
            confirmPasswordTxt.setText(confirmPasswordField.getText());
            passwordTxt.setText(passwordField.getText());
            confirmPasswordField.setVisible(false);
            confirmPasswordTxt.setVisible(true);
            passwordField.setVisible(false);
            passwordTxt.setVisible(true);
            return;
        }
        confirmPasswordField.setText(confirmPasswordTxt.getText());
        passwordField.setText(passwordTxt.getText());
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
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdminName()));
        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
        this.addressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress())));
        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
        this.userNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUserName())));
        populayeFields();
    }

    @FXML
    public void removeButton() {
        try {
            final Optional<ButtonType> deleteConfirmation = JavaFXUtils.showAlert(Alert.AlertType.CONFIRMATION,
                    "Delete Confirmation", "Are you sure you want to delete?");
            if (deleteConfirmation.isPresent() && deleteConfirmation.get() == ButtonType.OK) {
              if ( adminService.findByUserName(userName.getText())==null){
                  JavaFXUtils.showError("Admin with user name "+userName.getText()+"not found");
              }else{
                  adminService.deleteByUserName(userName.getText());
                  JavaFXUtils.showWarningMessage("Admin With " + userName.getText() + " Deleted Successfully");
                  setUpTable();
                  clearFields();
              }
            }
        } catch (Exception e) {
            JavaFXUtils.showError(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTable();
    }

    @FXML
    public void currentAdmin() throws IOException {
        switchView(FxmlView.ADMIN_INFO);
    }
    @FXML
    private void switchView(final FxmlView fxmlView) throws IOException {
        final Parent view = springFXMLLoader.load(fxmlView.getFxmlFile());
        stageManager.getStage().setTitle(fxmlView.getTitle());
        rootBorderPane.setCenter(view);
    }
    @FXML
    public void populayeFields(){
        adminsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                name.setText(newVal.getAdminName());
                fatherName.setText(newVal.getFatherName());
                phoneNumber.setText(newVal.getPhoneNumber());
                idCard.setText(String.valueOf(newVal.getIdCardNumber()));
                userName.setText(String.valueOf(newVal.getUserName()));
                address.setText(String.valueOf(newVal.getAddress()));

            }
        });

    }
    @FXML
    public void update() {
        Optional<AdminEntity> oldEntity = adminService.findByIdCardNumber(idCard.getText());
        oldEntity.toString();
        AdminEntity updatedEntity = new AdminEntity();
        updatedEntity.setAdminId(oldEntity.get().getAdminId());
        updatedEntity.setUserName(userName.getText());
        updatedEntity.setIdCardNumber(idCard.getText());
        updatedEntity.setPhoneNumber(phoneNumber.getText());
        updatedEntity.setFatherName(fatherName.getText());
        updatedEntity.setAddress(address.getText());
        updatedEntity.setAdminName(name.getText());
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            if (passwordField.getText().equals(oldEntity.get().getPassword())) {
                updatedEntity.setPassword(oldEntity.get().getPassword());
                if (updatedEntity == null) {
                    JavaFXUtils.showError("Alumnus not found to update");
                } else {

                    adminService.save(updatedEntity);
                    JavaFXUtils.showSuccessMessage("Alumnus updated success fully");
                    fullList();
                    setUpTable();
                    clearFields();
                }
            }else{
                JavaFXUtils.showError("please enter corretc password sam as old password");
            }
        }else {
            JavaFXUtils.showError("password and confirm password should equal");
        }
    }

    public void fullList(){
        ObservableList<AdminEntity> adminEntityObservableList = FXCollections.observableArrayList(adminRepo.findAll());
        adminEntityObservableList.stream().sorted();
        adminsTable.setItems(adminEntityObservableList);
    }
}
