package sadms.java;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sadms.Main;

public class ManagerProductUpdate {
    @FXML Label manager,ErrorMessage;
    @FXML TextField ProductId, ProductName, CostPrice, SellingPrice, Description;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        Main.openCon("product");
        Document query = new Document("ProductId",ManagerProduct.sendProductId);
        Document value = Main.collection.find(query).first();
        ProductId.setText(value.getString("ProductId"));
        ProductName.setText(value.getString("ProductName"));
        CostPrice.setText(value.getInteger("CostPrice").toString());
        SellingPrice.setText(value.getInteger("SellingPrice").toString());
        Description.setText(value.getString("Description"));
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
    //Add Query
    @FXML void UpdateClicked() throws Exception
    {
        if(CostPrice.getText().equals("") || SellingPrice.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
        }
        else
        {
            ErrorMessage.setVisible(false);
            Main.openCon("product");
            try
            {
                Main.collection.updateOne(Filters.eq("ProductId",ManagerProduct.sendProductId), Updates.set("CostPrice",Integer.parseInt(CostPrice.getText())));
                Main.collection.updateOne(Filters.eq("ProductId",ManagerProduct.sendProductId), Updates.set("SellingPrice",Integer.parseInt(SellingPrice.getText())));
                Main.collection.updateOne(Filters.eq("ProductId",ManagerProduct.sendProductId), Updates.set("ManagerName",manager.getText()));
                ManagerProduct.sendmessage = "** Product Updated Successfully **";
                Main.setRoot("ManagerProduct");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Enter Prices in Integer Only **");
                ErrorMessage.setVisible(true);
            }
            finally
            {
                Main.closeCon();
            }
        }
    }
    @FXML void ClearClicked()
    {
        CostPrice.clear();
        SellingPrice.clear();
    }
}
