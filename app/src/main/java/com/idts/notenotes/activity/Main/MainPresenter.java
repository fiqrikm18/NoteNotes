package com.idts.notenotes.activity.Main;

import android.content.Context;
import android.content.Intent;

import com.idts.notenotes.activity.Login.LoginActivity;
import com.idts.notenotes.libs.SessionManager;

public class MainPresenter implements IMainPresenter {
    IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
    }

    @Override
    public void logout(Context context) {
        SessionManager.clear(context);
        context.startActivity(new Intent(context, LoginActivity.class));
        view.finishActivity();
    }
}
