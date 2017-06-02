package com.project.android.myyoga.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.project.android.myyoga.R;

/**
 * Created by Renuka on 4/27/2017.
 */

public class AsanaHomePage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.asanas);
    TextView textView2=(TextView) findViewById(R.id.textView2);
        textView2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
    TextView textView3=(TextView) findViewById(R.id.textView3);
        textView3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

    }

    public void perform_action_beginner(View v)
    {
        Intent intent = new Intent(AsanaHomePage.this, BeginnerAsanas.class);
        startActivity(intent);
    }
    public void perform_action_expert(View v)
    {
        Intent intent = new Intent(AsanaHomePage.this, ExpertAsanas.class);
        startActivity(intent);
    }


}

