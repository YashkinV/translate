package com.Potoki;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static java.lang.Float.parseFloat;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        String text;
        String inL;
        String outL;
        System.out.println("Введите язык ввода");
        inL = in.nextLine();
        System.out.println("Введите язык перевода");
        outL = in.nextLine();
        System.out.println("Введите текст");
        text = in.nextLine();
       /* text=text.replaceAll(" ","+");*/


        MyThread mt = new MyThread(text, inL, outL);
        if(mt.getLangs()){
         mt.start();}
        else {
            System.out.println("Перевод с "+inL+" на "+outL+"невозможен");
        }




    }

}