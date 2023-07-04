package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AdminEntity;
import edu.imdadia.alumnus.enumuration.FxmlView;
import edu.imdadia.alumnus.repository.AdminRepo;
import edu.imdadia.alumnus.services.AdminService;
import edu.imdadia.alumnus.util.JavaFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    public static AdminEntity adminEntity = new AdminEntity();
    public static List<AdminEntity> allAdmins;
    private final AdminRepo adminRepo;

    public void initialize() {
        this.plainPassword.setVisible(false);
        allAdmins=adminService.findAll();

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
        if (allAdmins==null) {
            JavaFXUtils.showWarningMessage("please add an Admin because there is no admin");
            stageManager.switchScene(FxmlView.ADMIN);
        } else {
            for (int i = 0; i < allAdmins.size(); ) {
                for (AdminEntity a : allAdmins) {
                    if (check.isSelected()){
                        if (a.getPassword().equals(plainPassword.getText()) && a.getUserName().equalsIgnoreCase(username.getText())) {
                            stageManager.switchScene(FxmlView.HOME);
                            adminEntity = a;
                            break;
                        }
                    } else if (!check.isSelected()){
                        if (a.getPassword().equals(password.getText()) && a.getUserName().equalsIgnoreCase(username.getText())) {
                            stageManager.switchScene(FxmlView.HOME);
                            adminEntity = a;
                            break;
                        }
                    }
                    i++;
                    if (i == allAdmins.size()) {
                        JavaFXUtils.showError("Admin not found or password not match");
                    }
                }
                break;
            }

        }
    }

    @FXML
    public void showPassword() {
        String a;
        if (check.isSelected()) {
             a =password.getText();
            plainPassword.setText(a);
            password.setVisible(false);
            plainPassword.setVisible(true);
        }else {
            a=plainPassword.getText();
            password.setText(a);
            plainPassword.setVisible(false);
            password.setVisible(true);
        }
    }


    @FXML
    public void exitButton() {
        Platform.exit();
    }
}

