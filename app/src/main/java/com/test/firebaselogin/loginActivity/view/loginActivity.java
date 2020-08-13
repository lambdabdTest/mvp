package com.test.firebaselogin.loginActivity.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.firebaselogin.ProfileCreateActivity;
import com.test.firebaselogin.R;
import com.test.firebaselogin.loginActivity.presenter.LoginActivityPresenter;
import com.test.firebaselogin.loginActivity.presenter.LoginPresenterInterface;


public class loginActivity extends AppCompatActivity implements LoginPresenterInterface {

    private static final int track = 234;
    private static final String TAG = "quizBuzz";

    private LoginActivityPresenter loginActivityPresenter;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
        loginActivityPresenter = new LoginActivityPresenter(loginActivity.this, loginActivity.this);
        mCallbackManager = CallbackManager.Factory.create();


//google login
        findViewById(R.id.googleSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityPresenter.signIn();
            }
        });


//Facebook login

        LoginButton loginButton = (LoginButton) findViewById(R.id.fbLoginBtn);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                loginActivityPresenter.facebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, ProfileCreateActivity.class).putExtra("valid", "Yes"));
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == track) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                loginActivityPresenter.firebaseAuth(account);
            } catch (ApiException e) {
                Toast.makeText(loginActivity.this, e.getMessage() + "camera", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onSuccess(FirebaseUser user) {
        startActivity(new Intent(this, ProfileCreateActivity.class).putExtra("valid", "Yes"));
    }

    @Override
    public void onFailed(String message, Throwable exception) {
        Toast.makeText(loginActivity.this, "Authentication failed." + "Camera",
                Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void facebookCallback(CallbackManager mCallbackManager) {
//        System.out.println("facebook call back........");
//        this.mCallbackManager = mCallbackManager;
//
//    }
}