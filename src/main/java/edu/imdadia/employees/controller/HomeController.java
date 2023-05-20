package edu.imdadia.employees.controller;

import edu.imdadia.employees.config.StageManager;
import edu.imdadia.employees.entity.EmployeeEntity;
import edu.imdadia.employees.enumuration.FxmlView;
import edu.imdadia.employees.repository.EmployeeRepo;
import edu.imdadia.employees.util.JavaFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class HomeController {
    @FXML
    private MenuItem adminManagement;
    @FXML
    private MenuItem employeeManagement;
    @FXML
    private MenuItem attendance;

    private final StageManager stageManager;


    public HomeController(@Lazy final StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void adminManagement() {
        stageManager.switchScene(FxmlView.ADMIN);
    }

    @FXML
    public void employeeManagement() {
        stageManager.switchScene(FxmlView.EMPLOYEES);
    }

    @FXML
    public void attendance() {stageManager.switchScene(FxmlView.ATTENDANCE);}

    @FXML
    public void close() {
        Platform.exit();
    }
    @FXML
    public void logOut() {
        LoginController.adminEntity = null;
        stageManager.switchScene(FxmlView.LOGIN);
    }
    @FXML
    public void currentAdmin(){
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }

}
