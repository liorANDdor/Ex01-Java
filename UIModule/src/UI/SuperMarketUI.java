package UI;

import SDMModel.*;

import java.util.*;
import java.util.stream.Collectors;

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
        String userSelectionAsString;
        Integer userSelectionAsInt = 0;
        boolean validOption = false;
        do {
            userSelectionAsString = scanner.nextLine();
            try {
                userSelectionAsInt = Integer.parseInt(userSelectionAsString);
                if (userSelectionAsInt <= length && userSelectionAsInt >= 1)
                    validOption = true;
                else
                    System.out.println("Input not in range");
            } catch (Exception Ex) {
                System.out.println("Input should be a number!");
                validOption = false;
            }

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
    }

    private void printStore(Store store) {
        List<Store.InfoOptions>list=new LinkedList<>();
        list.add(Store.InfoOptions.ID);
        list.add(Store.InfoOptions.Name);
        list.add(Store.InfoOptions.DeliveryPPK);
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
        list.add(Item.InfoOptions.ItemId);
        list.add(Item.InfoOptions.Name);
        list.add(Item.InfoOptions.Category);
        list.add(Item.InfoOptions.NumberOfStoresSellTheItem);
        list.add(Item.InfoOptions.ItemAveragePrice);
        list.add(Item.InfoOptions.NumberOfTimesItemWasSold);
        System.out.println(systemManager.getinfoItem(item,list));

    }

    private void loadXMLFile() {
        System.out.println("Please enter full path of your XML file.");
        String fullPath = scanner.nextLine();
        fullPath = "C:\\Users\\Lior\\IdeaProjects\\ex1-small.xml";
        systemManager.LoadXMLFileAndCheckIt(fullPath);
        if(systemManager.getXmlUtilities().getIsXmlOk())
            System.out.println("Loadeded successfully");
        else{
            System.out.println("Not loadeded successfully \n");
            System.out.println(systemManager.getXmlUtilities().getWhatWrongMessage());
        }
    }

    private int getInt(){
        while (!scanner.hasNextInt()) { // <-- 'peeks' at, doesn't remove, the next token
            System.out.println("Please enter a number (INT)!");
            scanner.next(); // <-- skips over an invalid token
        }

        return scanner.nextInt();
    }

    private double getDouble(){
        while (!scanner.hasNextDouble()) { // <-- 'peeks' at, doesn't remove, the next token
            System.out.println("Please enter a number!");
            scanner.next(); // <-- skips over an invalid token
        }

        return scanner.nextDouble();
    }


    private void createOrder() {
        Order emptyOrder = systemManager.getEmptyOrder();
        systemManager.getSuperMarket().getStores().forEach((id, store) -> printStore(store));
        System.out.println("Please Choose a store (by ID)");
        int storeID = getInt();
        while (!systemManager.isValidStoreId(storeID)){
            System.out.println("Unknown ID, try again (by ID)");
            storeID = getInt();
        }
        systemManager.setStoreOfOrderByID(storeID,emptyOrder);
        System.out.println("Please enter a date");
        scanner.nextLine();
        System.out.println("Please enter a location");
        scanner.nextLine();
        int finalStoreID = storeID;
        systemManager.getSuperMarket().getItems().forEach((id, item) -> {
            List<Item.InfoOptions> list = new ArrayList<Item.InfoOptions>();
            list.add(Item.InfoOptions.ItemId);
            list.add(Item.InfoOptions.Name);
            list.add(Item.InfoOptions.Category);
            System.out.println(systemManager.getinfoItem(item, list));
            String itemPrice = systemManager.getPriceOfItemByStoreId(item, finalStoreID);
            System.out.println("Item price: " + itemPrice);
        });
        boolean isContinue =true;
        while (isContinue){
            System.out.println("Please choose item by put ID");
            int itemId = getInt();
            while (!systemManager.isValidItemId(itemId)){
                System.out.println("Unknown item ID, try again (by ID)");
                itemId = getInt();
            }
            Item.PurchaseCategory category =systemManager.getPurchaseCategory(itemId);
            System.out.println("Please Enter QUANTITY (" + category + ")");
            double quantity;
            if(category.equals(Item.PurchaseCategory.QUANTITY))
                quantity=getInt();
            else
                quantity=getDouble();
            if(systemManager.checkIfStoreSellAnItem(storeID,itemId)){
                emptyOrder.addAnItem(itemId,quantity);
                System.out.println("Item was added to order");
            }
            else
                System.out.println("this item is not an option");
            System.out.println("Press Q if you dont want another item, or any key to continue");
            String userWantToContinue = scanner.nextLine();
            if(userWantToContinue.equals("q")||userWantToContinue.equals("Q"))
                isContinue = false;
        }
        systemManager.getOrderInfo(emptyOrder);
        System.out.println("Press Y if you to commit the order");
        String userWantToCommit = scanner.nextLine();
        if(userWantToCommit.equals("y")||userWantToCommit.equals("Y"))
            systemManager.commitOrder(emptyOrder);

    }




}