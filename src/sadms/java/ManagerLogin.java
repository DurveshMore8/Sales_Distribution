package sadms.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.bson.Document;
import sadms.Main;

public class ManagerLogin
{
    @FXML RadioButton rbEmployee;
    @FXML TextField tfManagerName;
    @FXML TextField tfManagerGmail;
    @FXML TextField tfManagerPassword;
    @FXML Label ErrorMessage;
    static Document value;

    @FXML void gotoEmployee(ActionEvent event) throws Exception
    {
        Main.setRoot("EmployeeLogin");
    }
    @FXML  void LogInClicked(ActionEvent event) throws Exception
    {
        Main.openCon("managerlogin");

        Document query = new Document("ManagerName",tfManagerName.getText()).append("ManagerGmail", tfManagerGmail.getText()).append("ManagerPassword", tfManagerPassword.getText());
        value = Main.collection.find(query).first();
        Main.closeCon();
        if(value == null)
        {
            ErrorMessage.setText("* Wrong Credentials Entered *");
        }
        else
        {
            ErrorMessage = null;
            Main.setRoot("ManagerDashboard");
        }
    }
    @FXML void ClearClicked(ActionEvent event)
    {
        tfManagerName.clear();
        tfManagerGmail.clear();
        tfManagerPassword.clear();
    }
}
