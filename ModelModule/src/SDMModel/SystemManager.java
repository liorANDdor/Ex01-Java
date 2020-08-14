package SDMModel;

import SDMGenerated.SuperDuperMarketDescriptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SystemManager {
    public SuperMarket getSuperMarket() {
        return superMarket;
    }

    private SuperMarket superMarket;
    private SuperDuperMarketDescriptor superMarketSDM;

    public XmlUtilities getXmlUtilities() {
        return xmlUtilities;
    }

    private XmlUtilities xmlUtilities;
    private boolean thereIsXmlLoaded = false;

    public void LoadXMLFileAndCheckIt(String fullPath) {
        xmlUtilities = new XmlUtilities();
        xmlUtilities.isNameOfFileCorrect(fullPath);
        superMarketSDM = xmlUtilities.loadFile(fullPath);
        xmlUtilities.checkIfTheXmlThatJustLoadedOk(superMarketSDM);
        if (xmlUtilities.getIsXmlOk()) {
            superMarket = SuperMarket.creatInstance(superMarketSDM);
            thereIsXmlLoaded = true;
        }
    }

    public List<String>getAllItems(){
        List<String> str= new LinkedList<>();
        for(Map.Entry<Integer, Item>  inte : superMarket.getItems().entrySet()){
            str.add(inte.getValue().getName()+inte.getValue().getId());
        }
        return str;
    }

    public boolean isXmlLoaded() {
        return thereIsXmlLoaded;
    }

    public StringBuilder getItemInfo(Item item) {
        int numOfStoresSellTheItem = 0;
        double itemAveragePrice = 0;
        StringBuilder itemInfo = new StringBuilder();
        itemInfo.append("Item ID: ").append(item.getId()).append("\n");
        itemInfo.append("Item Name: ").append(item.getName()).append("\n");
        itemInfo.append("Item sell catagory (weight / quantity): ").append(item.getPurchaseCategory().toString() ).append("\n");

        if(item.getStoresWhoSellTheItem()!=null) {
            numOfStoresSellTheItem = item.getStoresWhoSellTheItem().size();
            itemAveragePrice = getSuperMarket().getItemAveragePriceByID(item.getId());
        }
        itemInfo.append("Amount of stores selling the item: ").append(numOfStoresSellTheItem).append("\n");

        if(itemAveragePrice == 0)
            itemInfo.append("Item average price: Item is currently unavailable \n");
        else
            itemInfo.append("Item average price: ").append(itemAveragePrice).append("\n");

        itemInfo.append("Number of times item was sold: ").append(item.getTotalNumberOfTimePurchased() ).append("\n");

        return itemInfo;
    }

    public StringBuilder getSellInfo(Sell sell){
        Item itemToSell = getSuperMarket().getItemByID(sell.getItemId());
        StringBuilder itemInfo = new StringBuilder();
        itemInfo.append("Item ID: ").append(itemToSell.getId()).append("\n");
        itemInfo.append("Item Name: ").append(itemToSell.getName()).append("\n");
        itemInfo.append("Item Price: ").append(sell.getPrice()).append("\n");
        itemInfo.append("Amount of items sold by store: ").append(sell.getNumberOfTimesItemWasSold()).append("\n");

        return itemInfo;
    }

    public StringBuilder getStoreInfo(Store store){
        StringBuilder storeInfo = new StringBuilder();
        storeInfo.append("Store ID: ").append(store.getId()).append("\n");
        storeInfo.append("Store Name: ").append(store.getName()).append("\n");

        return storeInfo;


    }
}
