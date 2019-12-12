package com.idts.notenotes.activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.idts.notenotes.R;
import com.idts.notenotes.activity.Register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    ILoginPresenter presenter;

    @BindView(R.id.login_btn)
    Button btnLogin;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.txt_register)
    TextView txtRegister;

    @BindView(R.id.loadingDialog)
    ProgressBar loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        presenter.onFocused(this, username, "Username");
        presenter.onFocused(this, password, "Password");

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.auth(LoginActivity.this, username.getText().toString(), password.getText().toString());
            }
        });

        presenter.isLoggedin(this);
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.setVisibility(View.GONE);
    }

    @Override
    public void showButton() {
        btnLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        btnLogin.setVisibility(View.GONE);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
