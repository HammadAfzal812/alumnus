package edu.imdadia.alumnus.controller;

import edu.imdadia.alumnus.config.SpringFXMLLoader;
import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.entity.AlumnusEntity;
import edu.imdadia.alumnus.enumuration.FxmlView;
import edu.imdadia.alumnus.repository.AlumnusRepo;
import edu.imdadia.alumnus.services.AlumnusService;
import edu.imdadia.alumnus.util.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    private final SpringFXMLLoader springFXMLLoader;
    private final AlumnusRepo alumnusRepo;

    public AlumnusController(@Lazy StageManager stageManager, AlumnusService alumnusService, SpringFXMLLoader springFXMLLoader, AlumnusRepo alumnusRepo) {
        this.stageManager = stageManager;
        this.alumnusService = alumnusService;
        this.springFXMLLoader = springFXMLLoader;
        this.alumnusEntity = new AlumnusEntity();
        this.alumnusRepo = alumnusRepo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final List<String> choiceConditionList = new ArrayList<>();
        choiceConditionList.add("Mufti");
        choiceConditionList.add("Scholar");
        choiceConditionList.add("Reader");
        choiceConditionList.add("Hafiz");
        typeChoice.getItems().addAll(choiceConditionList);
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
    @FXML
    public void goToAlumnusPage() {
        stageManager.switchScene(FxmlView.ALUMNUSTABLE);
    }
}
