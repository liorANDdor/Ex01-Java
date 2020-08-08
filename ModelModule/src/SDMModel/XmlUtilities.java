package SDMModel;

import SDMGenerated.SDMItem;
import SDMGenerated.SDMStore;
import SDMGenerated.SuperDuperMarketDescriptor;
import com.sun.jmx.remote.internal.Unmarshal;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlUtilities {

    public String whatWrongMessage;
    public boolean isXmlOk = true;

    public SuperDuperMarketDescriptor loadFile(String fullPath) {
        SuperDuperMarketDescriptor instance = null;
        if (isXmlOk) {

            try {
                File file = new File(fullPath);
                JAXBContext jaxbContext = JAXBContext.newInstance(SuperDuperMarketDescriptor.class);
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                instance = (SuperDuperMarketDescriptor) jaxbUnMarshaller.unmarshal(file);
            } catch (JAXBException e) {
                isXmlOk= false;
                whatWrongMessage = "Unknown file";
            } catch (Exception e) {
                isXmlOk = false;
                whatWrongMessage = "Unknown file";
            }
        }
        return instance;
    }

    public void checkIfTheXmlThatJustLoadedOk(SuperDuperMarketDescriptor superMarketSDM) {
        boolean isContentAsNeeded = true;
        if (isXmlOk) {
            List<SDMStore> stores = superMarketSDM.getSDMStores().getSDMStore();
            for (int i = 0; i < stores.size(); i++)
                for (int j = 1; j < stores.size(); j++)
                    if (stores.get(i).getId() == stores.get(j).getId()){
                        isContentAsNeeded = false;
                        whatWrongMessage = "There is two stores with the same ID \n";
                    }
            List<SDMItem>items= superMarketSDM.getSDMItems().getSDMItem();
            for (int i = 0; i < items.size(); i++)
                for (int j = 1; j < items.size(); j++)
                    if (items.get(i).getId() == items.get(j).getId()){
                        isContentAsNeeded = false;
                        whatWrongMessage = "There is two items with the same ID \n";
                    }



                        
                    
        }
    }

    public void isNameOfFileCorrect(String fullPath) {

        if(!(fullPath.split("\\.")[fullPath.split("\\.").length - 1].equals("xml"))){
            whatWrongMessage = "Your file should end with .xml";
            isXmlOk = false;
        }
    }

}
