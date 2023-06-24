package edu.imdadia.alumnus.controller;
import edu.imdadia.alumnus.config.StageManager;
import edu.imdadia.alumnus.enumuration.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AdminInfoController implements Initializable {
    private final StageManager stageManager;
    @FXML
    private TextArea textArea;




    public AdminInfoController(@Lazy StageManager stageManager) {
        this.stageManager = stageManager;

    }



    public void setText(){
        String admin=LoginController.adminEntity.getAdminName()+"\n \n \n"+LoginController.adminEntity.getFatherName()
                +"\n\n\n"+LoginController.adminEntity.getAddress();
        textArea.setText(admin);
        textArea.setEditable(false);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setText();
    }
    @FXML
    public void back(){
        stageManager.switchScene(FxmlView.HOME);
    }
}
