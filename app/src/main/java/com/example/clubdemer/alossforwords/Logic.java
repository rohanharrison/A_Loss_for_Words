package com.example.clubdemer.alossforwords;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by clubdemer on 4/6/15.
 * Version 0.0 by Logic on 4/14
 */

public class Logic {
    /**
     * requestMove is responsible for the game logic. It returns a random character for the first
     * turn, checks to see if the player made a word after every player's turn, makes an AI turn
     * in response to the player's input, and returns the current string in play and the state of
     * the game.
     *
     * @param sub Current string in play
     * @param scan Scanner object
     * @return Result object containing either new or current string in play and the current game
     *          state. 0 for continue, -1 for a player loss, 1 for an AI loss
     */
    static Result requestMove(String sub, Scanner scan) {
        Calendar c = Calendar.getInstance();
        Random r = new Random(c.getTimeInMillis());
        ArrayList<String> currentList;
        ArrayList<String> winningList;
        String currentString;

        //If first word
        if (sub.isEmpty()) {
            currentString = playChar(r) + "";
            //return substring and 0
            return new Result(currentString, 0);
        }

        currentString = sub;
        Dictionary d = new Dictionary(scan);
        currentList = d.requestList(currentString);

        //Player loss check
        int f = checkList(currentList, currentString);
        if (f==1 || f==2) { //Substring is not part of a word || User made a word
            //return substring and -1
            return new Result(currentString, -1);
        }

        //AI's Turn
        winningList = getWinningList(currentList);

        if (forcedWord(winningList)) {
            //return substring and 1
            return new Result(currentString, 1);
        }
        Log.d("LogicDebugInfo: ", winningList.get(0));
        currentString = placeLetter(winningList, currentList, currentString, r);

        if (checkList(currentList, currentString) == 2) {
            //return substring and 1
            return new Result(currentString, 1);
        }

        return new Result(currentString, 0);
    }

    /**
     * playChar returns a random character for the first move of the game.
     *
     * @param r Random object used to get a random char
     * @return randomly generated char
     */
    private static char playChar(Random r) {
        return (char) (r.nextInt(25) + 97);
    }

    /**
     * placeLetter returns a new string consisting of the AI's move. If a random substring results
     * in a loss for the AI, then the AI systematically goes through it's list of winning words,
     * until it forms a substring that it can win with. If it is forced to play a word, then it
     * simply passes on the last substring it formed.
     *
     * @param list A winning list of words for the AI that contains the current substring
     * @param cList A current list of words containing all words that contain the current substring
     * @param s The current string in play
     * @param ran Random object used for random decisions
     * @return String with the AI's move
     */
    private static String placeLetter(ArrayList<String> list, ArrayList<String> cList,
                                      String s, Random ran) {
        int i = ran.nextInt(list.size());
        String word = list.get(i);
        String temp;

        if (word.indexOf(s) == 0)
            temp = s + word.charAt(s.length());
        else if (word.indexOf(s) == word.length() - s.length())
            temp = word.charAt(word.length() - s.length() - 1) + s;
        else {
            if (ran.nextBoolean())
                temp = s + word.charAt(word.indexOf(s) + s.length());
            else
                temp = word.charAt(word.indexOf(s) - 1) + s;
        }

        boolean needsChange = false;

        for (String w : cList) {
            if (temp.compareTo(w) == 0) {
                needsChange = true;
                break;
            }
        }

        if (needsChange) {
            for (int j = 0; j < list.size(); j++) {
                word = list.get(j);

                if (word.indexOf(s) == 0)
                    temp = s + word.charAt(s.length());
                else if (word.indexOf(s) == word.length() - s.length())
                    temp = word.charAt(word.length() - s.length() - 1) + s;
                else {
                    if (ran.nextBoolean())
                        temp = s + word.charAt(word.indexOf(s) + s.length());
                    else
                        temp = word.charAt(word.indexOf(s) - 1) + s;
                }

                for (String w : cList) {
                    if (temp.compareTo(w) == 0) {
                        needsChange = true;
                        break;
                    } else
                        needsChange = false;
                }

                if (!needsChange)
                    break;
            }
        }

        return temp;
    }

    /**
     * checkList compares the string in play to the list of valid words containing the string to see
     * if there is a complete match, the substring is not valid, or if the game can continue
     *
     * @param list Current list of words that contain the substring in play
     * @param sub Current substring in play
     * @return 1 if the player played a substring that is not part of a word; 2 if the player or AI
     *          formed a word; 0 if no word has been formed yet and the substring is part of a word
     */
    private static int checkList(ArrayList<String> list, String sub) {
        if (list.isEmpty()) {
            return 1; //Player loses; no words
        } else {
            for (String s : list) {
                if (s.compareToIgnoreCase(sub) == 0)
                    return 2; //Word was formed
            }
        }
        return 0;
    }

    /**
     * getWinningList returns a list of words that the AI can win with given the current list of
     * words containing the substring in play
     *
     * @param list Current list of words containing the substring in play
     * @return List of words that the AI can win with, or an empty list
     */
    private static ArrayList<String> getWinningList(ArrayList<String> list) {
        ArrayList<String> rtnList = new ArrayList<>();
        for (String s : list) {
            if ( (s.length() & 1) == 0) //if of even length, add word
                rtnList.add(s);
        }
        return rtnList;
    }

    /**
     * winList determines whether or not the AI is forced to play a word
     *
     * @param winList List of words that the AI can win with (if any)
     * @return True if the AI has no winning options; False otherwise
     */
    private static boolean forcedWord(ArrayList<String> winList) {
        if (winList.size() == 0)
            return true;
        return false;
    }
}
