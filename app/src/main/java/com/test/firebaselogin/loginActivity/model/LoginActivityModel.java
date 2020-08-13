package com.test.firebaselogin.loginActivity.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.test.firebaselogin.R;

public class LoginActivityModel {


    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    Activity activity;
    //CallbackManager mCallbackManager;
    private LoginModelInterface loginModelInterface;

    public LoginActivityModel(Activity activity, LoginModelInterface loginModelInterface) {

        this.loginModelInterface = loginModelInterface;
        this.activity = activity;
        FacebookSdk.sdkInitialize(activity.getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        //mCallbackManager = CallbackManager.Factory.create();


    }


    public void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginModelInterface.onSuccess(user);
                        } else {
                            Log.w("", "signInWithCredential:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed." + "Camera",
                                    Toast.LENGTH_SHORT).show();
                            loginModelInterface.onFailed("", task.getException());

                        }

                    }
                });
    }


    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginModelInterface.onSuccess(user);
                        } else {
                            Log.w("", "signInWithCredential:failure", task.getException());
                            loginModelInterface.onFailed("signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }


    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, 234);
    }

}
