package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import edu.imdadia.alumnus.enumuration.FxmlView;
import edu.imdadia.alumnus.repository.AlumnusRepo;
import edu.imdadia.alumnus.services.AlumnusService;
import edu.imdadia.alumnus.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AlumnusTableController implements Initializable {

    private final AlumnusService alumnusService;
    private final AlumnusEntity alumnusEntity;

    private final StageManager stageManager;

    @FXML
    private TableView<AlumnusEntity> tableView;

    @FXML
    private TableColumn<AlumnusEntity, String> idColum;


    @FXML
    private TableColumn<AlumnusEntity, String> nameColum;

    @FXML
    private TableColumn<AlumnusEntity, String> fatherNameColum;

    @FXML
    private TableColumn<AlumnusEntity, String> idCardColum;

    @FXML
    private TableColumn<AlumnusEntity, String> phoneNumberColum;

    @FXML
    private TableColumn<AlumnusEntity, String> typeColum;

    @FXML
    private TableColumn<AlumnusEntity, String> districtColum;

    @FXML
    private TableColumn<AlumnusEntity, String> permanentAddressColum;

    @FXML
    private TableColumn<AlumnusEntity, String> graduationYearColum;


    @FXML
    private TextField districtNamefield;


    @FXML
    private TextField graduationYearfield;

    @FXML
    private ChoiceBox<String> typeChoice;
    private final AlumnusRepo alumnusRepo;


    public AlumnusTableController(AlumnusService alumnusService, @Lazy StageManager stageManager, AlumnusRepo alumnusRepo) {
        this.alumnusService = alumnusService;
        this.stageManager = stageManager;
        this.alumnusRepo = alumnusRepo;
        this.alumnusEntity = new AlumnusEntity();
    }


    private void setUpTable() {
        ObservableList<AlumnusEntity> alumnusEntityObservableList = FXCollections.observableArrayList(alumnusRepo.findAll());
        alumnusEntityObservableList.stream().sorted();
        tableView.setItems(alumnusEntityObservableList);
        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlumnusId())));
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlumnusName()));
        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
        this.permanentAddressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPermanentAddress())));
        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
        this.districtColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        this.typeColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getType())));
        this.graduationYearColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGraduationYear())));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final List<String> choiceConditionList = new ArrayList<>();
        choiceConditionList.add("Mufti");
        choiceConditionList.add("Scholar");
        choiceConditionList.add("Reader");
        choiceConditionList.add("Hafiz");
        typeChoice.getItems().addAll(choiceConditionList);
        setUpTable();
    }

    public void graduationYear() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findByGraduationYear(graduationYearfield.getText()));
        tableView.setItems(alumnusEntities);
        graduationYearfield.clear();
    }


    public void DegreeType() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findAllByType(typeChoice.getValue()));
        tableView.setItems(alumnusEntities);
       typeChoice.setValue("");
    }

    public void findByDistrictTypeYear() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findByGraduationYearAndDistrictAndType(graduationYearfield.getText(),districtNamefield.getText(),typeChoice.getValue()));
        tableView.setItems(alumnusEntities);
        clear();
    }

    public void districtName() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findAllByDistrict(districtNamefield.getText()));
        tableView.setItems(alumnusEntities);
        districtNamefield.clear();
    }

    private void clear(){
        typeChoice.setValue("");
        districtNamefield.clear();
        graduationYearfield.clear();

    }
    @FXML
    public void goToAlumnusPage() {
        stageManager.switchScene(FxmlView.ALUMNUS);
    }

    public void findAll(){
        setUpTable();
    }
}
