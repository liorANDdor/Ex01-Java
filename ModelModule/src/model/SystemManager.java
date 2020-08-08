package model;

import SDMGenerated.SuperDuperMarketDescriptor;

public class SystemManager {
    //private SuperMarket;
    private SuperDuperMarketDescriptor superMarketSDM;
    private XmlUtilities xmlUtilities = new XmlUtilities();
    private boolean isXmlLoaded = false;

    public void LoadXMLFileAndCheckIt(String fullPath) {
        superMarketSDM = xmlUtilities.loadFile(fullPath);
        boolean checkIfCurrentLoadIsOk;
    }

    public boolean isXMLFileLoaded() {
        return true;
    }
}
