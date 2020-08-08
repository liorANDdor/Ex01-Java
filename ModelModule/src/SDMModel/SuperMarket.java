package SDMModel;

import SDMGenerated.SDMItem;
import SDMGenerated.SDMStore;
import SDMGenerated.SuperDuperMarketDescriptor;

import java.util.List;

public class SuperMarket {

    private List<Store> stores;
    private List<Item> items;

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static SuperMarket creatInstance(SuperDuperMarketDescriptor superMarketSDM) {
        SuperMarket instance = new SuperMarket();
        List<SDMItem>itemsSDM = superMarketSDM.getSDMItems().getSDMItem();
        List<SDMStore>storesSDM = superMarketSDM.getSDMStores().getSDMStore();
        for(SDMItem sdmItem : itemsSDM){
            Item newItem = Item.createInstanceBySDM(sdmItem);
            instance.getItems().add(newItem);
        }

        for(SDMStore sdmStore : storesSDM){
            Store newStore = Store.createInstanceBySDM(sdmStore);
            instance.getStores().add(newStore);
        }

        return instance;
    }
}
