package SDMModel;


import SDMGenerated.SDMItem;

import java.util.ArrayList;

public class Item {

    protected String name;
    protected String purchaseCategory;
    protected int id;
    protected int numberOfTimePurchased;
    public ArrayList<Store> storesWhoSellTheItem;

    public static Item createInstanceBySDM(SDMItem sdmItem) {
        Item newItem = new Item();
        newItem.setId(sdmItem.getId());
        newItem.setName(sdmItem.getName());
        newItem.setPurchaseCategory(sdmItem.getPurchaseCategory());
        
        return newItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory = purchaseCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Store> getStoresWhoSellTheItem(){ return storesWhoSellTheItem; }



}
