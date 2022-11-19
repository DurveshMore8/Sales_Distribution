package sadms.java;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
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
    Document value;

    public ManagerProduct()
    {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase("sadms");
        MongoCollection<Document> collection = database.getCollection("Product");
        Document query = new Document("ProductId","Nex123");
        value = collection.find(query).first();
        client.close();
    }
    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
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
    @FXML void ProductIdEntered(ActionEvent event) throws Exception
    {
        if(value.getString("ProductId").equals(ProductId.getText()))
        {
            Details.setVisible(true);
            ErrorMessage.setVisible(false);
            ProductName.setText(value.getString("ProductName"));
            SellingPrice.setText(value.getInteger("SellingPrice").toString());
            CostPrice.setText(value.getInteger("CostPrice").toString());
            AddProduct.setVisible(false);
            UpdateProduct.setVisible(true);
            DeleteProduct.setVisible(true);
        }
        else
        {
            Details.setVisible(false);
            ErrorMessage.setVisible(true);
            AddProduct.setVisible(true);
            UpdateProduct.setVisible(false);
            DeleteProduct.setVisible(false);
        }
    }
}
