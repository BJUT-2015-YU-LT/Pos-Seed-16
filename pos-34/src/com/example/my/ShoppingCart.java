package com.example.my;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/10.
 */
public class ShoppingCart {
    public List<Item> shoppingCart = new ArrayList<Item>();
    public void add(Item item) {
        if (shoppingCart.size() == 0) {
            shoppingCart.add(item);
        } else if (shoppingCart.get(shoppingCart.size() - 1).getBarcode().equals(item.getBarcode())) {
            shoppingCart.get(shoppingCart.size() - 1).setNum(shoppingCart.get(shoppingCart.size() - 1).getNum() + 1);
        } else {
            shoppingCart.add(item);
        }
    }

}
