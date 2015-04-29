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

        Log.d("LogicDebugInfo0: ", "Random char");
        Result r = Logic.requestMove("");
        Log.d("LogicDebugInfo0:", r.getNewWord());
        Log.d("LogicDebugInfo0:", r.getStatus() + "");

        Log.d("LogicDebugInfo1: ", "Game Continue");
        Result r2 = Logic.requestMove("ca");
        Log.d("LogicDebugInfo1:", r2.getNewWord());
        Log.d("LogicDebugInfo1:", r2.getStatus() + "");

        Log.d("LogicDebugInfo2: ", "Player wins");
        Result r3 = Logic.requestMove("word");
        Log.d("LogicDebugInfo2:", r3.getNewWord());
        Log.d("LogicDebugInfo2:", r3.getStatus() + "");
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
