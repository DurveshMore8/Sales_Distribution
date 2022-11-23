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

public class ManagerEmployeeAdd {
    @FXML Label manager,ErrorMessage;
    @FXML TextField EmployeeId, Name, EmployeeName, DateofBirth, Phone, GmailId;
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
        if(EmployeeId.getText().equals("") || Name.getText().equals("") || EmployeeName.getText().equals("") || DateofBirth.getText().equals("") || Phone.getText().equals("") || GmailId.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
            return;
        }
        Main.openCon("employeedata");
        Document query = new Document("EmployeeId",EmployeeId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            try
            {
                
                query.append("Name",Name.getText()).append("EmployeeName", EmployeeName.getText()).append("Gender", ((RadioButton) Gender.getSelectedToggle()).getText()).append("DateofBirth",DateofBirth.getText()).append("Age", Period.between(LocalDate.parse(DateofBirth.getText()), LocalDate.now()).getYears()).append("Phone",Phone.getText()).append("GmailId",GmailId.getText()).append("ManagerName",manager.getText());
                Main.collection.insertOne(query);
                ManagerEmployee.sendmessage = "** Employee Added Successfully **";
                Main.closeCon();
                Main.openCon("employeelogin");
                query = new Document("EmployeeName",EmployeeName.getText()).append("EmployeePassword",EmployeeName.getText());
                Main.collection.insertOne(query);
                Main.setRoot("ManagerEmployee");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Enter Date in Proper Format **");
                ErrorMessage.setVisible(true);
                e.printStackTrace();
            }
            finally
            {
                Main.closeCon();
            }
        }
        else
        {
            ErrorMessage.setText("** Employee of this Id already exists **");
            ErrorMessage.setVisible(true);
        }
    }
    @FXML void ClearClicked() throws Exception
    {
        EmployeeId.clear();
        Name.clear();
        EmployeeName.clear();
        ((RadioButton) Gender.getSelectedToggle()).setSelected(false);
        DateofBirth.clear();
        Phone.clear();
        GmailId.clear();
    }
}
