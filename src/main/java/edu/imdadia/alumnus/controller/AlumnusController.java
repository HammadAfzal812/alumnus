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
        choiceProvincrList.add("Azad Kashmir");
        provinceChoice.getItems().addAll(choiceProvincrList);

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
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findByGraduationYearAndDistrictAndType(graduationYear.getText(), district.getText(), typeChoice.getValue()));
        tableView.setItems(alumnusEntities);
        clear();
    }

    public void findByDistrict() {
        ObservableList<AlumnusEntity> alumnusEntities = FXCollections.observableArrayList(alumnusService.findAllByDistrict(district.getText()));
        tableView.setItems(alumnusEntities);
        district.clear();
    }

    @FXML
    private void clear() {
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
    @FXML
    private void districtChoice(){
        final List<String> choiceDistrictList = new ArrayList<>();
        if (provinceChoice.getValue().equals("")){
            JavaFXUtils.showError("please chose province first than chose district");
        }else if (provinceChoice.getValue().equals("Punjab")){
            choiceDistrictList.add("Attock");
            choiceDistrictList.add("Bahawalnagar");
            choiceDistrictList.add("BahawalPur");
            choiceDistrictList.add("Bakkhar");
            choiceDistrictList.add("Chakwal");
            choiceDistrictList.add("chiniot");
            choiceDistrictList.add("Dera Ghazi Khan");
            choiceDistrictList.add("Faislabad");
            choiceDistrictList.add("Gujranwala");
            choiceDistrictList.add("Gujrat");
            choiceDistrictList.add("Hafizabad");
            choiceDistrictList.add("Jhang");
            choiceDistrictList.add("Jhelum");
            choiceDistrictList.add("Kasur");
            choiceDistrictList.add("Khanewal");
            choiceDistrictList.add("KhUshab");
            choiceDistrictList.add("Kot Adu");
            choiceDistrictList.add("Lahore");
            choiceDistrictList.add("Layyah");
            choiceDistrictList.add("Lodhran");
            choiceDistrictList.add("Mandi Bahauddin");
            choiceDistrictList.add("Mianwali");
            choiceDistrictList.add("Multan");
            choiceDistrictList.add("Murree");
            choiceDistrictList.add("Muzaffargarh");
            choiceDistrictList.add("Narowal");
            choiceDistrictList.add("Nankana Sahib");
            choiceDistrictList.add("Okara");
            choiceDistrictList.add("Pakpattan");
            choiceDistrictList.add("Rahim Yar Khan ");
            choiceDistrictList.add("Rajanpur");
            choiceDistrictList.add("Rawalpindi");
            choiceDistrictList.add("Sahiwal");
            choiceDistrictList.add("sargoda");
            choiceDistrictList.add("Sheikhupura");
            choiceDistrictList.add("Sialkot");
            choiceDistrictList.add("TalaGang");
            choiceDistrictList.add("Taunsa");
            choiceDistrictList.add("Toba Tek Singh");
            choiceDistrictList.add("Vehari");
            choiceDistrictList.add("Wazirabad");
            choiceDistrictList.add("Islamabad");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }else if (provinceChoice.getValue().equals("KPk")){
            choiceDistrictList.add("Abbottabad");
            choiceDistrictList.add("Bajaur");
            choiceDistrictList.add("Bannu");
            choiceDistrictList.add("Batgram");
            choiceDistrictList.add("Buner");
            choiceDistrictList.add("Charsadda");
            choiceDistrictList.add("Dera Ismail Khan");
            choiceDistrictList.add("Hangu");
            choiceDistrictList.add("Hariur");
            choiceDistrictList.add("Karak");
            choiceDistrictList.add("Kyber");
            choiceDistrictList.add("Kohat");
            choiceDistrictList.add("Kolai Pallas");
            choiceDistrictList.add("Kurram");
            choiceDistrictList.add("Lakki Marwat");
            choiceDistrictList.add("Lower Chitral");
            choiceDistrictList.add("Lower Dir");
            choiceDistrictList.add("Lower Kohistan");
            choiceDistrictList.add("Malakand");
            choiceDistrictList.add("North Wazirstan");
            choiceDistrictList.add("Nowshera");
            choiceDistrictList.add("Peshawar");
            choiceDistrictList.add("Sangla");
            choiceDistrictList.add("South Wazirastan");
            choiceDistrictList.add("Swabi");
            choiceDistrictList.add("Swat");
            choiceDistrictList.add("Tank");
            choiceDistrictList.add("Tor Ghar");
            choiceDistrictList.add("Upper Chitral");
            choiceDistrictList.add("Upper Dir");
            choiceDistrictList.add("Upper Kohistan");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }else if (provinceChoice.getValue().equals("Sindh")){
            choiceDistrictList.add("Badin");
            choiceDistrictList.add("Dadu");
            choiceDistrictList.add("Ghotki");
            choiceDistrictList.add("Hyderabad");
            choiceDistrictList.add("Jacobadad");
            choiceDistrictList.add("Jamshoro");
            choiceDistrictList.add("Karachi Central");
            choiceDistrictList.add("Kashmore");
            choiceDistrictList.add("Khairpur");
            choiceDistrictList.add("Larkana");
            choiceDistrictList.add("Matiari");
            choiceDistrictList.add("Mirpur Khas");
            choiceDistrictList.add("Naushahro Firoze");
            choiceDistrictList.add("Shaheed Benazirabad");
            choiceDistrictList.add("Qambar Shahdadkot");
            choiceDistrictList.add("Sanghar");
            choiceDistrictList.add("Shikarpur");
            choiceDistrictList.add("Sukkur");
            choiceDistrictList.add("Tando Allahyar");
            choiceDistrictList.add("Tando Muhammad Khan");
            choiceDistrictList.add("Tharparkar");
            choiceDistrictList.add("Thatta");
            choiceDistrictList.add("Umerkot");
            choiceDistrictList.add("Sujawal");
            choiceDistrictList.add("Karachi East");
            choiceDistrictList.add("Karachi South");
            choiceDistrictList.add("Karachi West");
            choiceDistrictList.add("Korangi");
            choiceDistrictList.add("Malir");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }else if (provinceChoice.getValue().equals("Balochictan")){
            choiceDistrictList.add("Awaran");
            choiceDistrictList.add("Barkhan");
            choiceDistrictList.add("Chagai");
            choiceDistrictList.add("Dera Bugti");
            choiceDistrictList.add("Duki");
            choiceDistrictList.add("Gwadar");
            choiceDistrictList.add("Harnai");
            choiceDistrictList.add("Jafrabad");
            choiceDistrictList.add("Jhal Magsi");
            choiceDistrictList.add("Kachhi");
            choiceDistrictList.add("Kalat");
            choiceDistrictList.add("Kech");
            choiceDistrictList.add("Kharan");
            choiceDistrictList.add("Khuzdar");
            choiceDistrictList.add("Killa Abdullah");
            choiceDistrictList.add("Kohlu");
            choiceDistrictList.add("Lasbela");
            choiceDistrictList.add("Loralai");
            choiceDistrictList.add("Mastung");
            choiceDistrictList.add("Musakhel");
            choiceDistrictList.add("Naseerabad");
            choiceDistrictList.add("Nushki");
            choiceDistrictList.add("Panjgur");
            choiceDistrictList.add("Pishin");
            choiceDistrictList.add("Qillaa Saaifullah");
            choiceDistrictList.add("Quetta");
            choiceDistrictList.add("Shaheed Skindarabaad");
            choiceDistrictList.add("Sherani");
            choiceDistrictList.add("Sibbi");
            choiceDistrictList.add("Sohbatpur");
            choiceDistrictList.add("Washuk");
            choiceDistrictList.add("Zhob");
            choiceDistrictList.add("Ziarat");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }else if (provinceChoice.getValue().equals("Gilgit")){

            choiceDistrictList.add("Astore");
            choiceDistrictList.add("Darel");
            choiceDistrictList.add("Diamer");
            choiceDistrictList.add("Ghanche");
            choiceDistrictList.add("Ghizer");
            choiceDistrictList.add("Gilgit");
            choiceDistrictList.add("Gupis Yasin");
            choiceDistrictList.add("Hunza");
            choiceDistrictList.add("Kharmang");
            choiceDistrictList.add("Nagar");
            choiceDistrictList.add("Roundu");
            choiceDistrictList.add("Shigar");
            choiceDistrictList.add("Skardu");
            choiceDistrictList.add("Tangir");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }else if (provinceChoice.getValue().equals("Azad Kashmir")){
            choiceDistrictList.add("Bagh");
            choiceDistrictList.add("Bhimber");
            choiceDistrictList.add("Hattian");
            choiceDistrictList.add("Haveli");
            choiceDistrictList.add("Kotli");
            choiceDistrictList.add("Mirpur");
            choiceDistrictList.add("Muzaffarabad");
            choiceDistrictList.add("Neelum");
            choiceDistrictList.add("Poonch");
            choiceDistrictList.add("Sudhnutti");
            districtChoice.setItems(FXCollections.observableList(choiceDistrictList));
        }
        districtChoice.getItems().addAll(choiceDistrictList);
        setUpTable();
    }

    public void findAll() {
        setUpTable();
    }
}
