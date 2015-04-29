package com.example.clubdemer.alossforwords;
import android.content.res.AssetManager;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by clubdemer on 4/6/15.
 */
public class GooglePlay {
    InputStream input;
    BufferedReader bufferedReader;
    AssetManager assetManager;
    Map<String, Integer[]> lockedAchieves;
    Map<String, Integer[]> unlockedAchieves;

    public GooglePlay(AssetManager assetManager) throws IOException {
        this.assetManager = assetManager;
        String line = null;
        Integer[] progress = new Integer[2];

        input = assetManager.open("Achievements.txt");
        bufferedReader = new BufferedReader(new InputStreamReader(input));
        lockedAchieves = new HashMap<String, Integer[]>();
        unlockedAchieves = new HashMap<String, Integer[]>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] achievement = line.split(":");
            //achievement:int:int
            //if achievement is locked 1 and 2 =/=
                progress[0] = Integer.parseInt(achievement[1]);
                progress[1] = Integer.parseInt(achievement[2]);
            if(progress[0] < progress[1]){
                lockedAchieves.put(achievement[0], progress);
            }else{
                unlockedAchieves.put(achievement[0], progress);
            }
        }
        bufferedReader.close();
    }
    public void checkForAchievement(String ws) throws IOException {

        //achievements are only awarded on a win
         increment("win100achieve");
        if(twoLetter(ws)){
            increment("twoletterachieve");
        }
        if(tenLetter(ws)){
            increment("tenletterachieve");
        }
        if(fiveVowel(ws)){
            increment("fivevowelachieve");
        }
        if(allVowel(ws)){
            increment("allvowelachieve");
        }
        //write all data back to the text file
        /*for(Map.Entry<String, Integer[]> entry : lockedAchieves.entrySet()){
            String achievement = entry.getKey() + ":" + entry.getValue()[0] + ":" +
                                entry.getValue()[1];
        }
        for(Map.Entry<String, Integer[]> entry: unlockedAchieves.entrySet()){
            String achievement = entry.getKey() + ":" + entry.getValue()[0] + ":" +
                    entry.getValue()[1];
        }*/



        /*
        increment("win100Achieve", 1); //win100Achieve is "achievement id"
        if (win100Achieve.getCurrentSteps() >= 100) {
            Games.Achievements.unlock(mGoogleApiClient, "win100Achieve");
        }
        if (twoLetter(ws)) {
            Games.Achievements.increment(mGoogleApiClient, "twoLetterAchieve", 1);
            if (twoLetterAchieve.getCurrentSteps() >= 100) {
                Games.Achievements.unlock(mGoogleApiClient, "twoLetterAchieve");
            }
        }
        if (tenLetter(ws)) {
            Games.Achievements.unlock(mGoogleApiClient, "tenLetterAchieve");
        }
        if (fiveVowel(ws)) {
            Games.Achievements.unlock(mGoogleApiClient, "fiveVowelAchieve");
        }
        if (allVowel(ws)) {
            Games.Achievements.unlock(mGoogleApiClient, "allVowelAchieve");
        }*/
    }

    public boolean connectedToGP(){
        return true;
    }
    public boolean twoLetter(String s){
        //already a win, don't need to check if it's a word
        return (s.length() == 2);
    }

    public boolean tenLetter(String s) {
        return (s.length() >= 10);
    }

    public boolean fiveVowel(String s) {
        //assumes winning string is all lower case
        int vowels = 0;
        for(int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vowels++;
        }

        return (vowels >= 5);
    }

    public boolean allVowel(String s) {
        //assumes winning string is all lower case
        int a = 0, e = 0, i = 0, o = 0, u = 0;

        for(int j = 0; j < s.length(); j ++) {
            char c = s.charAt(j);

            if(c == 'a') {
                a++;
            } else if (c == 'e') {
                e++;
            } else if (c == 'i') {
                i++;
            } else if (c == 'o') {
                o++;
            } else if (c == 'u') {
                u++;
            } else {
                break;
            }
        }

        return (a >= 1 && e >= 1 && i >= 1 && o >= 1 && u >= 1);
    }

    public void increment(String achieve){
        if(lockedAchieves.containsKey(achieve)) {
            Integer[] progress = lockedAchieves.get(achieve);
            progress[0] += 1;
            lockedAchieves.put(achieve, progress);
        }
    }

    public Map<String, Integer[]> getUnlockedAchieves(){
        return unlockedAchieves;
    }
}
