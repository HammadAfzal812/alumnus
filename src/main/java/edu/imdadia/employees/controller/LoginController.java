package edu.imdadia.employees.controller;

import edu.imdadia.employees.config.StageManager;
import edu.imdadia.employees.entity.AdminEntity;
import edu.imdadia.employees.enumuration.FxmlView;
import edu.imdadia.employees.repository.AdminRepo;
import edu.imdadia.employees.services.AdminService;
import edu.imdadia.employees.util.JavaFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

@Controller
public class LoginController {

    @FXML

    private TextField username;
    @FXML
    private TextField plainPassword;
    @FXML
    private PasswordField password;
    @FXML
    private final StageManager stageManager;
    @FXML
    private CheckBox check;
    private final AdminService adminService;
    public static AdminEntity adminEntity = null;
    private final AdminRepo adminRepo;

    public void initialize() {
        this.plainPassword.setVisible(false);
    }

    public LoginController(@Lazy final StageManager stageManager, AdminService adminService, AdminRepo adminRepo) {
        this.stageManager = stageManager;
        this.adminService = adminService;
        this.adminRepo = adminRepo;
    }


    @FXML
    public void loginButton() {
        if (StringUtils.isBlank(this.username.getText())) {
            JavaFXUtils.showError("Username required.");
            this.username.requestFocus();
            return;
        }
        if (StringUtils.isBlank(this.password.getText())) {
            JavaFXUtils.showError("Password required.");
            this.password.requestFocus();
            return;
        }
        List<AdminEntity> allAdmins= adminRepo.findAll();
        if (allAdmins.size()<1){
            stageManager.switchScene(FxmlView.ADMIN);
        }else{

            for (int i = 0;i<allAdmins.size();){
            for (AdminEntity a : allAdmins) {
                if (a.getPassword().equals(password.getText()) && a.getAdminName().equals(username.getText())) {
                    stageManager.switchScene(FxmlView.HOME);
                    adminEntity=a;
                    break;
                }
                i++;
                if (i==allAdmins.size()){
                    JavaFXUtils.showError("Admin not found or password not match");
                }
            }
            break;
        }

        }
    }

    @FXML
    public void showPassword() {
        if (check.isSelected()) {
            plainPassword.setText(password.getText());
            password.setVisible(false);
            plainPassword.setVisible(true);
            return;
        }
        plainPassword.setVisible(false);
        password.setVisible(true);
    }


    @FXML
    public void exitButton() {
        Platform.exit();
    }
}

