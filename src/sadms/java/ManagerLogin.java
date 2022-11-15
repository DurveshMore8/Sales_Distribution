package sadms.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import sadms.Main;

public class ManagerLogin
{
    @FXML RadioButton rbEmployee;
    @FXML TextField tfManagerName;
    @FXML TextField tfManagerGmail;
    @FXML TextField tfManagerPassword;
    @FXML Label ErrorMessage;

    @FXML void gotoEmployee(ActionEvent event) throws Exception
    {
        Main.setRoot("EmployeeLogin");
    }
    @FXML  void LogInClicked(ActionEvent event) throws Exception
    {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase("sadms");
        MongoCollection<Document> collection = database.getCollection("manager_login");

        Document query = new Document("ManagerName",tfManagerName.getText()).append("ManagerGmail", tfManagerGmail.getText()).append("ManagerPassword", tfManagerPassword.getText());
        Document value = collection.find(query).first();
        client.close();
        if(value == null)
        {
            ErrorMessage.setText("* Wrong Credentials Entered *");
        }
        else
        {
            ErrorMessage = null;
            Main.setRoot("ManagerDashboard");
        }
    }
    @FXML void ClearClicked(ActionEvent event)
    {
        tfManagerName.clear();
        tfManagerGmail.clear();
        tfManagerPassword.clear();
    }
}
