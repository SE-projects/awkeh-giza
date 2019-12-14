package com;

import com.model.*;
import com.model.central.CentralStorageManager;
import com.model.central.CentralStorageQueries;
import com.model.customer.*;
import com.model.shop.StorageManager;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.Month;

//use enhanced for loop and print only those attributes with values
public class Main {
    public static void main(String[] args) {
       /* CentralStorageManager abebe = new CentralStorageManager();

        abebe.addProductToCentralStorage("Soda Pop", "Beverage", 4.2, 250,
                "Pepsi", LocalDate.of(2020, Month.OCTOBER, 22), "16.9 Fl oz",
                19.99, 15);*/

        /*abebe.addProductToCentralStorage("Diet Coke Soda", "Beverage", 4.1, 150,
                "Coca Cola", LocalDate.of(2020, Month.NOVEMBER, 19), "2 liter bottle",
                80, 14);*/

       /* abebe.addProductToCentralStorage("test1", "Test", 4.5, 100, "exam",
                LocalDate.of(2020, Month.OCTOBER, 17), "3 tests", 12, 16);*/

//        abebe.removeProductFromCentralStorage("test1", "exam");

//        CentralStorageManager csm = new CentralStorageManager();
        /*ObservableList<Product> productList = csm.getProductsListInCentralStorage(3);
        productList.forEach(product -> System.out.println(product));*/
       /* csm.addProductToCentralStorage(48593467,"Cod Liver Oil", "Food", 4.2, 90,
                "Norwiegn company", LocalDate.of(2020, Month.DECEMBER, 19), "Medium sized",
                300, 19);*/
//        csm.removeProductFromCentralStorage("Cod Liver Oil", "Norwiegn company");

        /*Iterator<Product> iterator = productList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

//        CentralStorageQueries csq = new CentralStorageQueries();
      /* ObservableList<Product> products = csm.searchProductInCentralStorage("soda");
        products.forEach(product -> System.out.println(product));

       csm.updateProductInCentralStorage("Coke Soda", "Coca Cola", 345);*/
//        csm.createCentralStorageTable();

       //TODO figure out how to better coordinate. When the closeConnection method should be called.
//       csq.closeConnection();
       /* if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return;
        }

*/
     /*   StorageManager sm = new StorageManager();
        ObservableList<Product> productList = sm.getStorageProductList("Storage1");
        productList.forEach(product -> System.out.println(product));*/
//        sm.createStorageTable("Storage3");
        /*Storage storage = new Storage();
        ObservableList<Product> productList = storage.getProductListInStorage("Storage1");
        productList.forEach(product -> System.out.println(product));*/

//        sm.removeProductFromStorage("Storage1", "Coke Soda", "Coca Cola");

       /* sm.addProductToStorage("Storage1", 47578698, "Coke Soda", "Beverage", 4.5,
              321, "Coca Cola", LocalDate.of(2020, Month.APRIL, 12), "16.9 Fl Oz",
              21.99, 17);*/

     /* ObservableList<Product> productList = sm.getStorageProductList("Storage1");
      productList.forEach(product -> System.out.println(product));*/
        /*if(!DataSource.getInstance().open()){
            System.out.println("Couldn't open DataSource");
            return;
        }
        DataSource.getInstance().insertIntoStorage("Storage1", "Coke Soda", "Beverage",
                4.5, 321, "Coca Cola", LocalDate.of(2020, Month.APRIL, 12),
                "16.9 Fl Oz", 21.99, 12);

        DataSource.getInstance().close();*/

      /*  NewCustomer Kebe = new NewCustomer();
        Kebe.register("Tim", "@tims", "tim@gmail.com", "UPTOWN", 567,
                "Arada", "A.A");*/

     /*   NewCustomer Joel = new NewCustomer();
        Joel.register("Joel", "6758", "joel@gmail.com", "5 kilo", 342,
                "Arada", "A.A");*/

       /* NewCustomer Mark = new NewCustomer();
        Mark.register( "Mark", "4323", "0934567845","mark@gmail.com",
                "Tafo", 234, "Yeka", "A.A");*/

      /*  RegisteredCustomer Frazier = new RegisteredCustomer();
        Frazier.deleteCustomer("0934567845", "Mark");*/

    /*    RegisteredCustomer Tim = new RegisteredCustomer();
        Tim.updateCustomerInfo("0", "@tims","Timmy", "6453",
                "0943564356", "timmy@gmail.com", "shoa", 758, "Bole", "A.A");*/

/*        CentralManager kebe = new CentralManager();
        ObservableList<RegisteredCustomerData> rcd = kebe.getFullCustomerInfo(3);
        rcd.forEach(customer -> System.out.println(customer));*/

        //TODO A whole new
        /*NewCustomer Kevin = new NewCustomer();
        Kevin.register("kevin", "Stuart", "kev123", "75hfg*th", "9787545",
                "kev@gmail.com", "Janmeda", 756, "Arada", "A.A");*/

        /*RegisteredCustomer Joel = new RegisteredCustomer();
        Joel.updateCustomerInfo("JoelZ", "hfgt@fg", "Joel", "fht34",
        "956445", "joel@gmail.com", "Megenagna", 546, "Yeka", "A.A");*/
        CustomerQueries cq = new CustomerQueries();
        if(!cq.establishConnection()){
            System.out.println("Couldn't open connection");
            return;
        }
//        cq.createACart("My Cart");
//        cq.addProductToCart(45633, "Coke Soda", 20, 5, 10, "My Cart");
//        cq.addProductToCart(645323, "Coke Soda", 20, 6, 120, "My Cart");
//        cq.addProductToCart(536475, "Tooth brush", 12, 5, 60, "Another Cart");
/*
        cq.removeProductFromCart(new CartProduct(45633, "Coke Soda", 20,
                5, 10, cq.createACart("My Cart")));*/
   /*     ObservableList<ProductInCart> products = cq.viewCartContents(2);
        products.forEach(product -> System.out.println(product));*/
     /*   cq.updateProductInCart(new CartProduct(645323, "Coke Soda", 20,
                10, 200, 1));*/
        /*boolean order = cq.orderCart(new Cart(1, "My Cart"));
        if(order){
            System.out.println("Order successful");
        }*/
       /* cq.updateProductInCart(new CartProduct(536475, "Tooth brush", 12, 10,
                120, 2));*/
        /*boolean order = cq.orderCart(new Cart(2, "Another Cart"));
        System.out.println(order);*/
        RegisteredCustomer Eyuel = new RegisteredCustomer();
        Eyuel.cancelOrder(new Order(2, LocalDate.now(), 2));
        cq.closeConnection();
    }
}
