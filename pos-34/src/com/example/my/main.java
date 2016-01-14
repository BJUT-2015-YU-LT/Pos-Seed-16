package com.example.my;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Administrator on 2016/1/5.
 */

public class main {
    public static void main(String[] args) {
        List<Index> indexList = new ArrayList<Index>();
        List<Item> promotion = new ArrayList<Item>();
        ShoppingCart shoppingCart = new ShoppingCart();
        Index index = new Index();
        ProductList product = new ProductList();
        index.read(indexList);
        product.read();

        int i = 0;

        for (String barcode : product.getBarcode()) {
            if (barcode.equals(indexList.get(i).getBarcode())) {
                Item item = new Item(indexList.get(i));
                shoppingCart.add(item);
                //break;
            } else {
                while (++i < indexList.size()) {
                    if (barcode.equals(indexList.get(i).getBarcode())) {
                        Item item = new Item(indexList.get(i));
                        shoppingCart.add(item);
                        break;
                    }
                }
                if (i >= indexList.size()) {
                    System.out.println("Barcode not found.");
                }
            }
        }
        double sum = 0;
        double discount = 0;


        System.out.println("***商店购物清单***\n");
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月DD日 kk:mm:ss");
        System.out.println("打印时间:" + f.format(date));
        System.out.println("--------------------");


        for (Item a : shoppingCart.shoppingCart) {
            System.out.println("名称 ：" + a.getName() + " ， 数量 ：" + a.getNum() + a.getUnit() + " ， 单价 ：" + String.format("%.2f", a.getPrice()) + "(元) ， 小计 ：" + String.format("%.2f", a.getTotal()) + "(元)");

            if (a.isPromotion() && a.getNum() >= 2) {
                promotion.add(a);
            }
            sum += a.getTotal();
            discount += a.getTotalDiscount();
        }
        System.out.println("--------------------");
        if (!promotion.isEmpty()) {
            System.out.println("赠送商品：");
            for (Item a : promotion) {
                System.out.println("名称 ：" + a.getName() + " ， 数量 ：" + "1" + a.getUnit());
            }
        }
        System.out.println("--------------------\n");
        System.out.println("总计 ：" + String.format("%.2f", sum) + "(元)");
        System.out.println("节省 ：" + String.format("%.2f", discount) + "(元)");
        System.out.println("*********************\n");
    }



    public static List<Item> getShoppingCart(String inputStr) {
        List<Item> shoppingCart = new ArrayList<Item>();
        for (String a : inputStr.replaceAll("\\{|\\[|\\]| |　|\t|\n|'|barcode|name|unit|price|discount|:|：", "").split("]，|},|}")) {
            shoppingCart.add(new Item(a.split(",|，")));
        }
        return shoppingCart;
    }
}

