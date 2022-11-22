package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sadms.Main;

public class EmployeeLogin
{
    @FXML RadioButton Manager;
    @FXML TextField EmployeeName, Password;
    @FXML Label ErrorMessage;

    @FXML void gotoManager() throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void LogInClicked() throws Exception
    {
        Main.openCon("employeelogin");
        Document query = new Document("EmployeeName",EmployeeName.getText()).append("Password",Password.getText());
        Document value = Main.collection.find(query).first();
        Main.closeCon();
        if(value == null)
        {
            ErrorMessage.setText("** Wrong Credentials Entered **");
        }
        else
        {
            ErrorMessage = null;
            Main.setRoot("EmployeeDashboard");
        }
    }
    @FXML void ClearClicked() throws Exception
    {
        EmployeeName.clear();
        Password.clear();
    }
}