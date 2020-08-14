package SDMModel;


import SDMGenerated.SDMItem;

import java.util.ArrayList;

public class Item {
    public enum InfoOptions {
        Name, ID, Category;

        public String getInfo(Item item) {
            switch (this) {
                case ID:
                    return String.valueOf(item.getId());
                case Name:
                    return item.getName();
                case Category:
                    return String.valueOf(item.getPurchaseCategory());
                default:
                    return "Unknown";
            }
        }
    }

    public enum PurchaseCategory {
        QUANTITY,
        WEIGHT;

        @Override
        public String toString() {
            return name().substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    public int totalNumberOfTimePurchased = 0;
    public ArrayList<Store> storesWhoSellTheItem; //should it be static?
    private String name;
    private PurchaseCategory purchaseCategory; //can be enum
    private int id;



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

    public PurchaseCategory getPurchaseCategory() {
        return purchaseCategory;
    }

    public void setPurchaseCategory(String purchaseCategory) {
        if(purchaseCategory.equals("Quantity"))
            this.purchaseCategory = PurchaseCategory.QUANTITY;
        else if(purchaseCategory.equals("Weight"))
            this.purchaseCategory = PurchaseCategory.WEIGHT;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Store> getStoresWhoSellTheItem(){ return storesWhoSellTheItem; }

    public  int getTotalNumberOfTimePurchased() {
        return totalNumberOfTimePurchased;
    }


}
