package com.idts.notenotes.activity.Login;

import android.content.Context;
import android.widget.EditText;

public interface ILoginPresenter {
    void onFocused(Context context, EditText editText, String hint);
    void auth(Context context, String username, String password);
    void isLoggedin(Context context);
}
