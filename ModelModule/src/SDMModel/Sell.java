package SDMModel;


import SDMGenerated.SDMSell;

public class Sell {

    private int price;
    private int itemId;

    public static Sell createInstanceBySDM(SDMSell sell) {
        Sell newSell= new Sell();
        newSell.setItemId(sell.getItemId());
        newSell.setPrice(sell.getPrice());
        return newSell;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
