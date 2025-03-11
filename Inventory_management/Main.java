package Inventory_management;

import Inventory_management.store.Store;

public class Main {

    //this is the main class for all store operations to start
    public static void main(String[] args) {
        Store store = new Store();
        store.showMenu();
    }
}
