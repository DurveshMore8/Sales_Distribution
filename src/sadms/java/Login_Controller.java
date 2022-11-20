package sadms.java;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import sadms.Main;

public class Login_Controller {
    @FXML RadioButton rbManager;
    @FXML RadioButton rbEmployee;

    @FXML void gotoManager() throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void gotoEmployee() throws Exception
    {
        Main.setRoot("EmployeeLogin");
    }
}
