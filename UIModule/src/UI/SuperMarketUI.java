package UI;

import SDMModel.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class SuperMarketUI {

    private SystemManager systemManager = new SystemManager();
    private Scanner scanner = new Scanner(System.in);

    public enum eMainMenu {
        LoadFile,
        ShowStores,
        ShowAllItems,
        CreateAnOrder,
        ShowHistory,
        Exit
    }

    private  eMainMenu handleSelection(int length) {

        int userSelectionAsInt = 0;
        boolean validOption = false;
        do {
            userSelectionAsInt = IO.getInt();
            if (userSelectionAsInt <= length && userSelectionAsInt >= 1)
                validOption = true;
            else
                System.out.println("Input not in range");
        }
        while (!validOption);

        return eMainMenu.values()[userSelectionAsInt - 1];
    }

    public  void run() {
        eMainMenu userSelection;
        do {
            int i = 0;
            for (eMainMenu e : eMainMenu.values()) {
                i++;
                System.out.println(i + ". " + String.join(" ", e.toString().split("(?=[A-Z])")));
            }
            userSelection = handleSelection(eMainMenu.values().length);

            switch (userSelection) {
                case LoadFile:
                    loadXMLFile();
                    break;
                case ShowStores:
                    System.out.println("Stores:");
                    showStores();
                    break;
                case ShowAllItems:
                    System.out.println("Items");
                    showItems();
                    break;
                case CreateAnOrder:
                    createOrder();
                    break;
                case ShowHistory:
                    showHistory();
                    System.out.println("history");
                    break;

            }
        } while (!userSelection.equals(eMainMenu.Exit)) ;

    }


    private void showStores() {
        if(systemManager.isXmlLoaded()){
            HashMap<Integer, Store> stores = systemManager.getSuperMarket().getStores();
            for(Store store:stores.values()) {
                System.out.println("#################################################");
                printStoreAndSells(store);
                System.out.println("\n");
            }
        }
        else
            System.out.println("You should load an xml file");
    }

    private void showItems() {
        if(systemManager.isXmlLoaded()){
            HashMap<Integer, Item> items = systemManager.getSuperMarket().getItems();
            for(Item item:items.values()) {
                System.out.println("#################################################");
                printItem(item);
                System.out.println("\n");
            }
        }
        else
            System.out.println("You should load an xml file");
    }

    private void printStoreAndSells(Store store) {
        printStore(store);
        System.out.println("Store Items: ");
        for(Sell sell:store.getItemsToSell()) {
            printSellOffer(sell);
        }
        for(Order order:store.getOrders().values()) {
            List<Order.InfoOptions> list = new ArrayList<>();
            list.add(Order.InfoOptions.Date);
            list.add(Order.InfoOptions.AmountOfAllItems);
            list.add(Order.InfoOptions.ItemsPrice);
            list.add(Order.InfoOptions.ShipmentPrice);
            list.add(Order.InfoOptions.TotalPrice);
            System.out.println(systemManager.getinfoOrder(order, list));


        }

    }

    private void printStore(Store store) {
        List<Store.InfoOptions>list=new LinkedList<>();
        list.add(Store.InfoOptions.ID);
        list.add(Store.InfoOptions.Name);
        list.add(Store.InfoOptions.DeliveryPPK);
        list.add(Store.InfoOptions.TotalEarning);
        System.out.println(systemManager.getStoreInfo(store,list));
    }

    private void printSellOffer(Sell sell) {
        Item itemToSell = systemManager.getSuperMarket().getItemByID(sell.getItemId());
        List <Item.InfoOptions> itemAttributes = new ArrayList<Item.InfoOptions>();
        List <Sell.InfoOptions> sellAttributes = new ArrayList<>();
        itemAttributes.add(Item.InfoOptions.ItemId);
        itemAttributes.add(Item.InfoOptions.Name);
        itemAttributes.add(Item.InfoOptions.Category);
        StringBuilder SellInfo = systemManager.getinfoItem(itemToSell,itemAttributes);
        sellAttributes.add(Sell.InfoOptions.Price);
        sellAttributes.add(Sell.InfoOptions.TimesWasSold);
        System.out.println(SellInfo.append(systemManager.getInfoSell(sell, sellAttributes)));
    }

    private void printItem(Item item) {
        System.out.println("________");
        List <Item.InfoOptions>list = new ArrayList<Item.InfoOptions>();
        printItemIDNamePPK(item);
        list.add(Item.InfoOptions.NumberOfStoresSellTheItem);
        list.add(Item.InfoOptions.ItemAveragePrice);
        list.add(Item.InfoOptions.NumberOfTimesItemWasSold);
        System.out.println(systemManager.getinfoItem(item,list));

    }

    private void printItemIDNamePPK(Item item) {
        List<Item.InfoOptions> list = new ArrayList<Item.InfoOptions>();
        list.add(Item.InfoOptions.ItemId);
        list.add(Item.InfoOptions.Name);
        list.add(Item.InfoOptions.Category);
        System.out.print(systemManager.getinfoItem(item, list));
    }

    private void loadXMLFile() {
        System.out.println("Please enter full path of your XML file.");
        String fullPath = scanner.nextLine();
        fullPath = "C:\\Users\\Lior\\IdeaProjects\\ex1-small.xml";
        //fullPath = "/Users/dor.cohen/Downloads/ex1-big.xml";
        systemManager.LoadXMLFileAndCheckIt(fullPath);
        if(systemManager.getXmlUtilities().getIsXmlOk())
            System.out.println("Loadeded successfully");
        else{
            System.out.println("Not loadeded successfully \n");
            System.out.println(systemManager.getXmlUtilities().getWhatWrongMessage());
        }
    }

    private void createOrder() {
        if(systemManager.isXmlLoaded()) {
            Order emptyOrder = systemManager.getEmptyOrder();
            systemManager.getSuperMarket().getStores().forEach((id, store) -> printStore(store));
            System.out.println("Please Choose a store (by ID)");
            int storeID = IO.getInt();
            while (!systemManager.isValidStoreId(storeID)) {
                System.out.println("Unknown ID, try again (by ID)");
                storeID = IO.getInt();
            }
            systemManager.setStoreOfOrderByID(storeID, emptyOrder);
            System.out.println("Please enter delivery date (dd/mm-hh:mm format)");
            boolean fixdate = false;
            while (fixdate == false) {
                String date1 = scanner.nextLine();
                if(date1.length()==11) {
                    try {
                        Date date = new SimpleDateFormat("dd/MM-hh:mm").parse(date1);
                        date.setYear(120);
                        fixdate = true;
                        emptyOrder.setDateOfOrder(date);
                    } catch (Exception e) {
                        System.out.println("The date that selected not fix, please select fix date");
                        fixdate = false;
                    }
                }
                else
                    System.out.println("The date that selected not fix, please select fix date");

            }

            boolean isfixedLocation = false;
            do {
                System.out.println("Please enter a location");
                System.out.print("X: ");
                Integer xCordinate = IO.getInt();
                System.out.print("Y: ");
                Integer yCordinate = IO.getInt();
                try {
                    systemManager.isfixedLocationAndSetToOrder(new Point(xCordinate, yCordinate), emptyOrder);
                    isfixedLocation = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (!isfixedLocation);

            int finalStoreID = storeID;
            systemManager.getSuperMarket().getItems().forEach((id, item) -> {
                printItemIDNamePPK(item);
                String itemPrice = systemManager.getPriceOfItemByStoreId(item, finalStoreID);
                System.out.println("Item price: " + itemPrice + "\n");
            });
            boolean isContinue = true;
            while (isContinue) {
                System.out.println("Please choose item by put ID");
                int itemId = IO.getInt();
                while (!systemManager.isValidItemId(itemId)) {
                    System.out.println("Unknown item ID, try again (by ID)");
                    itemId = IO.getInt();
                }
                Item.PurchaseCategory category = systemManager.getPurchaseCategory(itemId);

                double quantity;
                if (systemManager.checkIfStoreSellAnItem(storeID, itemId)) {
                    System.out.println("Please Enter QUANTITY (" + category + ")");
                    if (category.equals(Item.PurchaseCategory.QUANTITY))
                        quantity = IO.getInt();
                    else
                        quantity = IO.getDouble();
                    systemManager.addAnItemToOrder(emptyOrder, storeID, itemId, quantity);
                    System.out.println("Item was added to order");
                } else
                    System.out.println("this item is not an option");
                System.out.println("Press Q if you dont want another item, or any key to continue");
                String userWantToContinue = scanner.nextLine();
                if (userWantToContinue.equals("q") || userWantToContinue.equals("Q"))
                    isContinue = false;
            }

            getOrderInfo(emptyOrder);
            System.out.println("Press Y if you to commit the order");
            String userWantToCommit = scanner.nextLine();
            if (userWantToCommit.equals("y") || userWantToCommit.equals("Y"))
                systemManager.commitOrder(emptyOrder);
        }
        else
            System.out.println("You should load an xml file");
    }

    public void getOrderInfo(Order order){
        List <Item.InfoOptions> itemAttributes = new ArrayList<>();
        itemAttributes.add(Item.InfoOptions.ItemId);
        itemAttributes.add(Item.InfoOptions.Name);
        itemAttributes.add(Item.InfoOptions.Category);
        Integer itemID;
        double itemQuantity;
        double itemPrice;
        for (Item item: order.getItemsToOrder().values()){
            itemID = item.getId();
            System.out.print(systemManager.getinfoItem(item, itemAttributes));
            itemPrice = order.getStoreToOrderFrom().getItemPrice(itemID);
            itemQuantity = order.getItemsQuantity().get(itemID);
            System.out.println("Item price: " + itemPrice);
            System.out.println("Item quantity: " + itemQuantity);
            System.out.println("Item total price: " + String.valueOf(((itemPrice * itemQuantity)* 1000d) / 1000d) +"\n");
        }
        printDistanceAndPPK(order);
    }

    private void printDistanceAndPPK(Order order) {
        systemManager.calculateDistance(order);
        System.out.println("\n\nDistance from store: " + order.getDeliveryDistance());
        System.out.println("PPK of store: " + order.getStoreToOrderFrom().getDeliveryPpk());
        System.out.println("Shipment total price: " + (double)Math.round(  order.getShipmentPrice() * 1000d) / 1000d);

    }



    private void showHistory() {
        if(systemManager.isXmlLoaded()){
            HashMap<Integer,Order> allOrders =  systemManager.getSuperMarket().getOrders();

            for(Order order: allOrders.values()) {
                System.out.println("________");
                List<Order.InfoOptions> list = new ArrayList<>();
                list.add(Order.InfoOptions.OrderId);
                list.add(Order.InfoOptions.Date);
                list.add(Order.InfoOptions.StoreNameAndId);
                list.add(Order.InfoOptions.AmountOfKindsOfItems);
                list.add(Order.InfoOptions.AmountOfAllItems);
                list.add(Order.InfoOptions.ItemsPrice);
                list.add(Order.InfoOptions.ShipmentPrice);
                list.add(Order.InfoOptions.TotalPrice);
                System.out.println(systemManager.getinfoOrder(order, list));

            }


        }
        else
            System.out.println("You should load an xml file");

    }



}


