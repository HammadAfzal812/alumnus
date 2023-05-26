package edu.imdadia.alumnus.controller;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;


@Controller
public class AlumnusController  {

//    private TableView<AlumnusEntity> alumnusEntityTableViewTable;
    @FXML
    private TableColumn<AlumnusEntity, String> idColum;
    @FXML
    private TableColumn<AlumnusEntity, String> nameColum;
    @FXML
    private TableColumn<AlumnusEntity, String> fatherNameColum;
    @FXML
    private TableColumn<AlumnusEntity, String> phoneNumberColum;
    @FXML
    private TableColumn<AlumnusEntity, String> idCardColum;
    @FXML
    private TableColumn<AlumnusEntity, String> addressColum;
    @FXML
    private TableColumn<AlumnusEntity, String> districtColum;
    @FXML
    private TableColumn<AlumnusEntity, String> typeColum;
    @FXML
    private TableColumn<AlumnusEntity, String> yearColum;

//    private BorderPane rootBorderPane;

    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField permanentAddress;
    @FXML
    private TextField district;
    @FXML
    private TextField idCard;


}
