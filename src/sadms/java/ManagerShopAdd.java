package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sadms.Main;

public class ManagerShopAdd {
    @FXML Label manager,ErrorMessage;
    @FXML TextField ShopId, Location, City, State, Pincode;

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
        if(ShopId.getText().equals("") || Location.getText().equals("") || City.getText().equals("") || State.getText().equals("") || Pincode.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
            return;
        }
        Main.openCon("shop");
        Document query = new Document("ShopId",ShopId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            try
            {
                query.append("Location",Location.getText()).append("City", City.getText()).append("State",State.getText()).append("Pincode",Integer.parseInt(Pincode.getText())).append("ManagerName",manager.getText());
                Main.collection.insertOne(query);
                ManagerShop.sendmessage = "** Shop Added Successfully **";
                Main.setRoot("ManagerShop");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Enter Pincode in Integer Only **");
                ErrorMessage.setVisible(true);
            }
            finally
            {
                Main.closeCon();
            }
        }
        else
        {
            ErrorMessage.setText("** Shop of this Id already exists **");
            ErrorMessage.setVisible(true);
        }
    }
    @FXML void ClearClicked() throws Exception
    {
        ShopId.clear();
        Location.clear();
        City.clear();
        State.clear();
        Pincode.clear();
    }
}
