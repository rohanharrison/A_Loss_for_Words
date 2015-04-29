package com.example.clubdemer.alossforwords;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by clubdemer on 4/6/15.
 */
public class Dictionary {

    ArrayList<String> dictionary = new ArrayList();
    ArrayList<String> sublist = new ArrayList();

     Dictionary (Scanner scan){
         while (scan.hasNext()) {
             dictionary.add(scan.next());
         }
    }



    public ArrayList<String> requestList(String word){
        String next;

        for (int i = 0; i < dictionary.size(); i++){
            next = dictionary.get(i);
            if (next.contains(word)){
                sublist.add(next);
                Log.d("test", next);
            }


        }

        return sublist;
    }





}
