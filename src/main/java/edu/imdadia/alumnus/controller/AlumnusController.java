package edu.imdadia.alumnus.controller;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

import java.awt.*;
@Controller
public class AlumnusController {

    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private TextField permanentAddress;
    @FXML
    private TextField district;
    @FXML
    private TextField idCard;
    @FXML
    private TextField complete;


}
