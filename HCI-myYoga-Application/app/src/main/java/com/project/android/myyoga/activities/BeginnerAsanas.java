package com.project.android.myyoga.activities;

/**
 * Created by thaik on 4/30/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.project.android.myyoga.R;

public class BeginnerAsanas extends AppCompatActivity {

    private Button head,chest, elbow,back,hands,legs;
    private final AppCompatActivity activity = BeginnerAsanas.this;
    private TextView tv ,tv12,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv10,tv11,tv13,tv14;
    //ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        head = (Button)findViewById(R.id.button);
        //toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        tv2 = (TextView)findViewById(R.id.textView2);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv2.setVisibility(View.VISIBLE);

                tv4.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);
                tv13.setVisibility(View.GONE);

            }
        });
/*
        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                //text.setText("Status: " + isChecked);
                if(isChecked) {
                    Intent intent = new Intent(activity, ExpertAsanas.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                }
            }
        });*/

        chest = (Button)findViewById(R.id.button2);
        tv4 = (TextView)findViewById(R.id.textView4);
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv4.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);
                tv13.setVisibility(View.GONE);

            }
        });

        hands = (Button)findViewById(R.id.button3);
        tv6 = (TextView)findViewById(R.id.textView6);
        hands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv6.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);
                tv13.setVisibility(View.GONE);

            }
        });

        back = (Button)findViewById(R.id.button4);
        tv8 = (TextView)findViewById(R.id.textView8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv8.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);
                tv13.setVisibility(View.GONE);

            }
        });


        legs = (Button)findViewById(R.id.button5);
        tv11 = (TextView)findViewById(R.id.textView11);
        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv11.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv13.setVisibility(View.GONE);

            }
        });

        elbow = (Button)findViewById(R.id.button6);
        tv13 = (TextView)findViewById(R.id.textView13);
        elbow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv13.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv6.setVisibility(View.GONE);
                tv8.setVisibility(View.GONE);
                tv11.setVisibility(View.GONE);

            }
        });


    }

    public void goToActivity1 (View view){
        Intent intent = new Intent (this, AsanaDisplay.class);

        TextView bt = (TextView) view;
        String str = bt.getText().toString();;
        intent.putExtra("YOGANAME",str);
        startActivityForResult(intent,0);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }
}