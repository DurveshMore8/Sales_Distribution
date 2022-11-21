package sadms.java;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sadms.Main;

public class ManagerEmployee {
    @FXML GridPane Details;
    @FXML Label manager, ErrorMessage;
    @FXML TextField EmployeeId, Name, EmployeeName, DateofBirth, Age, Phone, GmailId;
    @FXML Button AddEmployee, UpdateEmployee, DeleteEmployee;
    static String sendEmployeeId, sendEmployeeName, sendmessage;

    public void initialize()
    {
        manager.setText(ManagerLogin.value.getString("ManagerName"));
        ErrorMessage.setVisible(false);
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
    @FXML void ProductClicked() throws Exception
    {
        Main.setRoot("ManagerProduct");
    }
    @FXML void ShopClicked() throws Exception
    {
        Main.setRoot("ManagerShop");
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

    @FXML void EmployeeIdEntered() throws Exception
    {
        Main.openCon("employeedata");
        Document query = new Document("EmployeeId", EmployeeId.getText());
        Document value = Main.collection.find(query).first();
        if(value == null)
        {
            Details.setVisible(false);
            ErrorMessage.setText("** No Employee Available **");
            ErrorMessage.setVisible(true);
            UpdateEmployee.setDisable(true);
            DeleteEmployee.setDisable(true);   
        }
        else
        {
            Details.setVisible(true);
            ErrorMessage.setVisible(false);
            Name.setText(value.getString("Name"));
            EmployeeName.setText(value.getString("EmployeeName"));
            DateofBirth.setText(value.getString("DateofBirth"));
            Age.setText(value.getInteger("Age").toString());
            Phone.setText(value.getString("Phone"));
            GmailId.setText(value.getString("GmailId"));
            UpdateEmployee.setDisable(false);
            DeleteEmployee.setDisable(false);
            sendEmployeeId = EmployeeId.getText();
            sendEmployeeName = EmployeeName.getText();
        }
        Main.closeCon();
    }

    @FXML void AddEmployeeClicked() throws Exception
    {
        Main.setRoot("ManagerEmployeeAdd");
    }
    @FXML void UpdateEmployeeClicked() throws Exception
    {
        Main.setRoot("ManagerEmployeeUpdate");
    }
    @FXML void DeleteEmployeeClicked() throws Exception
    {
        Main.openCon("employeedata");
        Document query = new Document("EmployeeId",EmployeeId.getText());
        Main.collection.deleteOne(query);
        Main.closeCon();
        Main.openCon("employeelogin");
        query = new Document("EmployeeName",EmployeeName.getText());
        Main.collection.deleteOne(query);
        Main.closeCon();
        Details.setVisible(false);
        ErrorMessage.setText("** Employee Deleted Successfully **");
        UpdateEmployee.setDisable(true);
        DeleteEmployee.setDisable(true);
        EmployeeId.setText("");
        ErrorMessage.setVisible(true);
    }
}
