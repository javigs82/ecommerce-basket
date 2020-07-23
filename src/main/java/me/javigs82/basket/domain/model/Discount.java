package me.javigs82.basket.domain.model;

public class Discount {

    private final String code;
    private final String itemCode;
    //amount of items to apply the discount
    private final short amount;
    //percentage to apply
    private final byte percentage;

    public Discount(String code, String itemCode, short amount, byte percentage) {
        this.code = code;
        this.itemCode = itemCode;
        this.amount = amount;
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }

    public String getItemCode() {
        return itemCode;
    }

    public short getAmount() {
        return amount;
    }

    public byte getPercentage() {
        return percentage;
    }

}
