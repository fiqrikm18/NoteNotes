package com.idts.notenotes.activity.Register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.idts.notenotes.R;
import com.idts.notenotes.models.RegisterModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    IRegisterPresenter presenter;

    @BindView(R.id.txt_register)
    TextView txtRegister;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.fullname)
    EditText fullname;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.register_btn)
    Button btnRegister;

    @BindView(R.id.loadingDialog)
    ProgressBar loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);

        presenter.onFucusChanged(this, username, "Username");
        presenter.onFucusChanged(this, fullname, "Fullname");
        presenter.onFucusChanged(this, email, "Email");
        presenter.onFucusChanged(this, password, "Password");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterModel registerData = new RegisterModel(username.getText().toString(), fullname.getText().toString(), email.getText().toString(), password.getText().toString());
                presenter.register(getApplicationContext(), registerData);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showDialogBar() {
        loadingDialog.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialogBar() {
        loadingDialog.setVisibility(View.GONE);
    }

    @Override
    public void showButton() {
        btnRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        btnRegister.setVisibility(View.GONE);
    }
}
