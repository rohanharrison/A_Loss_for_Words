package com.example.clubdemer.alossforwords;

/**
 * Created by Rohan on 4/22/2015.
 */
public class Result {
    String newWord; //the new word created by the ai
    int status;     //the current status of the program, -1 if loss, 0 if continue, 1 if won

    /**
     * Result will have the string for the new word and it will have the status of the game
     * @param newWord the word created by the ai
     * @param status the current status of the program, -1 if loss, 0 if continue, 1 if won
     */
    public Result(String newWord, int status)
    {
        this.newWord = newWord;
        this.status = status;
    }

    /**
     * accessor method for the new word
     * @return the new word created by the ai
     */
    public String getNewWord()
    {
        return newWord;
    }

    /**
     * accessor method for the current status of the program
     * @return the status of the program, -1 if loss, 0 if continue, 1 if won
     */
    public int getStatus()
    {
        return status;
    }
}
