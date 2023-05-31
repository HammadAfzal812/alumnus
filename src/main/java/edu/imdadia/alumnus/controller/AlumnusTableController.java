package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import edu.imdadia.alumnus.repository.AlumnusRepo;
import edu.imdadia.alumnus.services.AlumnusService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AlumnusTableController implements Initializable {

    private final AlumnusService alumnusService;

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
    private TableColumn<AlumnusEntity, String> addressColum;

    @FXML
    private TableColumn<AlumnusEntity, String> graduationYearColum;
    private final AlumnusRepo alumnusRepo;


    public AlumnusTableController(AlumnusService alumnusService, @Lazy StageManager stageManager, AlumnusRepo alumnusRepo) {
        this.alumnusService = alumnusService;
        this.stageManager = stageManager;
        this.alumnusRepo = alumnusRepo;
    }


//    private void setUpTable() {
//
//        List<MeausrementEntity> meausrementEntities = new ArrayList<>();
//        try {
//            meausrementEntities = meausrementService.findAllByOrderByMeausrementIdAsc();
//        } catch (Exception e) {
//            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
//        }
//        customerDataTable.setItems(FXCollections.observableArrayList(meausrementEntities));
//
//        this.idTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMeausrementId()))
//        );
//        this.firstNameTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
//        this.lastNameTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLastName())));
//        this.genderTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGender())));
//        this.cnicTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCnicNo())));
//        this.mobNoTableColumn.setCellValueFactory(
//                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMobileNo())));
//
//        this.customerDataTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                MeausrementController.meausrementTable = meausrementService.findById(newSelection.getMeausrementId());
//            }
//        });
//    }
//
//    public void tableFilter() {
//        final List<MeausrementEntity> itemList = meausrementService.findAllByOrderByMeausrementIdAsc();
//        FilteredList<MeausrementEntity> filteredData = new FilteredList<>(FXCollections.observableArrayList(itemList), p -> true);
//
//        // 2. Set the filter Predicate whenever the filter changes.
//        searchTable.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(MeausrementEntity -> {
//            // If filter text is empty, display all persons.
//            if (newValue == null || newValue.isEmpty()) {
//                return true;
//            }
//            // Compare first name and last name of every person with filter text. yhn jis jis colon mn lgana hy filter usy get likhy first name , last name , number , id, cnic
//            String lowerCaseFilter = newValue.toLowerCase();
//            if (MeausrementEntity.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
//                return true; // Filter matches first name.
//            } else if (MeausrementEntity.getLastName().toLowerCase().contains(lowerCaseFilter)) {
//                return true; // Filter matches last name.
//            } else if (String.valueOf(MeausrementEntity.getMobileNo()).toLowerCase().contains(lowerCaseFilter)) {
//                return true; // Filter matches last name.
//            } else if (String.valueOf(MeausrementEntity.getCnicNo()).toLowerCase().contains(lowerCaseFilter)) {
//                return true; // Filter matches last name.
//            } else
//                return String.valueOf(MeausrementEntity.getMeausrementId()).toLowerCase().contains(lowerCaseFilter); // Filter matches last name.
//// Does not match.
//        }));
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<MeausrementEntity> sortedData = new SortedList<>(filteredData);
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(customerDataTable.comparatorProperty());
//        // 5. Add sorted (and filtered) data to the table.
//        customerDataTable.setItems(sortedData);
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setUpTable();
    }

//    private void setUpTable() {
//        ObservableList<AlumnusEntity> alumnusEntityObservableList = FXCollections.observableArrayList(alumnusRepo.findAll());
//        alumnusEntityObservableList.stream().sorted();
//        tableView.setItems(alumnusEntityObservableList);
//        this.idColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlumnusId())));
//        this.nameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlumnusName()));
//        this.fatherNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFatherName())));
//        this.addressColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPermanentAddress())));
//        this.phoneNumberColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhoneNumber())));
//        this.idCardColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCardNumber())));
//        this.districtColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
//        this.typeColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getType())));
//        this.graduationYearColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGraduation_year())));
//    }

}
