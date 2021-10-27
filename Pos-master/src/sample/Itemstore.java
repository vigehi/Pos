package sample;

import javafx.beans.property.SimpleStringProperty;

public class Itemstore {
    private SimpleStringProperty itemNome;
    private SimpleStringProperty itemCategory;
    private SimpleStringProperty itemLbl;

    public Itemstore(String itemNome, String itemCategory, String itemLbl) {
        this.itemNome = new SimpleStringProperty(itemNome);
        this.itemCategory = new SimpleStringProperty(itemCategory);
        this.itemLbl = new SimpleStringProperty(itemLbl);
    }

    public String getItemNome(){
        return itemNome.get();
    }

    public void setItemNome(String active){
        this.itemNome = new SimpleStringProperty(active);
    }

    public String getItemCategory() {
        return itemCategory.get();
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = new SimpleStringProperty(itemCategory);
    }

    public String getItemLbl() {
        return itemLbl.get();
    }

    public void setItemLbl(String itemLbl) {
        this.itemLbl = new SimpleStringProperty(itemLbl);
    }

}
