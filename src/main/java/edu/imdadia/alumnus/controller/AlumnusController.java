package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.SpringFXMLLoader;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


@Controller
public class AlumnusController implements Initializable {
    private final AlumnusEntity alumnusEntity;
    private final StageManager stageManager;
    private final AlumnusService alumnusService;

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
    private ChoiceBox<String> typeChoice;

    @FXML
    private ChoiceBox<String> provinceChoice;

    @FXML
    private ChoiceBox<String> districtChoice;


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
    private TextField graduationYear;
    @FXML
    private TextField district;
    @FXML
    private TextField idCard;
    private final SpringFXMLLoader springFXMLLoader;
    private final AlumnusRepo alumnusRepo;

    public AlumnusController(@Lazy StageManager stageManager, AlumnusService alumnusService, SpringFXMLLoader springFXMLLoader, AlumnusRepo alumnusRepo) {
        this.stageManager = stageManager;
        this.alumnusService = alumnusService;
        this.springFXMLLoader = springFXMLLoader;
        this.alumnusEntity = new AlumnusEntity();
        this.alumnusRepo = alumnusRepo;
    }


    @FXML
    public void add() {
        if (id.getText().equals("") || name.getText().equals("") || fatherName.getText().equals("") || graduationYear.getText().equals("") || permanentAddress.getText().equals("") || phoneNumber.getText().equals("") || district.getText().equals("") || idCard.getText().equals("")) {
            JavaFXUtils.showWarningMessage("Any field of the following fields should not be null ID  , Name , Father Name , IDCard number , PhoneNumber , GraduationYear ,Permanent Address , District,Type");
        } else {
            AlumnusEntity alumnus = new AlumnusEntity();
            alumnus.setAlumnusId(Integer.valueOf(id.getText()));
            alumnus.setAlumnusName(name.getText());
            alumnus.setFatherName(fatherName.getText());
            alumnus.setPermanentAddress(permanentAddress.getText());
            alumnus.setIdCardNumber(idCard.getText());
            alumnus.setPhoneNumber(phoneNumber.getText());
            alumnus.setDistrict(district.getText());
            alumnus.setGraduationYear(graduationYear.getText());
            alumnus.setType(typeChoice.getValue());
            Optional<AlumnusEntity> employeeEntity = alumnusRepo.findByIdCardNumber(idCard.getText());
            if (employeeEntity != null) {
                alumnusService.save(alumnus);
                JavaFXUtils.showSuccessMessage("Alumnus added successfully");
                this.clear();
            } else {
                JavaFXUtils.showError("Alumnus with Id card number " + idCard.getText() + " already added");
            }
        }
    }

    @FXML
    public void searchByName() {
        Optional<AlumnusEntity> s = alumnusService.findByAlumnusName(name.getText());
        AlumnusEntity alumnus = s.orElse(null);
        if (alumnus != null) {
            name.setText(String.valueOf(alumnus.getAlumnusName()));
            id.setText(String.valueOf(alumnus.getAlumnusId()));
            fatherName.setText(alumnus.getFatherName());
            phoneNumber.setText(String.valueOf(alumnus.getPhoneNumber()));
            permanentAddress.setText(alumnus.getPermanentAddress());
            district.setText(alumnus.getDistrict());
            graduationYear.setText(alumnus.getGraduationYear());
            typeChoice.getValue();
            idCard.setText(String.valueOf(alumnus.getIdCardNumber()));
        } else {
            JavaFXUtils.showError("Alumnus with Name " + name.getText() + " does not exist");
            clear();
        }
    }

    @FXML
    public void back() {
        stageManager.switchScene(FxmlView.HOME);
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
        final List<String> choiceDegreeTypeList = new ArrayList<>();
        final List<String> choiceProvincrList = new ArrayList<>();
        final List<String> choiceDistrictList = new ArrayList<>();

        choiceDegreeTypeList.add("Mufti");
        choiceDegreeTypeList.add("Scholar");
        choiceDegreeTypeList.add("Reader");
        choiceDegreeTypeList.add("Hafiz");
        typeChoice.getItems().addAll(choiceDegreeTypeList);

        choiceProvincrList.add("Punjab");
        choiceProvincrList.add("KPk");
        choiceProvincrList.add("Sindh");
        choiceProvincrList.add("Balochictan");
        choiceProvincrList.add("Gilgit");
        choiceProvincrList.add("Fata");
        choiceProvincrList.add("Kashmir");
        choiceProvincrList.add("federal");

        provinceChoice.getItems().addAll(choiceProvincrList);

        choiceDistrictList.add("Mufti");
        choiceDistrictList.add("Scholar");
        choiceDistrictList.add("Reader");
        choiceDistrictList.add("Hafiz");
        districtChoice.getItems().addAll(choiceDegreeTypeList);
        setUpTable();
    }

    public void findByYear() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findByGraduationYear(graduationYear.getText()));
        tableView.setItems(alumnusEntities);
        graduationYear.clear();
    }


    public void findByDegree() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findAllByType(typeChoice.getValue()));
        tableView.setItems(alumnusEntities);
        typeChoice.setValue("");
    }

    public void findByDegreeDistrictYear() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findByGraduationYearAndDistrictAndType(graduationYear.getText(),district.getText(),typeChoice.getValue()));
        tableView.setItems(alumnusEntities);
        clear();
    }

    public void findByDistrict() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findAllByDistrict(district.getText()));
        tableView.setItems(alumnusEntities);
        district.clear();
    }

    @FXML
    private void clear(){
        typeChoice.setValue("");
        id.clear();
        name.clear();
        fatherName.clear();
        idCard.clear();
        permanentAddress.clear();
        district.clear();
        phoneNumber.clear();
        graduationYear.clear();

    }

    public void findAll(){
        setUpTable();
    }
}
