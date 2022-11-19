package sadms.java;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javafx.event.ActionEvent;
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
        CostPrice.setText(value.getString("CostPrice"));
        SellingPrice.setText(value.getString("SellingPrice"));
        Description.setText(value.getString("Description"));
        Main.closeCon();
    }
    @FXML void Log_Out(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void ShopClicked(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerShop");
    }
    @FXML void EmployeeClicked(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerEmployee");
    }
    @FXML void SalesClicked(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerSales");
    }
    @FXML void StockClicked(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerStock");
    }
    @FXML void ChartGraphClicked(ActionEvent event) throws Exception
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
            Main.collection.updateOne(Filters.eq("ProductId",ManagerProduct.sendProductId), Updates.set("CostPrice",CostPrice.getText()));
            Main.collection.updateOne(Filters.eq("ProductId",ManagerProduct.sendProductId), Updates.set("SellingPrice",SellingPrice.getText()));
            Main.closeCon();
            Main.setRoot("ManagerProduct");
        }
    }
    @FXML void ClearClicked()
    {
        CostPrice.clear();
        SellingPrice.clear();
    }
}
