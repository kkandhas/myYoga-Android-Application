package com.project.android.myyoga.activities;

/**
 * Created by Renuka on 4/27/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

import com.project.android.myyoga.R;

public class Mudras extends Activity {
        ListView list;
        String[] web = {
                "Buddhi Mudra",
                "Dhayana Mudra",
                "Gyana Mudra",
                "Prana Mudra",
                "Shuni Mudra",
                "SuryaRavi Mudra",
                "Anjali Mudra"
        } ;
        Integer[] imageId = {
                R.drawable.buddhi,
                R.drawable.dhyana,
                R.drawable.gyana,
                R.drawable.prana,
                R.drawable.shuni,
                R.drawable.suryaravi,
                R.drawable.anjali

        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mudras_list);

            MudraListAdapter adapter = new
                    MudraListAdapter(Mudras.this, web, imageId);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(Mudras.this, "You Have Selected " +web[+ position], Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    intent.putExtra("MUDRA",position);
                    setResult(2,intent);
                    finish();
                }
            });

        }
    @Override
    public void onBackPressed() {

        Intent mIntent = new Intent();
        mIntent.putExtra("MUDRA",7);
        setResult(RESULT_OK, mIntent);
        super.onBackPressed();
    }


    }

