package com.project.android.myyoga.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.android.myyoga.R;
import com.project.android.myyoga.config.ObjectPreferences;
import com.project.android.myyoga.config.SessionManager;
import com.project.android.myyoga.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private final AppCompatActivity activity = SignupActivity.this;

    private ObjectPreferences objectPreferences;
    User user = new User();
    @Bind(R.id.input_name)
    EditText _nameText;

    @Bind(R.id.input_address)
    EditText _addressText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_mobile)
    EditText _mobileText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @Bind(R.id.input_dob)
    EditText _dob;
    //@Bind(R.id.input_height)
    //EditText _height;
    @Bind(R.id.input_weight)
    EditText _weight;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.genderRadioGroup)
    RadioGroup _genderGroup;
    @Bind(R.id.link_login)
    TextView _loginLink;
    @Bind(R.id.height_pick)
    NumberPicker _numberPicker;
    @Bind(R.id.height_inch_pick)
    NumberPicker _numberInchPicker;
    String height_ft = "";
    String height_inch = "";


    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        _numberPicker.setMaxValue(10);
        _numberPicker.setMinValue(1);
        _numberInchPicker.setMaxValue(12);
        _numberInchPicker.setMinValue(0);
        _numberInchPicker.setWrapSelectorWheel(true);
        _numberPicker.setWrapSelectorWheel(true);
        _dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SignupActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        objectPreferences = (ObjectPreferences) this.getApplication();
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
               height_ft = Integer.toString(newVal);
            }
        });
        _numberInchPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                height_inch = Integer.toString(newVal);
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        _dob.setText(sdf.format(myCalendar.getTime()));
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        // emptyInputEditText();
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        // onSignupSuccess();
                        // onSignupFailed();
                        String name = _nameText.getText().toString();
                        String address = _addressText.getText().toString();
                        String email = _emailText.getText().toString();
                        String mobile = _mobileText.getText().toString();
                        String password = _passwordText.getText().toString();
                        String reEnterPassword = _reEnterPasswordText.getText().toString();
                        String dob = _dob.getText().toString();
                        String gender = ((RadioButton) findViewById(_genderGroup.getCheckedRadioButtonId())).getText().toString();
                        //String height = _height.getText().toString();
                        String height = (height_ft).concat("."+height_inch);
                        Log.i(TAG, height);
                        String weight = _weight.getText().toString();
                        RequestParams requestParams = new RequestParams();
                        requestParams.put("email", email);
                        requestParams.put("password", password);
                        requestParams.put("name", name);
                        requestParams.put("address", address);
                        requestParams.put("gender", gender.toUpperCase());
                        requestParams.put("mobile", mobile);
                        requestParams.put("dob", dob);
                        requestParams.put("height", height);
                        requestParams.put("weight", weight);
                        invokeWS(requestParams);
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);

        setResult(RESULT_OK, null);
        //Intent intent = new Intent(getApplicationContext(), AsanaDisplay.class);
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    private void emptyInputEditText() {
        _nameText.setText(null);
        _addressText.setText(null);
        _emailText.setText(null);
        _mobileText.setText(null);
        _passwordText.setText(null);
    }

    public void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8080/MyYoga_-_Project_-_RESTAPI/Register/registerUser", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                try {
                    SessionManager sessionManager = objectPreferences.getSessionManager();
                    String responseString = new String(response, "UTF-8");
                    JSONObject obj = new JSONObject(responseString);
                    Log.i(obj.toString(), "This is JSON response");
                    if (obj.getBoolean("status")) {
                        JSONObject userJSON = obj.getJSONObject("User");
                        user.setName(userJSON.getString("name"));
                        user.setEmail(userJSON.getString("email"));
                        user.setAddress(userJSON.getString("address"));
                        user.setSex(userJSON.getString("gender"));
                        user.setHeight(userJSON.getString("Height"));
                        user.setWeight(userJSON.getString("Weight"));
                        user.setDob(userJSON.getString("dob"));
                        if(sessionManager != null){
                            sessionManager.putObject("User", user);
                            sessionManager.commit();
                        } else {
                            Log.i(TAG, "Preference is Null");
                        }
                        Intent accountsIntent = new Intent(activity, AsanaDisplay.class);
                        //accountsIntent.putExtra("Email", email);
                        emptyInputEditText();
                        startActivity(accountsIntent);
                        onSignupSuccess();
                    } else {
                        onSignupFailed();

                    }
                } catch (JSONException e) {

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Resource not found", Toast.LENGTH_SHORT).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Signup Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}