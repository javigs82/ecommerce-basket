package me.javigs82.basket.domain;

//Events for vertx
public final class AddItemToBasketEvent {
    public final String basketCode;
    public final String itemCode;
    public AddItemToBasketEvent (String basketCode, String itemCode) {
        this.basketCode = basketCode;
        this.itemCode = itemCode;
    }
}
