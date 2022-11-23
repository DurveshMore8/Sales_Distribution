package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sadms.Main;

public class ManagerManager {
    @FXML Label manager, ErrorMessage;
    @FXML TextField ManagerId, Name, Gender, DateofBirth, Age, Phone, EmailId, ManagerName;
    @FXML GridPane Details;
    @FXML Button AddManager, UpdateManager, DeleteManager;
    static String sendManagerId, sendManagerName;
    public static String sendmessage;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        Details.setVisible(false);
        ErrorMessage.setVisible(false);
        if(sendmessage != "")
        {
            ErrorMessage.setText(sendmessage);
            ErrorMessage.setVisible(true);
            sendmessage = "";
        }
    }
    @FXML void Log_Out() throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void EditProfileClicked() throws Exception
    {
        Main.setRoot("ManagerEditProfile");
    }
    @FXML void DashboardClicked() throws Exception
    {
        Main.setRoot("ManagerDashboard");
    }
    @FXML void ProductClicked() throws Exception
    {
        Main.setRoot("ManagerProduct");
    }
    @FXML void ShopClicked() throws Exception
    {
        Main.setRoot("ManagerShop");
    }
    @FXML void EmployeeClicked() throws Exception
    {
        Main.setRoot("ManagerEmployee");
    }
    @FXML void SalesClicked() throws Exception
    {
        Main.setRoot("ManagerSales");
    }
    @FXML void StockClicked() throws Exception
    {
        Main.setRoot("ManagerStock");
    }
    @FXML void ChartGraphClicked() throws Exception
    {
        Main.setRoot("ManagerChartGraph");
    }

    @FXML void ManagerIdEntered() throws Exception
    {
        Main.openCon("managerdata");
        Document query = new Document("ManagerId", ManagerId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null || value.getBoolean("SuperManager") != null)
        {
            Details.setVisible(false);
            ErrorMessage.setText("** No Manager Available **");
            ErrorMessage.setVisible(true);
            UpdateManager.setDisable(true);
            DeleteManager.setDisable(true);   
        }
        else if(value.getString("ManagerName").equals(manager.getText()))
        {
            Details.setVisible(false);
            ErrorMessage.setText("** Go to Edit Profile **");
            ErrorMessage.setVisible(true);
            UpdateManager.setDisable(true);
            DeleteManager.setDisable(true);   
        }
        else
        {
            Details.setVisible(true);
            ErrorMessage.setVisible(false);
            Name.setText(value.getString("Name"));
            Gender.setText(value.getString("Gender"));
            DateofBirth.setText(value.getString("DateofBirth"));
            Age.setText(value.getInteger("Age").toString());
            Phone.setText(value.getString("Phone"));
            EmailId.setText(value.getString("EmailId"));
            ManagerName.setText(value.getString("ManagerName"));
            UpdateManager.setDisable(false);
            DeleteManager.setDisable(false);
            sendManagerId = ManagerId.getText();
            sendManagerName = ManagerName.getText();
        }
        Main.closeCon();
    }
    @FXML void AddManagerClicked() throws Exception
    {
        Main.setRoot("ManagerManagerAdd");
    }
    @FXML void UpdateManagerClicked() throws Exception
    {
        Main.setRoot("ManagerManagerUpdate");
    }
    @FXML void DeleteManagerClicked() throws Exception
    {
        Main.openCon("managerdata");
        Document query = new Document("ManagerId",ManagerId.getText());
        Main.collection.deleteOne(query);
        Main.closeCon();
        Main.openCon("managerlogin");
        query = new Document("ManagerName",ManagerName.getText());
        Main.collection.deleteOne(query);
        Main.closeCon();
        Details.setVisible(false);
        ErrorMessage.setText("** Manager Deleted Successfully **");
        UpdateManager.setDisable(true);
        DeleteManager.setDisable(true);
        ManagerId.setText("");
        ErrorMessage.setVisible(true);
    }
}
