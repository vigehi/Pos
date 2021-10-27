package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.webComm.sendData;

public class Itemslist implements Initializable {

    @FXML
    private TableView<Itemstore> item_list;

    @FXML
    private TableColumn<Itemstore, String> itemLabel;

    @FXML
    private TableColumn<Itemstore, String> itemCategory;

    @FXML
    private TableColumn<Itemstore, String> itemNombre;

    JSONArray jaItems = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemLabel.setCellValueFactory(new PropertyValueFactory<>("itemLbl"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        itemNombre.setCellValueFactory(new PropertyValueFactory<>("itemNome"));



        ObservableList<Itemstore> data = FXCollections.observableArrayList();

        String sResp = null;
        try {
            sResp = webComm.auth("https://demo.dewcis.com/hcm/pos_server","root", "baraza");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jResp = null;
        try {
            jResp = new JSONObject(sResp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            int ResultCode = jResp.getInt("ResultCode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if(jResp.has("ResultCode") && (jResp.getInt("ResultCode") == 0)) {
                String auth = jResp.getString("access_token");

                String sItems = sendData("https://demo.dewcis.com/hcm/pos_server" + "?view=405:0", auth, "read", "{}");
                JSONObject jItems = new JSONObject(sItems);
                if(jItems.has("data")) {
                    jaItems = jItems.getJSONArray("data");
                    for(int j = 0; j < jaItems.length(); j++){
                        JSONObject jItem = jaItems.getJSONObject(j);
                        data.add(new Itemstore(jItem.getString("item_name"),jItem.getString("item_category_name"),jItem.getString("item_id")));
                    }
                    item_list.setItems(data);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            Itemstore is = item_list.getItems().get(item_list.getSelectionModel().getSelectedIndex());



        }
    }

}

