package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.webComm.sendData;

public class Items implements Initializable {

    @FXML
    public TableView<store> itemsView;

    @FXML
    public TableColumn<store, String> itemId;

    @FXML
    public TableColumn<store, String> categoryName;

    @FXML
    public TableColumn<store, String> itemName;

    @FXML
    public TableColumn<store, String> salesPrice;

    @FXML
    public TableColumn<store, String> purchase;

    @FXML
    public TableColumn<store, String> active;

    @FXML
    public TableColumn<store, String> inventory;

    @FXML
    public TableColumn<store, String> keyField;

    JSONArray jaItems = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemId.setCellValueFactory(new PropertyValueFactory<store, String>("itemId"));
        categoryName.setCellValueFactory(new PropertyValueFactory<store, String>("categoryName"));
        itemName.setCellValueFactory(new PropertyValueFactory<store, String>("itemName"));
        salesPrice.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
        purchase.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        active.setCellValueFactory(new PropertyValueFactory<>("active"));
        inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        keyField.setCellValueFactory(new PropertyValueFactory<>("item"));

        ObservableList<store> data = FXCollections.observableArrayList();

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
                        data.add(new store(jItem.getString("is_active"),jItem.getString("item_category_name"),jItem.getString("item_id"),jItem.getString("keyfield"),jItem.getString("sales_price"),jItem.getString("purchase_price"),jItem.getString("item_name"),jItem.getString("inventory")));
                    }
                    itemsView.setItems(data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
