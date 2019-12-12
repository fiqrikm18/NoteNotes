package com.idts.notenotes.activity.Login;

import android.content.Context;
import android.widget.EditText;

public interface ILoginPresenter {
    public void onFocused(Context context, EditText editText, String hint);
    public void auth(Context context, String username, String password);
    public void isLoggedin(Context context);
}
