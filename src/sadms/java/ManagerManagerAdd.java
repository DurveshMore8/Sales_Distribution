package sadms.java;
import java.time.LocalDate;
import java.time.Period;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sadms.Main;

public class ManagerManagerAdd {
    @FXML Label manager,ErrorMessage;
    @FXML TextField ManagerId, Name, DateofBirth, Phone, EmailId;
    @FXML ToggleGroup Gender;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
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
    @FXML void ManagerClicked() throws Exception
    {
        Main.setRoot("ManagerManager");
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

    @FXML void AddClicked() throws Exception
    {
        ErrorMessage.setVisible(false);
        if(ManagerId.getText().equals("") || Name.getText().equals("") || (RadioButton) Gender.getSelectedToggle() == null || DateofBirth.getText().equals("") || Phone.getText().equals("") || EmailId.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
            return;
        }
        Main.openCon("managerdata");
        Document query = new Document("ManagerId",ManagerId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            try
            {
                query.append("Name",Name.getText()).append("Gender", ((RadioButton) Gender.getSelectedToggle()).getText()).append("DateofBirth",DateofBirth.getText()).append("Age", Period.between(LocalDate.parse(DateofBirth.getText()), LocalDate.now()).getYears()).append("Phone",Phone.getText()).append("EmailId",EmailId.getText()).append("ManagerName",Name.getText().replaceAll(" ", "")).append("AddedBy",manager.getText());
                Main.collection.insertOne(query);
                ManagerManager.sendmessage = "** Manager Added Successfully **";
                Main.closeCon();
                Main.openCon("managerlogin");
                query = new Document("ManagerName", Name.getText().replaceAll(" ", "")).append("EmailId", EmailId.getText()).append("Password", Name.getText().replaceAll(" ", ""));
                Main.collection.insertOne(query);
                Main.setRoot("ManagerManager");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Fill all Text-Fields Properly **");
                ErrorMessage.setVisible(true);
            }
            finally
            {
                Main.closeCon();
            }
        }
        else
        {
            ErrorMessage.setText("** Manager of this Id already exists **");
            ErrorMessage.setVisible(true);
        }
    }

    @FXML void ClearClicked() throws Exception
    {
        ManagerId.clear();
        Name.clear();
        ((RadioButton) Gender.getSelectedToggle()).setSelected(false);
        DateofBirth.clear();
        Phone.clear();
        EmailId.clear();
    }
}