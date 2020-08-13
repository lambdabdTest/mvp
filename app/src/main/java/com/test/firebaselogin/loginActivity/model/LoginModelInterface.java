package com.test.firebaselogin.loginActivity.model;

import com.google.firebase.auth.FirebaseUser;

public interface LoginModelInterface {
    void onSuccess(FirebaseUser user);
    void onFailed(String message, Throwable exception);
    //void facebookCallback(CallbackManager mCallbackManager);
}
