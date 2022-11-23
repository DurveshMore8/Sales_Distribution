package sadms.java;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sadms.Main;

public class ManagerShopUpdate {
    @FXML Label manager,ErrorMessage;
    @FXML TextField ShopId, Location, City, State, Pincode;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        Main.openCon("shop");
        Document query = new Document("ShopId",ManagerShop.sendShopId);
        Document value = Main.collection.find(query).first();
        ShopId.setText(value.getString("ShopId"));
        Location.setText(value.getString("Location"));
        City.setText(value.getString("City"));
        State.setText(value.getString("State"));
        Pincode.setText(value.getInteger("Pincode").toString());
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
        if(Location.getText().equals("") || City.getText().equals("") || State.getText().equals("") || Pincode.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
        }
        else
        {
            ErrorMessage.setVisible(false);
            Main.openCon("shop");
            try
            {
                Main.collection.updateOne(Filters.eq("ShopId",ManagerShop.sendShopId), Updates.set("Location",Location.getText()));
                Main.collection.updateOne(Filters.eq("ShopId",ManagerShop.sendShopId), Updates.set("City",City.getText()));
                Main.collection.updateOne(Filters.eq("ShopId",ManagerShop.sendShopId), Updates.set("State",State.getText()));
                Main.collection.updateOne(Filters.eq("ShopId",ManagerShop.sendShopId), Updates.set("Pincode",Integer.parseInt(Pincode.getText())));
                Main.collection.updateOne(Filters.eq("ShopId",ManagerShop.sendShopId), Updates.set("ManagerName",manager.getText()));
                ManagerShop.sendmessage = "** Shop Updated Successfully **";
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
    }
    @FXML void ClearClicked() throws Exception
    {
        Location.clear();
        City.clear();
        State.clear();
        Pincode.clear();
    }
}
