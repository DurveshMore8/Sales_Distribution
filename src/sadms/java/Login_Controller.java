package sadms.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import sadms.Main;

public class Login_Controller {
    @FXML RadioButton rbManager;
    @FXML RadioButton rbEmployee;

    @FXML void gotoManager(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void gotoEmployee(ActionEvent event) throws Exception
    {
        Main.setRoot("EmployeeLogin");
    }
}
