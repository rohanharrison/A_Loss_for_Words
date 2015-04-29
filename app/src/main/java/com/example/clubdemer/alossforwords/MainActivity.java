package com.example.clubdemer.alossforwords;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.widget.EditText;

import java.util.Scanner;

public class MainActivity extends ActionBarActivity {
    GooglePlay GP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            AssetManager assets = getAssets();
            GP = new GooglePlay(assets);
        } catch (IOException e) {
            System.out.println("failed");
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quit Button
        Button quitButton = (Button) findViewById(R.id.quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        //Play Button
        Button passButton = (Button) findViewById(R.id.compPlay);
        passButton.setOnClickListener(new View.OnClickListener() {

            //Dictionary
            Scanner scan =  new Scanner(getResources().openRawResource(R.raw.dictionary));
            Logic log = new Logic(scan);
            public void onClick(View view) {
                setContentView(R.layout.play);

                //Next Button
                Button nextButton = (Button) findViewById(R.id.nextPlayer);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    EditText theText = (EditText) findViewById(R.id.wordView);
                    public void onClick(View view) {
                        Result result = log.requestMove(theText.getText().toString());
                        String theWord = result.getNewWord();
                        theText.setText(theWord);
                        int terminate = result.getStatus();
                        if (terminate == 1)
                        {

                            winLoss(theWord, true);
                        }
                        else if (terminate == -1)
                        {

                            winLoss(theWord, false);
                        }
                    }
                });
            }
        });

        //Force Landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void winLoss(String word, boolean winLoss)
    {

        String[] achieves = GP.checkAchieve(word, winLoss);
        if(winLoss)  setContentView(R.layout.win);
        else    setContentView(R.layout.loss);
        EditText theText = (EditText) findViewById(R.id.achievement);
        theText.setKeyListener(null);
        for (String achieve : achieves) {
            theText.setText(achieve);
        }
        //Replay Button
        Button replayButton = (Button) findViewById(R.id.replay);
        replayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
            }
        });
    }
}
