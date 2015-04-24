package com.example.clubdemer.alossforwords;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LogicDebugInfo: ", "Random char");
        Result r = Logic.requestMove("");
        Log.d("LogicDebugInfo:", r.getNewWord());
        Log.d("LogicDebugInfo:", r.getStatus() + "");

        Log.d("LogicDebugInfo: ", "Player loses");
        Result r2 = Logic.requestMove("ca");
        Log.d("LogicDebugInfo:", r2.getNewWord());
        Log.d("LogicDebugInfo:", r2.getStatus() + "");

        Log.d("LogicDebugInfo: ", "Player wins");
        Result r3 = Logic.requestMove("word");
        Log.d("LogicDebugInfo:", r3.getNewWord());
        Log.d("LogicDebugInfo:", r3.getStatus() + "");

        Log.d("LogicDebugInfo: ", "Game Continue");
        Result r4 = Logic.requestMove("wo");
        Log.d("LogicDebugInfo:", r4.getNewWord());
        Log.d("LogicDebugInfo:", r4.getStatus() + "");
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
}
