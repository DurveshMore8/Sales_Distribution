package sadms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Scene scene;

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
