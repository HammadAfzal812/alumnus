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
    TableView<AlumnusEntity> alumnusEntityTableView;
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
    private TableColumn<AlumnusEntity, String> graduationYearColum;

    @FXML
    private ChoiceBox<String> typeChoice;


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
    //    @FXML
    private final SpringFXMLLoader springFXMLLoader;
    //
    private final AlumnusRepo alumnusRepo;

    public AlumnusController(@Lazy StageManager stageManager, AlumnusService alumnusService, SpringFXMLLoader springFXMLLoader, AlumnusRepo alumnusRepo) {
        this.stageManager = stageManager;
        this.alumnusService = alumnusService;
        this.springFXMLLoader = springFXMLLoader;
        this.alumnusEntity = new AlumnusEntity();
        this.alumnusRepo = alumnusRepo;
    }

    //
//
    private void setUpTable() {
        ObservableList<AlumnusEntity> alumnusEntityObservableList = FXCollections.observableArrayList(alumnusRepo.findAll());
        alumnusEntityObservableList.stream().sorted();
        alumnusEntityTableView.setItems(alumnusEntityObservableList);
        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlumnusId())));
        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlumnusName()));
        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
        this.addressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPermanentAddress())));
        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
        this.districtColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        this.typeColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getType())));
        this.graduationYearColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGraduation_year())));
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

    public void clear() {
        id.clear();
        name.clear();
        fatherName.clear();
        idCard.clear();
        permanentAddress.clear();
        district.clear();
        phoneNumber.clear();
        graduationYear.clear();
    }

    //
//    @FXML
//    public void delete() {
//        alumnusService.delete(alumnusEntity);
//        JavaFXUtils.showSuccessMessage("Alumnus With " + id.getText() + "Alumnus With Name" + name.getText() + " Deleted Successfully");
//        setUpTable();
//        clear();
//    }
//
//
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
            alumnus.setGraduation_year(graduationYear.getText());
//            alumnus.setType(typeChoice.getValue());
            Optional<AlumnusEntity> employeeEntity = alumnusRepo.findByIdCardNumber(idCard.getText());
            if (employeeEntity != null) {
                alumnusService.save(alumnus);
                JavaFXUtils.showSuccessMessage("Alumnus added successfully");
                this.clear();
            } else {
                JavaFXUtils.showError("Alumnus with Id card number " + idCard.getText() + " already added");
            }
            setUpTable();
        }
    }

        @FXML
    public void search() {
        Optional<AlumnusEntity> s = alumnusService.findByAlumnusName(name.getText());
        AlumnusEntity alumnus = s.orElse(null);
        if (alumnus != null) {
            name.setText(String.valueOf(alumnus.getAlumnusName()));
            id.setText(String.valueOf(alumnus.getAlumnusId()));
            fatherName.setText(alumnus.getFatherName());
            phoneNumber.setText(String.valueOf(alumnus.getPhoneNumber()));
            permanentAddress.setText(alumnus.getPermanentAddress());
            district.setText(alumnus.getDistrict());
            graduationYear.setText(alumnus.getGraduation_year());
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
}
