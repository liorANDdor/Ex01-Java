package SDMModel;

import SDMGenerated.SuperDuperMarketDescriptor;

public class SystemManager {
    private SuperMarket superMarket;
    private SuperDuperMarketDescriptor superMarketSDM;
    private XmlUtilities xmlUtilities;
    private boolean isXmlLoaded = false;

    public void LoadXMLFileAndCheckIt(String fullPath) {
        xmlUtilities = new XmlUtilities();
        xmlUtilities.isNameOfFileCorrect(fullPath);
        superMarketSDM = xmlUtilities.loadFile(fullPath);
        xmlUtilities.checkIfTheXmlThatJustLoadedOk(superMarketSDM);
        if (xmlUtilities.isXmlOk) {
            superMarket = SuperMarket.creatInstance(superMarketSDM);
            isXmlLoaded = true;
        }
    }

    public boolean isXMLFileLoaded() {
        return isXmlLoaded;
    }
}
