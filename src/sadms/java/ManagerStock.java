package sadms.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sadms.Main;

public class ManagerStock {
    @FXML Label manager;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
    }
    @FXML void Log_Out(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }
}
