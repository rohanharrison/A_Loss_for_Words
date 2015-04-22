package com.example.clubdemer.alossforwords;
import java.util.*;
/**
 * Created by clubdemer on 4/6/15.
 */
public class Logic {
    int i;

    public Logic()
    {
        i = 0;
    }
    public Result requestMove(String word)
    {
        int status = 0;
        switch (i)
        {
            case 0:
                word = "a" + word;
                i++;
                break;
            case 1:
                word += "b";
                i++;
                break;
            case 2:
                status = -1;
                break;
        }
        return new Result(word, status);
    }
}
