package sadms.java;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sadms.Main;

public class EmployeeLogin
{
    @FXML RadioButton rbManager;
    @FXML TextField tfEmployeeName;
    @FXML TextField tfEmployeePassword;
    @FXML Label ErrorMessage;

    @FXML void gotoManager(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
    @FXML void LogInClicked(ActionEvent event) throws Exception
    {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase("sadms");
        MongoCollection<Document> collection = database.getCollection("employee_login");
        
        Document query = new Document("EmployeeName",tfEmployeeName.getText()).append("EmployeePassword",tfEmployeePassword.getText());
        Document value = collection.find(query).first();
        client.close();
        if(value == null)
        {
            ErrorMessage.setText("* Wrong Credentials Entered *");
        }
        else
        {
            ErrorMessage = null;
            Main.setRoot("EmployeeDashboard");
        }
    }
    @FXML void ClearClicked(ActionEvent event) throws Exception
    {
        tfEmployeeName.clear();
        tfEmployeePassword.clear();
    }
}