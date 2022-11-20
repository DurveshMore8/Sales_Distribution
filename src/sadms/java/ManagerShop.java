package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sadms.Main;

public class ManagerShop {
    @FXML Label manager, ErrorMessage;
    @FXML TextField ShopId, Location, City, State, Pincode;
    @FXML GridPane Details;
    @FXML Button AddShop, UpdateShop, DeleteShop;
    static String sendShopId, sendmessage;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
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
    @FXML void ProductClicked() throws Exception
    {
        Main.setRoot("ManagerProduct");
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
    @FXML void ShopIdEntered() throws Exception
    {
        Main.openCon("shop");
        Document query = new Document("ShopId",ShopId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            Details.setVisible(false);
            ErrorMessage.setText("** No Shop Available **");
            ErrorMessage.setVisible(true);
            UpdateShop.setDisable(true);
            DeleteShop.setDisable(true);
        }
        else
        {
            Details.setVisible(true);
            ErrorMessage.setVisible(false);
            Location.setText(value.getString("Location"));
            City.setText(value.getString("City"));
            State.setText(value.getString("State"));
            Pincode.setText(value.getInteger("Pincode").toString());
            UpdateShop.setDisable(false);
            DeleteShop.setDisable(false);
            sendShopId = ShopId.getText();
        }
        Main.closeCon();
    }
    @FXML void AddShopClicked() throws Exception
    {
        Main.setRoot("ManagerShopAdd");
    }
    @FXML void UpdateShopClicked() throws Exception
    {
        Main.setRoot("ManagerShopUpdate");
    }
    @FXML void DeleteShopClicked() throws Exception
    {
        Main.openCon("shop");
        Document query = new Document("ShopId",ShopId.getText());
        Main.collection.deleteOne(query);
        Details.setVisible(false);
        ErrorMessage.setText("** Shop Deleted Successfully **");
        UpdateShop.setDisable(true);
        DeleteShop.setDisable(true);
        ShopId.setText("");
        ErrorMessage.setVisible(true);
        Main.closeCon();
    }
}
