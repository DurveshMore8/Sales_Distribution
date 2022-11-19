package sadms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main extends Application {
    public static Scene scene;
    static MongoClient client;
    public static MongoCollection<Document> collection;

    public static void openCon(String col)
    {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase("sadms");
        collection = database.getCollection(col);
    }
    public static void closeCon()
    {
        client.close();
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        scene = new Scene(LoadFXML("Login"));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws Exception
    {
        scene.setRoot(LoadFXML(fxml));
    }

    public static Parent LoadFXML(String fxml) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/"+fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {   
        launch();
    }
}
