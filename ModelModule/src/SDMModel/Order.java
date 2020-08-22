package SDMModel;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class Order {


    public enum InfoOptions {
        OrderId, Date, StoreNameAndId, ItemsPrice, ShipmentPrice, TotalPrice, DeliveryDistance, AmountOfKindsOfItems, AmountOfAllItems;


        public String getInfo(Order order) {

            switch (this) {
                case Date:
                    return order.getDateOfOrder().toString();
                case OrderId:
                    return String.valueOf(order.getOrderNumber());
                case StoreNameAndId:
                    return order.getStoreToOrderFrom().getName() +", " +String.valueOf(order.getStoreToOrderFrom().getId());
                case ShipmentPrice:
                    return String.valueOf(order.getShipmentPrice());
                case ItemsPrice:
                    return String.valueOf(order.getItemsPrice());
                case TotalPrice:
                    return String.valueOf((order.getItemsPrice() + order.getShipmentPrice()));
                case DeliveryDistance:
                    return String.valueOf(order.getDeliveryDistance());
                case AmountOfKindsOfItems:
                    return String.valueOf(order.getAmountOfKindsOfItems());
                case AmountOfAllItems:
                    return String.valueOf(order.getAmountOfAllItems());
                default:
                    return "Unknown";
            }
        }
    }

    private double getAmountOfAllItems() {
        return itemsQuantity.values().stream().mapToDouble(i->i).sum();
    }

    private int getAmountOfKindsOfItems() {
        return itemsQuantity.keySet().size();
    }

    private Integer orderNumber;
    private Store storeToOrderFrom;
    //private HashMap<Integer ,Item> itemsToOrder = new  HashMap<Integer ,Item>();
    private Point locationOfClient;
    private HashMap<Item , Double> itemsQuantity = new  HashMap<Item ,Double>();
    private Date dateOfOrder;
    private Double totalPrice;

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    private Double itemsPrice = 0.0;

    public double getDeliveryDistance() {
        return (double)Math.round( deliveryDistance * 1000d) / 1000d;
    }

    private double deliveryDistance;
    public void increaseOrderTotalPrice(double itemPrice) {
        this.itemsPrice=this.itemsPrice+itemPrice;
    }

    public Double getItemsPrice() {
        return itemsPrice;
    }

    private Double shipmentPrice;

    public Double getShipmentPrice() {
        return (shipmentPrice * 1000d) / 1000d;
    }
    public void setShipmentPrice(Double price) {
        shipmentPrice = price;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public void setStore(Store store) {
        storeToOrderFrom = store;
    }

    public java.util.Date getDateOfOrder() {
        return dateOfOrder;
    }

//    public HashMap<Integer, Item> getItemsToOrder() {
//        return itemsToOrder;
//    }
    public Store getStoreToOrderFrom() {
        return storeToOrderFrom;
    }

    public HashMap<Item, Double> getItemsQuantity() {
        return itemsQuantity;
    }

    public Point getLocationOfClient() {
        return locationOfClient;
    }

    public void setLocationOfClient(Point locationOfClient) {
        this.locationOfClient = locationOfClient;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }


}
