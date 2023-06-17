package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.SpringFXMLLoader;
import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.enumuration.FxmlView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HomeController {
    @FXML
    private BorderPane rootBorderPane;

    private SpringFXMLLoader springFXMLLoader;
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
    public void currentAdmin() throws IOException {
        stageManager.switchScene(FxmlView.ADMIN_INFO);
    }

    @FXML
    public void tableSearchTable() {
        stageManager.switchScene(FxmlView.ALUMNUSTABLE);
    }




    @FXML
    private void switchView(final FxmlView fxmlView) throws IOException {
        final Parent view = springFXMLLoader.load(fxmlView.getFxmlFile());
        stageManager.getStage().setTitle(fxmlView.getTitle());
        rootBorderPane.setCenter(view);
    }
}


