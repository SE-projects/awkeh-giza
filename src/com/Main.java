package com;

import com.model.*;
import com.model.central.CentralStorageManager;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.Month;

//use enhanced for loop and print only those attributes with values
public class Main {
    public static void main(String[] args) {
//        CentralStorageManager abebe = new CentralStorageManager();

        /*abebe.addProductToCentralStorage("Soda Pop", "Beverage", 4.2, 250,
                "Pepsi", LocalDate.of(2020, Month.OCTOBER, 22), "16.9 Fl oz",
                19.99, 15);*/

        /*abebe.addProductToCentralStorage("Diet Coke Soda", "Beverage", 4.1, 150,
                "Coca Cola", LocalDate.of(2020, Month.NOVEMBER, 19), "2 liter bottle",
                80, 14);*/

       /* abebe.addProductToCentralStorage("test1", "Test", 4.5, 100, "exam",
                LocalDate.of(2020, Month.OCTOBER, 17), "3 tests", 12, 16);*/

//        abebe.removeProductFromCentralStorage("test1", "exam");

     /*   ObservableList<Product> productList = abebe.getProductsListInCentralStorage(3);
        productList.forEach(product -> System.out.println(product));*/

        /*Iterator<Product> iterator = productList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/


    /*   ObservableList<Product> products = abebe.searchProductInCentralStorage("soda");
        products.forEach(product -> System.out.println(product));*/

//       abebe.updateProductInCentralStorage("Coke Soda", "Coca Cola", 420);
       /* if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return;
        }

*/
      /*  Storage storage = new Storage();
        ObservableList<Product> productList = storage.getProductListInStorage("Storage1");
        productList.forEach(product -> System.out.println(product));*/
      /*  storage.addProductToStorage("Storage1", "Coke Soda", "Beverage", 4.5, 321,
                "Coca Cola", LocalDate.of(2020, Month.APRIL, 12), "16.9 Fl Oz", 21.99,
                12);*/
      /*  DataSource.getInstance().insertIntoStorage("Storage1", "Coke Soda", "Beverage",
                4.5, 321, "Coca Cola", LocalDate.of(2020, Month.APRIL, 12),
                "16.9 Fl Oz", 21.99, 12);

        DataSource.getInstance().close();*/

      /*  NewCustomer Kebe = new NewCustomer();
        Kebe.register("Tim", "@tims", "tim@gmail.com", "UPTOWN", 567,
                "Arada", "A.A");*/

     /*   NewCustomer Joel = new NewCustomer();
        Joel.register("Joel", "6758", "joel@gmail.com", "5 kilo", 342,
                "Arada", "A.A");*/

       /* NewCustomer Frazier = new NewCustomer();
        Frazier.register("Frazier", "5646", "frazier@gmail.com", "5 kilo", 324,
                "Arada", "A.A");*/

       /* RegisteredCustomer Frazier = new RegisteredCustomer();
        Frazier.deleteCustomer("Frazier", "5646");*/
    }
}
