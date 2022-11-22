package sadms.java;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.bson.Document;
import sadms.Main;

public class ManagerLogin
{
    @FXML RadioButton Employee;
    @FXML TextField ManagerName, EmailId, Password;
    @FXML Label ErrorMessage;
    static Document value;

    @FXML void gotoEmployee() throws Exception
    {
        Main.setRoot("EmployeeLogin");
    }
    @FXML  void LogInClicked() throws Exception
    {
        Main.openCon("managerlogin");
        Document query = new Document("ManagerName",ManagerName.getText()).append("EmailId", EmailId.getText()).append("Password", Password.getText());
        value = Main.collection.find(query).first();
        Main.closeCon();
        if(value == null)
        {
            ErrorMessage.setText("** Wrong Credentials Entered **");
        }
        else
        {
            ErrorMessage = null;
            Main.setRoot("ManagerDashboard");
        }
    }
    @FXML void ClearClicked()
    {
        ManagerName.clear();
        EmailId.clear();
        Password.clear();
    }
}
