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

}
