# Super Duper Market - SDM 
<br>

## Code by:
Lior Fridman - 206081085, fridman.lior1@gmail.com
Dor Cohen - 307939959, doric93@gmail.com



## System Description

   The SDM is a service that provides the user to shop for certain items, based on the stores which are part of it.
   Currently the SDM information is taken from XML file provided by the user.
   Once the file was provided and parsed, the user can see all the items and stores in the SDM.
   Moreover, the user can decide to shop from one store or let the app to dynamically shop for him and choose the stores to shope from based on the items price.

  

## Notes

   - First bonus was implemented - dynamic shopping:
    When the user chooses to create a new order, we first ask him if he want us to shop dynamically for him (Yes/No question)
    in a dynamaic order the shipment price is total of all shipment price 
  
   - before the customer commits his order we show him a summary of the order.
    as of now, we don't show the order total price (as we didn't see such request in the word document)

   - before the customer commits his order we show him a summary of the order.
    as of now, we don't show the order total price (as we didn't see such request in the word document)

  
 
## Main classes:

System Manager
   class members:
   - superMarket: main object that contains all other classes
   - xmlUtilities: responsible for loading/parsing/validating the XML files

Super Market
  class members:
   - stores: hashmap that cotains all SDM stores
   - items: hashmap that cotains all SDM items
   - orders: hashmap that cotains all SDM orders
   
Store
   - ID, Name, Location, PPK, Total Earning: store attributes
   - itemsToSell: hashmap of items that the store Sell
   - orders: hashmap of orders specific to the store
  
Item
   - ID, Name, Purchase Catagory, totalNumberOfTimePurchased: item attributes
   - storesWhoSellTheItem: list of stores that sell the item
  
Sell
   Sell is an object coupled to a store, it represents the way the store uses the item she sells:
   - price, itemID, numberOfTimesItemWasSold
   
Order:
   - Order Number, Location Of Client, Date Of Order, Shipment Price, Delivery Distance, Item Price, Total Price: order attributes
   - storesToOrderFrom: hashmap with all the stores who are part of the order, if the order is a "static" order, the hashmap will contan one store
   - itemsQuantity: hashmap with the item + quantity that are part of the order
  
   
