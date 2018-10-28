package com.Potoki;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MyThread extends Thread {

    private String text;
    private String inL;
    private String outL;

    public MyThread(String text, String inL, String outL) throws UnsupportedEncodingException {
        this.text = URLEncoder.encode(text, "UTF-8");
        this.inL = inL;
        this.outL = outL;
    }
    public boolean getLangs() throws MalformedURLException {
        URL url = null;
        List<String> list = new ArrayList<String>();
        url = new URL("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=trnsl.1.1.20181020T133944Z.8cf9228f391b4f39.44721d2628db09ecf4f35163d0987af6b164b470&ui=" + inL);
        Scanner in = null;
        try {
            in = new Scanner((InputStream) url.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject jdata = null;
        try {
            jdata = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String temp = null;
        try {
            JSONArray arr = jdata.getJSONArray("dirs");

            for (int i=0; i<arr.length(); i++) {
                list.add(arr.getString(i) );
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (String i: list) {
            if(i.equals(inL+"-"+outL)){ return true;}
        }

      return false;

    }

    public void run() {
        URL url = null;
        try {
            url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20181020T133944Z.8cf9228f391b4f39.44721d2628db09ecf4f35163d0987af6b164b470&text="
                    +text+"&lang="+inL+"-"+outL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Scanner in = null;
        try {
            in = new Scanner((InputStream) url.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject jdata = null;
        try {
            jdata = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String temp = null;
        int code=0;
        try {
            temp = jdata.get("text").toString();
            code = parseInt(jdata.get("code").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(temp);

        if(code==200){
            System.out.println("Перевод выполнен успешно");
        }
        else {
            System.out.println("C переводом что-то не так :(");
        }
}


    }



