package com.project.android.myyoga.activities;

/**
 * Created by thaik on 4/29/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.android.myyoga.R;
import com.project.android.myyoga.config.ObjectPreferences;
import com.project.android.myyoga.config.SessionManager;
import com.project.android.myyoga.model.User;


public class Suggestions extends AppCompatActivity implements OnClickListener {

    TextView final_result;
    User user = new User();
    TextView BMI_place;
    float BMI_value;
    String yogasana_under = "Under";
    String yogasana_normal = "Normal";
    String yogasana_over = "Over";
    String yogasana_obese = "Obese";
    private RadioGroup radioAsanaGroup;
    private RadioButton radioAsanaButton;
    private Button btnDisplay;
    private ObjectPreferences objectPreferences;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private RadioButton option5;
    private RadioButton option6;
    private RadioButton option7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions);
        radioAsanaGroup = (RadioGroup) findViewById(R.id.RadioAsana);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);
        option4 = (RadioButton) findViewById(R.id.option4);
        option5 = (RadioButton) findViewById(R.id.option5);
        option6 = (RadioButton) findViewById(R.id.option6);
        option7 = (RadioButton) findViewById(R.id.option7);
        BMI_place = (TextView) findViewById(R.id.BMI);
        btnDisplay.setOnClickListener(this);
        objectPreferences = (ObjectPreferences) this.getApplication();
        SessionManager sessionManager = objectPreferences.getSessionManager();
        user = sessionManager.getObject("User", User.class);

        float weight = Float.parseFloat(user.getWeight());
        String height = user.getHeight();
        if (height.contains(".")) {
            String[] heights = height.split("\\.");
            float height_ft = (Float.parseFloat(heights[0])) * 12;
            float heightInches = height_ft + (Float.parseFloat(heights[1]));
            BMI_value = (weight / (heightInches * heightInches)) * 703;
        } else {
            BMI_value = (weight / ((Float.parseFloat(height) * 12) * (Float.parseFloat(height) * 12))) * 703;
        }

        if (BMI_value <= 18.5) {
            BMI_place.setText("Your BMI index is " + BMI_value + ". You are " + yogasana_under + " Weight");
            option7.setText("Vajrasana");
        } else if (BMI_value > 18.5 && BMI_value <= 24.9) {
            BMI_place.setText("Your BMI index is " + BMI_value + ". You are " + yogasana_normal + " Weight");
            option7.setText("Paschimottanasana");
        } else if (BMI_value >= 25 && BMI_value <= 29.9) {
            BMI_place.setText("Your BMI index is " + BMI_value + ". You are " + yogasana_over + " Weight");
            option7.setText("Ardha Chandraasana");
        } else if (BMI_value >= 30) {
            BMI_place.setText("Your BMI index is " + BMI_value + ". You are " + yogasana_obese + " Weight");
            option7.setText("SuryaNamaskar");
        }

    }

    public void onClick(View v) {

        Intent intent = new Intent(Suggestions.this, AsanaDisplay.class);
        if (option1.isChecked()) {
            String yogasana_rejuvenate = "AdhoMukhaSvanasana";
            intent.putExtra("YOGANAME", yogasana_rejuvenate);
            startActivity(intent);
        } else if (option2.isChecked()) {
            String yogasana_depressed = "Vajrasana";
            intent.putExtra("YOGANAME", yogasana_depressed);
            startActivity(intent);
        } else if (option3.isChecked()) {
            String yogasana_adventures = "Tittibhasana";
            intent.putExtra("YOGANAME", yogasana_adventures);
            startActivity(intent);
        } else if (option4.isChecked()) {
            String yogasana_positive = "Garudasana";
            intent.putExtra("YOGANAME", yogasana_positive);
            startActivity(intent);
        } else if (option5.isChecked()) {
            String yogasana_posture = "Kurmasana";
            intent.putExtra("YOGANAME", yogasana_posture);
            startActivity(intent);
        } else if (option6.isChecked()) {
            String yogasana_back = "Paschimottanasana";
            intent.putExtra("YOGANAME", yogasana_back);
            startActivity(intent);
        } else if (option7.isChecked()) {
            if (BMI_value <= 18.5) {
                intent.putExtra("YOGANAME", "Vajrasana");
                startActivity(intent);
            } else if (BMI_value > 18.5 && BMI_value <= 24.9) {
                intent.putExtra("YOGANAME", "Paschimottanasana");
                startActivity(intent);
            } else if (BMI_value >= 25 && BMI_value <= 29.9) {
                intent.putExtra("YOGANAME", "Ardha Chandraasana");
                startActivity(intent);
            } else if (BMI_value >= 30) {
                intent.putExtra("YOGANAME", "SuryaNamaskar");
                startActivity(intent);
            }
        }
    }
}
