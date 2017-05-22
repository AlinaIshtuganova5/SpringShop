package com.itis.shop.business.exceptions;

/**
 * Created on 18.05.17.
 */
public class NotEnoughtItemsException extends RuntimeException {

    private String view;
    private Long itemId;

    public NotEnoughtItemsException(String view, Long itemId) {
        this.view = view;
        this.itemId = itemId;
    }

    public Long getItemId() {

        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public NotEnoughtItemsException(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String getMessage() {
        return "not enought items(((";
    }


}
