package SDMModel;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class Order {
    private Store storeToOrderFrom;
    private HashMap<Integer ,Item> itemsToOrder = new  HashMap<Integer ,Item>();
    private Date dateOfOrder;
    private Point locationOfClient;

    public void setStore(Store store) {
        storeToOrderFrom = store;
    }

    public void addAnItem(int itemId, double quantity) {
    }
}
