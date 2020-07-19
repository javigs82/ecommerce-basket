package me.javigs82.domain;

//Inmutable
public final class Item {

    final String code;
    final String description;
    final double price;
    final double discount; //an object??, an percentage??

    public Item(String code, String description, double price, double discount) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

}

