package com.idts.notenotes.activity.Register;

import android.content.Context;
import android.widget.EditText;

import com.idts.notenotes.models.RegisterModel;

public interface IRegisterPresenter {
    void onFucusChanged(Context context, EditText editText, String hint);
    void register(Context context, RegisterModel registerData);
}
