package UI;

import SDMModel.SuperMarket;
import SDMModel.SystemManager;
import SDMModel.Store;
import SDMModel.Item;

import java.util.HashMap;
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

        System.out.println("#################################################");

        System.out.println("#################################################");
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