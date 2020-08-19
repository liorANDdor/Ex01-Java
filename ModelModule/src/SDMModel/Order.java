package SDMModel;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class Order {


    public enum InfoOptions {
        OrderId, Date, StoreNameAndId, ItemsPrice, ShipmentPrice, TotalPrice, DeliveryDistance;


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
                    return String.valueOf(order.getItemsPrice() + order.getShipmentPrice());
                case DeliveryDistance:
                    return String.valueOf(order.getDeliveryDistance());
                default:
                    return "Unknown";
            }
        }
    }

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    private Double totalPrice;
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

    private Integer orderNumber;
    private Store storeToOrderFrom;
    private HashMap<Integer ,Item> itemsToOrder = new  HashMap<Integer ,Item>();
    private Point locationOfClient;
    private HashMap<Integer , Double> itemsQuantity = new  HashMap<Integer ,Double>();



    private Date dateOfOrder;

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public void setStore(Store store) {
        storeToOrderFrom = store;
    }

    public java.util.Date getDateOfOrder() {
        return dateOfOrder;
    }

    public HashMap<Integer, Item> getGetItemsToOrder() {
        return itemsToOrder;
    }
    public Store getStoreToOrderFrom() {
        return storeToOrderFrom;
    }

    public HashMap<Integer, Double> getItemsQuantity() {
        return itemsQuantity;
    }

    public Point getLocationOfClient() {
        return locationOfClient;
    }

    public boolean setLocationOfClient(Point locationOfClient) {
        if(locationOfClient.x>50 || locationOfClient.x < 0 || locationOfClient.y>50 || locationOfClient.y < 0 )
            return false;
        else {
            this.locationOfClient = locationOfClient;
            return true;
        }

    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }


}
