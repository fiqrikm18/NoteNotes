package com.idts.notenotes.activity.Login;

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
import com.idts.notenotes.models.LoginModel;
import com.idts.notenotes.models.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;

    LoginPresenter(ILoginView view) {
        this.view = view;
    }

    @Override
    public void onFocused(Context context, final EditText editText, final String hint) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    editText.setHint("");
                }
                else {
                    editText.setHint(hint);
                }
            }
        });
    }

    @Override
    public void auth(final Context context, String username, String password) {
        view.showLoadingDialog();
        view.hideButton();
        ApiRequest request = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        Call<UserLogin> call = request.auth(new LoginModel(username, password));
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                view.hideLoadingDialog();
                view.showButton();
                try {
                    UserLogin userData = response.body();
//                    Toast.makeText(context, userData.toString(), Toast.LENGTH_LONG).show();
//                    Log.d("REQUEST", userData.toString());
                    if(userData.isIsSuccess()) {
                        SessionManager.setToken(context, userData.getToken());
                        SessionManager.setUserId(context, userData.getUserData().getId());
                        SessionManager.setUsername(context, userData.getUserData().getUsername());
                        SessionManager.setIsLoggedin(context, true);

                        context.startActivity(
                                new Intent(context, MainActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        );
                        view.finishActivity();
                    }
                    else {
                        Toast.makeText(context, "Username or Password wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                view.hideLoadingDialog();
                view.showButton();
                Log.d("USERLOGIN_ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void isLoggedin(Context context) {
        if(SessionManager.isLoggedin(context)) {
            context.startActivity(
                    new Intent(context, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
            );
            view.finishActivity();
        }
    }
}
