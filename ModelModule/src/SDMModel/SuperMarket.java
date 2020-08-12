package SDMModel;

import SDMGenerated.SDMItem;
import SDMGenerated.SDMStore;
import SDMGenerated.SuperDuperMarketDescriptor;

import java.util.HashMap;
import java.util.List;

public class SuperMarket {
    // /Users/dor.cohen/Downloads/ex1-big.xml
    private HashMap<Integer, Store> stores = new  HashMap<Integer ,Store>();
    private HashMap<Integer ,Item> items = new  HashMap<Integer ,Item>();

    public HashMap<Integer, Store> getStores() {
        return stores;
    }

    public void setStores(HashMap<Integer, Store> stores) {
        this.stores = stores;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, Item> items) {
        this.items = items;
    }

    public static SuperMarket creatInstance(SuperDuperMarketDescriptor superMarketSDM) {
        SuperMarket instance = new SuperMarket();

        List<SDMItem>itemsSDM = superMarketSDM.getSDMItems().getSDMItem();
        List<SDMStore>storesSDM = superMarketSDM.getSDMStores().getSDMStore();
        for(SDMItem sdmItem : itemsSDM){
            Item newItem = Item.createInstanceBySDM(sdmItem);
            instance.getItems().put(newItem.id,newItem);
        }

        for(SDMStore sdmStore : storesSDM){
            Store newStore = Store.createInstanceBySDM(sdmStore);
            instance.getStores().put(newStore.getId(), newStore);
        }

        return instance;
    }

    public Item getItemByID(int itemId){
        Item item = items.getOrDefault(itemId, null);
        if (item ==null)
            return null;//TODO error

        return item;
    }

    public double getItemAveragePriceByID(int itemId){
        double sumPriceOfItems = 0;

        Item item = items.getOrDefault(itemId, null);
        if(item.storesWhoSellTheItem == null)
            return 0; //no stores sell the item
        if (item ==null)
            //TODO error
            return 0;
        int numberOfStoresSellTheItem =  item.storesWhoSellTheItem.size();
        for (Store store : item.storesWhoSellTheItem){
            sumPriceOfItems = store.getItemPrice(itemId);
        }
        return sumPriceOfItems/numberOfStoresSellTheItem;
    }

    public int getNumberOfTimesItemWasPurchased(int itemId){
        int numberOfTimeItemPurched = 0;
        Item item = items.getOrDefault(itemId, null);
        if (item ==null)
            //TODO error
            return 0;
        for (Store store : item.storesWhoSellTheItem){
            numberOfTimeItemPurched = store.getNumberOfTimesItemWasSold(itemId);
        }
        return numberOfTimeItemPurched;
    }
}
