package org.jing1578.basicapplication.model;

import com.google.gson.Gson;

/**
 * Created by jing1578 on 2016/12/22.
 */

public class Category {
    private int id;
    private  String category_name;
    private String  category_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }


    public static Category convertJsonObjectToCategory(String objectStr){
        Gson gson=new Gson();
       return  gson.fromJson(objectStr,Category.class);
    }
}
