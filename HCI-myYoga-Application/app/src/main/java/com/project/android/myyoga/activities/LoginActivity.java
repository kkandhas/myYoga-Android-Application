package com.project.android.myyoga.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private final AppCompatActivity activity = LoginActivity.this;
    private ObjectPreferences objectPreferences;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;

    User user = new User();
    private NestedScrollView nestedScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        objectPreferences = (ObjectPreferences) this.getApplication();
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        // DONE: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();

                        RequestParams requestParams = new RequestParams();
                        requestParams.put("email", email);
                        requestParams.put("password", password);
                        invokeWS(requestParams);
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically

                this.finish();
            }
        }
    }

    public void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2:8080/MyYoga_-_Project_-_RESTAPI/login/doLogin", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                try {

                    SessionManager sessionManager = objectPreferences.getSessionManager();

                    String responseString = new String(response, "UTF-8");
                    JSONObject obj = new JSONObject(responseString);
                    Log.i(obj.toString(), "This is JSON response");
                    if (obj.getBoolean("status")) {
                        //Log.i((obj.get("User")).toString(),"User Object");
                        Log.i(obj.toString(), "This is JSON response");

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
                        //Intent accountsIntent = new Intent(activity, AsanaDisplay.class);
                        Intent accountsIntent = new Intent(activity, HomePage.class);
                        //accountsIntent.putExtra("Email", email);
                        emptyInputEditText();
                        startActivity(accountsIntent);

                    } else {
                        onLoginFailed();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //return user;
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
//        return user;
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void emptyInputEditText() {
        _emailText.setText(null);
        _passwordText.setText(null);
    }
}
