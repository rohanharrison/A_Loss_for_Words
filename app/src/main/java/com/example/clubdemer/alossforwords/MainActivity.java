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

/**
 * Created by Interface.
 *
 * The MainActivity class will be the main activity for the Loss for Words Application.
 * It will handle the user interface and all user related aspects of game play
 */
public class MainActivity extends ActionBarActivity {
    GooglePlay GP;

    /**
     * onCreate will set up the start of the program. When all game objects are being created,
     * button handlers and all of the different tools required by our application will also
     * be created.
     *
     * @param savedInstanceState data needed for the application to run
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Gogle Play
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

    /**
     * This method will ad the necessary options to the action bar when the action bar
     * is present
     *
     * @param menu the menu to create
     * @returns true that the options menu was created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method handles action bar item clicks.
     *
     *
     * @param item the item in the collection
     * @return true when everything went well with the action handler
     */
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

    /**
     * This private function handles the end of gameplay when a user has either won, or ultimately
     * lost.
     * @param word
     * @param winLoss
     */
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
