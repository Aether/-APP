package com.example.shoppingcart;

import java.util.ArrayList;
import java.util.Random;

import cn.bmob.v3.BmobObject;

public class Goods extends BmobObject {
    public int id = 1;
    public int typeId = 1;
    public int rating = 0;
    public String name;
    public String typeName = " ";
    public double price;
    public int count = 0;
    public static ArrayList<Goods> goodsList = new ArrayList<>();
    private static ArrayList<Goods> typeList = new ArrayList<>();


    public Goods(){

    }

    public Goods(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5)+1;
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private static void initData(){
        Goods item = null;
        for(int i=1;i<15;i++){
            for(int j=1;j<10;j++){
                item = new Goods(100*i+j,Math.random()*100,"商品"+(100*i+j),i,"种类"+i);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public static ArrayList<Goods> getGoodsList(){
        if(goodsList==null){
            initData();
        }
        return goodsList;
    }
    public static ArrayList<Goods> getTypeList(){
        if(typeList==null){
            initData();
        }
        return typeList;
    }
}
