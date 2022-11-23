package sadms.java;
import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sadms.Main;

public class ManagerEditProfile {
    @FXML Label manager, ErrorMessage;
    @FXML TextField ManagerName;
    @FXML PasswordField EnterPassword, ConfirmPassword;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        ErrorMessage.setText("");
    }
    @FXML void Log_Out() throws Exception
    {
        Main.setRoot("ManagerLogin");
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
        ErrorMessage.setText("");
        if(ManagerName.getText().equals("") || EnterPassword.getText().equals("") || ConfirmPassword.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
        }
        else if(EnterPassword.getText().equals(ConfirmPassword.getText()))
        {
            Main.openCon("managerlogin");
            Document query = new Document("ManagerName",ManagerName.getText());
            Document value = Main.collection.find(query).first();
            if(value == null || value.getString("ManagerName").equals(ManagerName.getText()))
            {
                Main.collection.updateOne(Filters.eq("ManagerName",manager.getText()), Updates.set("ManagerName",ManagerName.getText()));
                Main.collection.updateOne(Filters.eq("ManagerName",manager.getText()),Updates.set("Password",EnterPassword.getText()));
                ErrorMessage.setText("** Credentials Updated Successfully **");
            }
            else
            {
                ErrorMessage.setText("** Manager with this Id already exists **");
            }
            Main.closeCon();
        }
        else
        {
            ErrorMessage.setText("** Password doesn't match **");
        }
    }
    @FXML void ClearClicked() throws Exception
    {
        ManagerName.clear();
        EnterPassword.clear();
        ConfirmPassword.clear();
    }
}
