package sadms.java;
import java.time.LocalDate;
import java.time.Period;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sadms.Main;

public class ManagerEmployeeUpdate {
    @FXML Label manager,ErrorMessage;
    @FXML TextField EmployeeId, Name, DateofBirth, Phone, EmailId;
    @FXML RadioButton Male, Female;
    @FXML ToggleGroup Gender;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        Main.openCon("employeedata");
        Document query = new Document("EmployeeId",ManagerEmployee.sendEmployeeId);
        Document value = Main.collection.find(query).first();
        EmployeeId.setText(value.getString("EmployeeId"));
        Name.setText(value.getString("Name"));
        if(value.getString("Gender").equals(Male.getText())) Male.setSelected(true);
        else Female.setSelected(true);
        DateofBirth.setText(value.getString("DateofBirth"));
        Phone.setText(value.getString("Phone"));
        EmailId.setText(value.getString("EmailId"));
        Main.closeCon();
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

    @FXML void UpdateClicked() throws Exception
    {
        if(Name.getText().equals("") || (RadioButton) Gender.getSelectedToggle() == null || DateofBirth.getText().equals("") || Phone.getText().equals("") || EmailId.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
        }
        else
        {
            ErrorMessage.setVisible(false);
            Main.openCon("employeedata");
            try
            {
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("Name",Name.getText()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("Gender", ((RadioButton) Gender.getSelectedToggle()).getText()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("DateofBirth",DateofBirth.getText()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("Age", Period.between(LocalDate.parse(DateofBirth.getText()), LocalDate.now()).getYears()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("Phone",Phone.getText()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("EmailId",EmailId.getText()));
                Main.collection.updateOne(Filters.eq("EmployeeId",ManagerEmployee.sendEmployeeId), Updates.set("UpdatedBy",manager.getText()));
                ManagerEmployee.sendmessage = "** Employee Updated Successfully **";
                Main.setRoot("ManagerEmployee");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Enter Date in Proper Format **");
                ErrorMessage.setVisible(true);
            }
            finally
            {
                Main.closeCon();
            }
        }
    }
    @FXML void ClearClicked() throws Exception
    {
        Name.clear();
        ((RadioButton) Gender.getSelectedToggle()).setSelected(false);
        DateofBirth.clear();
        Phone.clear();
        EmailId.clear();
    }
}
