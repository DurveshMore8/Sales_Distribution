package sadms.java;

import org.bson.Document;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sadms.Main;

public class ManagerProductAdd {
    @FXML Label manager,ErrorMessage;
    @FXML TextField ProductId, ProductName, CostPrice, SellingPrice, Description;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
    }
    @FXML void Log_Out(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void ProductClicked(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerProduct");
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
    @FXML void AddClicked() throws Exception
    {
        ErrorMessage.setVisible(false);
        if(ProductId.getText().equals("") || ProductName.getText().equals("") || CostPrice.getText().equals("") || SellingPrice.getText().equals("") || Description.getText().equals(""))
        {
            ErrorMessage.setText("** Fill all Text-Fields **");
            ErrorMessage.setVisible(true);
            return;
        }
        Main.openCon("product");
        Document query = new Document("ProductId",ProductId.getText()).append("ProductName", ProductName.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            try
            {
                query.append("CostPrice",Integer.parseInt(CostPrice.getText())).append("SellingPrice",Integer.parseInt(SellingPrice.getText())).append("Description",Description.getText()).append("ManagerName",manager.getText());
                Main.collection.insertOne(query);
                ManagerProduct.sendmessage = "** Product Added Successfully **";
                Main.setRoot("ManagerProduct");
            }
            catch(Exception e)
            {
                ErrorMessage.setText("** Enter Prices in Integer Only **");
                ErrorMessage.setVisible(true);
            }
        }
        else
        {
            ErrorMessage.setText("** Product of this Id already exists **");
            ErrorMessage.setVisible(true);
        }
        Main.closeCon();
    }
    @FXML void ClearClicked()
    {
        ProductId.clear();
        ProductName.clear();
        CostPrice.clear();
        SellingPrice.clear();
        Description.clear();
    }
}
