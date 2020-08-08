package SDMModel;

import SDMGenerated.Location;
import SDMGenerated.SDMSell;
import SDMGenerated.SDMStore;

import java.awt.*;
import java.util.List;


public class Store {

    private String name;
    private int deliveryPpk;
    private Point location;
    private List<Sell> ItemsToSell;
    private int id;

    public static Store createInstanceBySDM(SDMStore sdmStore) {
        Store newStore = new Store();

        newStore.setId(sdmStore.getId());
        newStore.setDeliveryPpk(sdmStore.getDeliveryPpk());
        newStore.setName(sdmStore.getName());
        newStore.setLocation(new Point(sdmStore.getLocation().getX(),sdmStore.getLocation().getY()));
        List<SDMSell>itemsToSellSDM = sdmStore.getSDMPrices().getSDMSell();
        for(SDMSell sell : itemsToSellSDM){
            Sell newSell= Sell.createInstanceBySDM(sell);
            newStore.getItemsToSell().add(newSell);
        }
        return newStore;
    }

    public int getDeliveryPpk() {
        return deliveryPpk;
    }

    public void setDeliveryPpk(int deliveryPpk) {
        this.deliveryPpk = deliveryPpk;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<Sell> getItemsToSell() {
        return ItemsToSell;
    }

    public void setItemsToSell(List<Sell> itemsToSell) {
        ItemsToSell = itemsToSell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
