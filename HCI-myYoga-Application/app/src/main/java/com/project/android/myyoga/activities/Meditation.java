package com.project.android.myyoga.activities;

/**
 * Created by Renuka on 4/27/2017.
 */

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.myyoga.R;

import java.util.concurrent.TimeUnit;

public class Meditation extends AppCompatActivity implements View.OnClickListener {


    private long timeCountInMilliSeconds = 1 * 60000;
    private int mudra=7;

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
            R.drawable.anjali,
            R.drawable.explore

    };

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;

    private ProgressBar progressBarCircle;
    private EditText editTextMinute;
    private TextView textViewTime;
    private TextView instruction;
    private TextView information;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private CountDownTimer countDownTimer;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation);

        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
        initListeners();
        final Button btnOpenPopup = (Button)findViewById(R.id.button);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.med_info, null);

                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                information=(TextView) popupView.findViewById(R.id.information);
                switch(mudra){
                    case 0:information.setText("Buddhi Mudra\nBy touching the tips of the little finger and thumb together, you are enhancing intuitive communication. The elements of fire and water are brought together, and this symbolizes communication and openness. It can also help strengthen your intuitive knowledge.");
                        break;
                    case 1:information.setText("Dhaya Mudra\nThis mudra provides calming energy for meditation and is used for deep contemplation and reflection. To do this, place your hands on your lap, left palm under, palms facing up, and the tips of the thumbs touching.");
                            break;
                    case 2:information.setText("Gyana Mudra\nTo do this, bring the tips of the thumb and index finger together, and keep the other three fingers together, lightly stretched. This symbolizes the unity of fire and air as well as the unity of universal and individual consciousness.\n" +
                            "\n" +
                            "The Gyana mudra increases concentration, creativity, and is a gesture of knowledge. Keep your palms facing upwards when feeling receptive or rest your palm on your leg when you wish to feel more grounded.");
                        break;
                    case 3:information.setText("Prana mudra\nThis mudra activates the dormant energy within the body. To do this, place the tips of your thumb, ring finger, and little finger together. This mudra symbolizes the vital energy of prana, and will encourage the flow of this energy, making you feel energized and strong.");
                        break;
                    case 4:information.setText("Shuni Mudra\nBring the tip of the middle finger and thumb together, uniting the elements of fire and connection. This mudra symbolizes patience and discipline, and helps us generate a feeling of stability. Use this mudra when you feel you need additional strength to follow through with tasks.");
                        break;
                    case 5:information.setText("SuryaRavi Mudra\nUnite the tip of the ring finger and the thumb, and you bring together the elements of fire and earth. This mudra represents energy and health, and it provides us with a feeling of balance. It can also help with bringing positive changes into our lives.");
                        break;
                    case 6:information.setText("Anjali Mudra\nBringing the palms together in front of the heart space symbolizes honor and respect toward yourself and toward the universe. This mudra expresses love and gratitude. Namaste.");
                        break;
                    default:information.setText("Close your eyes.Breathe deeply.Clear your mind.\nThe timer will notify you when to stop\n You may choose to hold a mudra while meditating.");
                }

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }});
    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);
        editTextMinute = (EditText) findViewById(R.id.editTextMinute);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        instruction = (TextView) findViewById(R.id.instruction);
        imageViewReset = (ImageView) findViewById(R.id.imageViewReset);
        imageViewStartStop = (ImageView) findViewById(R.id.imageViewStartStop);
        imageView = (ImageView) findViewById(R.id.imageButton2);
        imageView.setImageResource(imageId[mudra]);
        instruction.setText("SET THE TIMER AND MEDITATE PEACEFULLY");

    }

    /**
     * method to initialize the click listeners
     */
    private void initListeners() {
        imageViewReset.setOnClickListener(this);
        imageViewStartStop.setOnClickListener(this);
    }

    /**
     * implemented method to listen clicks
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
        }
    }

    /**
     * method to reset count down timer
     */
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }


    /**
     * method to start and stop count down timer
     */
    private void startStop() {
        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues();
            // call to initialize the progress bar values
            setProgressBarValues();
            // showing the reset icon
            imageViewReset.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            imageViewStartStop.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            editTextMinute.setEnabled(false);
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {

            // hiding the reset icon
            imageViewReset.setVisibility(View.GONE);
            // changing stop icon to start icon
            imageViewStartStop.setImageResource(R.drawable.icon_start);
            // making edit text editable
            editTextMinute.setEnabled(true);
            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }

    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues() {
        int time = 0;
        if (!editTextMinute.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(editTextMinute.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                MediaPlayer mPlayer = MediaPlayer.create(Meditation.this, R.raw.meditation);
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.start();
                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();
                // hiding the reset icon
                imageViewReset.setVisibility(View.GONE);
                // changing stop icon to start icon
                imageViewStartStop.setImageResource(R.drawable.icon_start);
                // making edit text editable
                editTextMinute.setEnabled(true);
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;
            }

        }.start();
        countDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }

    public void mudras(View v)
    {
        Intent intent = new Intent(Meditation.this, Mudras.class);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            mudra =data.getIntExtra("MUDRA",7);
            imageView.setImageResource(imageId[mudra]);
            if(mudra!=7)
            instruction.setText("HOLD THE "+web[mudra].toUpperCase()+" AND MEDITATE");
        }
    }

}
