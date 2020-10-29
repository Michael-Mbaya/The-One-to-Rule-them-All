package com.example.lotrcharacters.ui;

import java.util.ArrayList;
import java.util.Collections;

public class Toy {

    public static void main(String[] args) {
        String[] strArray = new String[]{"redShirt", "greenPants", "redShirt", "orangeShoes", "blackPants", "blackerPants"};
        counter(strArray);
    }

    public static void counter(String[] arry) {
        ArrayList<String> holder = new ArrayList();
        String count = "";
        for (int i = 0; i < arry.length; i++) {
            String p = arry[i];
            int counted = 0;
            for (int x = 0; x < arry.length; x++) {
                if (arry[x].equals(p)) {
                    holder.add(arry[x]);
                    counted++;
                }
                if (counted > 1) {
                    Collections.sort(holder);
                    count = holder.get(holder.size() - 1).toString();
                } else {
                    count = holder.get(0);
                }
            }
        }
        System.out.println("The most frequently sold item is " + count);

    }


}