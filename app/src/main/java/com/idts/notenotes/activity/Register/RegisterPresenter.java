package com.idts.notenotes.activity.Register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.idts.notenotes.activity.Main.MainActivity;
import com.idts.notenotes.libs.ApiRequest;
import com.idts.notenotes.libs.RetrofitBuilder;
import com.idts.notenotes.libs.SessionManager;
import com.idts.notenotes.models.RegisterModel;
import com.idts.notenotes.models.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements IRegisterPresenter {
    IRegisterView view;

    public RegisterPresenter(IRegisterView view) {
        this.view = view;
    }

    @Override
    public void onFucusChanged(Context context, final EditText editText, final String hint) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) editText.setHint("");
                else editText.setHint(hint);
            }
        });
    }

    @Override
    public void register(final Context context, RegisterModel registerData) {
        view.showDialogBar();
        view.hideButton();
        final ApiRequest request = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        Call<UserLogin> call = request.register(registerData);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                view.hideDialogBar();
                view.showButton();
                try {
                    UserLogin userData = response.body();
                    if(userData.isIsSuccess()) {
                        //Toast.makeText(context, userData.toString(), Toast.LENGTH_LONG).show();
                        SessionManager.setToken(context, userData.getToken());
                        SessionManager.setUserId(context, userData.getUserData().getId());
                        SessionManager.setUsername(context, userData.getUserData().getUsername());
                        SessionManager.setIsLoggedin(context, true);

                        context.startActivity(
                                new Intent(context, MainActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        );
                        Toast.makeText(context, userData.getMessage(), Toast.LENGTH_SHORT).show();
                        view.finishActivity();
                    }
                    else {
                        Toast.makeText(context, "Please fill out the form correctly", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                view.hideDialogBar();
                view.showButton();
                Log.d("ERROR_REQUEST", t.getMessage());
            }
        });
    }
}
