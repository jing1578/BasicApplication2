package org.jing1578.basicapplication.utils;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * a.{code:"",data:[],msg:""}
 * b.{code:"",data:{},msg:""}
 */
public class JsonUtil {

    public  static String judgeJson(String json) {
        boolean jsonStatement =!json.startsWith("{") && !json.startsWith("[");
        if (TextUtils.isEmpty(json) || jsonStatement) {
            return "0";
        }
        return "1";
    }

}


