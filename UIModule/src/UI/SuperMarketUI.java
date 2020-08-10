package UI;

import SDMModel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
            } catch (Exception Ex) {
                System.out.println(Ex.getMessage());
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
        HashMap<Integer, Store> stores = systemManager.superMarket.getStores();
        for(Store store:stores.values()) {
            System.out.println("#################################################");
            printStore(store);
            System.out.println("\n\n");
        }
    }

    private void showItems() {
        HashMap<Integer, Item> items = systemManager.superMarket.getItems();
        for(Item item:items.values()) {
            System.out.println("#################################################");
            printItem(item);
            System.out.println("\n\n");
        }
    }

    private void printStore(Store store) {
        System.out.println("Store ID: " + store.getId() );
        System.out.println("Store Name: " + store.getName() );
        System.out.println("Store Items: ");
        for(Sell sell:store.getItemsToSell()) {
            printSellOffer(sell);
        }
        //System.out.println("Store ID: " + store.getId() );
    }

    private void printSellOffer(Sell sell) {
        Item itemToShow = systemManager.superMarket.getItemByID(sell.getItemId());
        System.out.println("________");
        System.out.println("Item ID: " + itemToShow.getId() );
        System.out.println("Item Name: " + itemToShow.getName());
        System.out.println("Item sell catagory (weight / quantity): " + itemToShow.getPurchaseCategory() );
        System.out.println("Item price : " + sell.getPrice() );
        System.out.println("Amount of items sold by store : " + sell.getNumberOfTimesItemWasSold() );

    }

    private void printItem(Item item) {
        int numOfStoresSellTheItem = 0;
        System.out.println("________");
        System.out.println("Item ID: " + item.getId() );
        System.out.println("Item Name: " + item.getName());
        System.out.println("Item sell catagory (weight / quantity): " + item.getPurchaseCategory() );
        if(item.getStoresWhoSellTheItem()!=null)
            numOfStoresSellTheItem = item.getStoresWhoSellTheItem().size() ;

        System.out.println("Amount of stores selling the item : " + numOfStoresSellTheItem );
        System.out.println("Amound of time item was sold : " + item.getTotalNumberOfTimePurchased() );

    }


    private void createOrder() {

    }

    private void loadXMLFile() {
        System.out.println("Please enter full path of your XML file.");
        String fullPath = scanner.nextLine();
        systemManager.LoadXMLFileAndCheckIt(fullPath);
        if(systemManager.isXmlLoaded())
            System.out.println("Loadeded successfully");
        else
            System.out.println("Not loadeded successfully");
    }


}