package com.idts.notenotes.activity.Login;

import android.content.Context;

public interface ILoginView {
    public void showLoadingDialog();
    public void hideLoadingDialog();
    public void showButton();
    public void hideButton();
    public void finishActivity();
}
