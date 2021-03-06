package com.example.my;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.List;

public class Index implements Serializable {
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private double discount;
    private double vipDiscount;
    private boolean promotion;

    public Index() {
        setDiscount(1);
        setVipDiscount(1);
    }


    public Index(String a[]) {
        setBarcode(a[0]);
        setName(a[1]);
        setUnit(a[2]);
        setPrice(Double.parseDouble(a[3]));
        if (a.length == 5) {
            if (a[4].equals("true")) {
                setPromotion(true);
                setDiscount(1);
            } else {
                setPromotion(false);
                setDiscount(Double.parseDouble(a[4]));
            }
        } else {
            setDiscount(1);
            setPromotion(false);
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public double getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(double vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public void read(List<Index> indexList) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Index.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            String data = null;
            do {
                data = bufferedReader.readLine();
                if (data != null) {
                    stringBuilder.append(data);
                }
            } while (data != null);
            JSONObject jObject = new JSONObject(stringBuilder.toString());
            Iterator keys = jObject.keys();
            while (keys.hasNext()) {
                Index index = new Index();
                String barcode = (String) keys.next();
                index.setBarcode(barcode);
                JSONObject jChildObject = jObject.getJSONObject(barcode);
                Iterator childKeys = jChildObject.keys();
                while (childKeys.hasNext()) {
                    String childKey = (String) childKeys.next();
                    if (childKey.equals("unit")) {
                        index.setUnit(jChildObject.get(childKey).toString());
                    } else if (childKey.equals("price")) {
                        index.setPrice(Double.parseDouble(jChildObject.get(childKey).toString()));
                    } else if (childKey.equals("name")) {
                        index.setName(jChildObject.get(childKey).toString());
                    } else if (childKey.equals("discount")) {
                        index.setDiscount(Double.parseDouble(jChildObject.get(childKey).toString()));
                    }else if (childKey.equals("promotion")) {
                        index.setPromotion(Boolean.parseBoolean(jChildObject.get(childKey).toString()));
                    }
                    else if (childKey.equals("vipDiscount")) {
                        index.setVipDiscount(Double.parseDouble(jChildObject.get(childKey).toString()));
                    }
                }
                indexList.add(index);
            }
            indexList.sort(new SortIndex());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class SortIndex implements Comparator {
    public int compare(Object o1, Object o2) {
        Index s1 = (Index) o1;
        Index s2 = (Index) o2;
        return s1.getBarcode().compareTo(s2.getBarcode());
    }
}
