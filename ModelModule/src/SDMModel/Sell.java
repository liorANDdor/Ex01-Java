package SDMModel;


import SDMGenerated.SDMSell;

public class Sell {

    public enum InfoOptions {
        Price, ID, TimesWasSold;

        public String getInfo(Sell sell) {
            switch (this) {
                case ID:
                    return String.valueOf(sell.getItemId());
                case Price:
                    return String.valueOf(sell.getPrice());
                case TimesWasSold:
                    return String.valueOf(sell.getNumberOfTimesItemWasSold());
                default:
                    return "Unknown";
            }
        }
    }

    private double price;
    private int itemId;
    private int numberOfTimesItemWasSold;


    public static Sell createInstanceBySDM(SDMSell sell) {
        Sell newSell= new Sell();
        newSell.setItemId(sell.getItemId());
        newSell.setPrice(sell.getPrice());
        return newSell;
    }

    public double getPrice() {
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

    public int getNumberOfTimesItemWasSold() {
        return numberOfTimesItemWasSold;
    }

    public void increaseNumberOfTimesItemWasSold(int itemId) {
        this.numberOfTimesItemWasSold=numberOfTimesItemWasSold+1;
    }

}
