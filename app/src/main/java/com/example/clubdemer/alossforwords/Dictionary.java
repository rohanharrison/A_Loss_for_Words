package com.example.clubdemer.alossforwords;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by clubdemer on 4/6/15.
 */
public class Dictionary {
    static ArrayList<String> requestList(String sub, Scanner scan) {
        ArrayList<String> dict = new ArrayList<>();
        ArrayList<String> ret = new ArrayList<>();
        dict.add("cart");
        dict.add("words");

        for (String s : dict) {
            if (s.contains(sub))
                ret.add(s);
        }
        return ret;
    }
}
