package me.javigs82.domain;

//Inmutable
public final class Item {

    final String code;
    final String description;
    final double price;
    //Discount should be an object
    final byte minNumberForDiscount;
    final double discount;

    public Item(String code, String description, double price, double discount) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.minNumberForDiscount = Byte.MAX_VALUE;
        this.discount = discount;
    }

}

