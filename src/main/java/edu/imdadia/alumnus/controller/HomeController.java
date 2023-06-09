package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.enumuration.FxmlView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @FXML
    private MenuItem adminManagement;
    @FXML
    private MenuItem employeeManagement;
    @FXML
    private MenuItem attendance;
    @FXML
    private MenuItem alumnusManagement;

    @FXML
    private MenuItem alumnusTableManagement;

    private final StageManager stageManager;


    public HomeController(@Lazy final StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void adminManagement() {
        stageManager.switchScene(FxmlView.ADMIN);
    }

    @FXML
    public void alumnusManagement() {
        stageManager.switchScene(FxmlView.ALUMNUS);
    }

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
    public void currentAdmin() {
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }

    @FXML
    public void tableSearchTable() {
        stageManager.switchScene(FxmlView.ALUMNUSTABLE);
    }
}


