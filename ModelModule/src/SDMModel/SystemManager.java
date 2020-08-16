package SDMModel;

import SDMGenerated.SuperDuperMarketDescriptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SystemManager {

    private SuperMarket superMarket;
    private XmlUtilities xmlUtilities;
    private boolean thereIsXmlLoaded = false;

    public XmlUtilities getXmlUtilities() {
        return xmlUtilities;
    }

    public SuperMarket getSuperMarket() {
        return superMarket;
    }

    public void LoadXMLFileAndCheckIt(String fullPath) {
        xmlUtilities = new XmlUtilities();
        xmlUtilities.isNameOfFileCorrect(fullPath);
        SuperDuperMarketDescriptor superMarketSDM = xmlUtilities.loadFile(fullPath);
        xmlUtilities.checkIfTheXmlThatJustLoadedOk(superMarketSDM);
        if (xmlUtilities.getIsXmlOk()) {
            superMarket = SuperMarket.creatInstance(superMarketSDM);
            thereIsXmlLoaded = true;
        }
    }


    public boolean isXmlLoaded() {
        return thereIsXmlLoaded;
    }

    public StringBuilder getinfoItem(Item item, List<Item.InfoOptions>list){
        StringBuilder itemInfo = new StringBuilder();
        for (Item.InfoOptions option : list){
            itemInfo.append(String.join(" ", option.toString().split("(?=[A-Z])"))).append(": ").append(option.getInfo(item)).append("\n");
        }

        return itemInfo;
    }

    public StringBuilder getInfoSell(Sell sell, List<Sell.InfoOptions>list){
        StringBuilder sellInfo = new StringBuilder();
        for (Sell.InfoOptions option : list) {
            sellInfo.append(String.join(" ", option.toString().split("(?=[A-Z])"))).append(": ").append(option.getInfo(sell)).append("\n");
        }
        return sellInfo;
    }

    public StringBuilder getStoreInfo(Store store , List<Store.InfoOptions>list){
        StringBuilder storeInfo = new StringBuilder();
        for (Store.InfoOptions option : list) {
            storeInfo.append(option.toString()).append(": ").append(option.getInfo(store)).append("\n");
        }
        return storeInfo;
    }

    public Order getEmptyOrder() {
        Order order = new Order();
        return order;
    }

    public boolean isValidStoreId(int storeID) {
        return superMarket.getStores().keySet().stream().filter(key -> key == storeID).count() == 1;
    }

    public void setStoreOfOrderByID(int storeID, Order emptyOrder) {
        emptyOrder.setStore(superMarket.getStores().get(storeID));
    }

    public String getPriceOfItemByStoreId(Item item, int finalStoreID) {
        Store store = superMarket.getStores().get(finalStoreID);
        Sell sellFound = (Sell)store.getItemsToSell().stream().filter(sell->sell.getItemId()==item.getId()).findAny().orElse(null);
        if(sellFound != null)
            return String.valueOf(sellFound.getPrice());
        else
            return "No Price (not for sale)";
    }

    public boolean isValidItemId(int itemId) {
        return superMarket.getItems().keySet().stream().anyMatch(key->itemId == key);
    }

    public Item.PurchaseCategory getPurchaseCategory(int itemId) {
        return superMarket.getItems().get(itemId).getPurchaseCategory();
    }

    public boolean checkIfStoreSellAnItem(int storeID, int itemId) {
        return true;
    }

    public void getOrderInfo(Order emptyOrder) {
    }

    public void commitOrder(Order emptyOrder) {
    }
}
