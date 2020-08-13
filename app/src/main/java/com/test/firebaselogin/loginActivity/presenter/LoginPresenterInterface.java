package com.test.firebaselogin.loginActivity.presenter;

import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenterInterface {

    void onSuccess(FirebaseUser user);
    void onFailed(String message, Throwable exception);
    //void facebookCallback(CallbackManager mCallbackManager);

}
