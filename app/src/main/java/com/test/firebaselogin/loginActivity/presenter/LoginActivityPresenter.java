package com.test.firebaselogin.loginActivity.presenter;

import android.app.Activity;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;
import com.test.firebaselogin.loginActivity.model.LoginActivityModel;
import com.test.firebaselogin.loginActivity.model.LoginModelInterface;

public class LoginActivityPresenter implements LoginModelInterface {

    private LoginActivityModel loginActivityModel;
    private LoginPresenterInterface loginPresenterInterface;

    public LoginActivityPresenter(Activity activity, LoginPresenterInterface loginPresenterInterface) {

        this.loginActivityModel = new LoginActivityModel(activity, LoginActivityPresenter.this);
        this.loginPresenterInterface = loginPresenterInterface;
    }

    public void signIn() {
        loginActivityModel.signIn();
    }

    public void firebaseAuth(GoogleSignInAccount acct) {
        loginActivityModel.firebaseAuthWithGoogle(acct);

    }

    public void facebookToken(AccessToken token) {
        loginActivityModel.handleFacebookAccessToken(token);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        loginPresenterInterface.onSuccess(user);
    }

    @Override
    public void onFailed(String message, Throwable exception) {
        loginPresenterInterface.onFailed(message, exception);
    }

//    @Override
//    public void facebookCallback(CallbackManager mCallbackManager) {
//        loginPresenterInterface.facebookCallback(mCallbackManager);
//        System.out.println("facebook call back........");
//    }
}
