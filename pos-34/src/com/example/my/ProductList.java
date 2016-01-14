package com.example.my;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/10.
 */
public class ProductList implements Serializable {
    private List<String> barcode;
    private String userName;

    public ProductList() {
        barcode = new ArrayList<String>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getBarcode() {
        return barcode;
    }

    public void setBarcode(List<String> barcode) {
        this.barcode = barcode;
    }


    public void read() {
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("product3.txt"));
           BufferedReader bufferedReader = new BufferedReader(new FileReader("product4.txt"));
            String data;
            data = bufferedReader.readLine();
            while((data= bufferedReader.readLine())!=null && !data.equals("]")){
                this.barcode.add(data.substring(5, 15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


