package sadms.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sadms.Main;

public class ManagerDashboard {

    @FXML void Log_Out(ActionEvent event) throws Exception
    {
        Main.setRoot("ManagerLogin");
    }

}
