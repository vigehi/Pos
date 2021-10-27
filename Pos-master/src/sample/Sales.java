package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Sales implements Initializable {

    @FXML
    private Button item_list;
    @FXML
    private TextField textfield;
    @FXML
    private TableView<Itemslist> paa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void listItems(ActionEvent event) {
        try {
            Parent pur = FXMLLoader.load(getClass().getResource("itemslist.fxml"));
            Scene s = new Scene(pur);
            Stage stg = new Stage();
            stg.setResizable(false);
            stg.setScene(s);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* private void setCellValueFromTableToTexted(){
        item_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Itemstore is = item_list.getItems().get(item_list.getSelectionModel().getSelectedIndex());
                textfield.setText
            }
        });
    }*/

}

