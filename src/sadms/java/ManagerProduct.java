package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sadms.Main;

public class ManagerProduct {
    @FXML GridPane Details;
    @FXML Label manager, ErrorMessage;
    @FXML TextField ProductId, ProductName, SellingPrice, CostPrice, Description;
    @FXML Button AddProduct, UpdateProduct, DeleteProduct;
    static String sendProductId, sendmessage;
    public void initialize()
    {
        ErrorMessage.setVisible(false);
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

    //Retrive data from database
    @FXML void ProductIdEntered() throws Exception
    {
        Main.openCon("product");
        Document query = new Document("ProductId", ProductId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            Details.setVisible(false);
            ErrorMessage.setText("** No Product Available **");
            ErrorMessage.setVisible(true);
            UpdateProduct.setDisable(true);
            DeleteProduct.setDisable(true);   
        }
        else
        {
            Details.setVisible(true);
            ErrorMessage.setVisible(false);
            ProductName.setText(value.getString("ProductName"));
            SellingPrice.setText(value.getInteger("SellingPrice").toString());
            CostPrice.setText(value.getInteger("CostPrice").toString());
            Description.setText(value.getString("Description"));
            UpdateProduct.setDisable(false);
            DeleteProduct.setDisable(false);
            sendProductId = ProductId.getText();
        }
        Main.closeCon();
    }

    //Add product to database
    @FXML void AddProductClicked() throws Exception
    {
        Main.setRoot("ManagerProductAdd");
    }
    //Update product to database
    @FXML void UpdateProductClicked() throws Exception
    {
        Main.setRoot("ManagerProductUpdate");
    }
    //Delete product to database
    @FXML void DeleteProductClicked() throws Exception
    {
        Main.openCon("product");
        Document query = new Document("ProductId",ProductId.getText());
        Main.collection.deleteOne(query);
        Details.setVisible(false);
        ErrorMessage.setText("** Product Deleted Successfully **");
        UpdateProduct.setDisable(true);
        DeleteProduct.setDisable(true);
        ProductId.setText("");
        ErrorMessage.setVisible(true);
        Main.closeCon();
    }
}
