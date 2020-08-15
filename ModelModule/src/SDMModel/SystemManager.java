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
}
