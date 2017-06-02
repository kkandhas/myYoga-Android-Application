package com.project.android.myyoga.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.project.android.myyoga.R;


/**
 * Created by Renuka on 4/26/2017.
 */

public class HomePage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        TextView textView2=(TextView) findViewById(R.id.textView2);
        textView2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TextView textView3=(TextView) findViewById(R.id.textView3);
        textView3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        TextView textView4=(TextView) findViewById(R.id.textView4);
        textView4.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    }

    public void perform_action1(View v)
    {
        Intent intent = new Intent(HomePage.this, Meditation.class);
        startActivity(intent);
    }
    public void perform_action2(View v)
    {
        Intent intent = new Intent(HomePage.this, AsanaHomePage.class);
        startActivity(intent);
    }
    public void perform_action3(View v)
    {
        Intent intent = new Intent(HomePage.this, Suggestions.class);
        startActivity(intent);
    }
}
