package com.idts.notenotes.activity.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.idts.notenotes.R;

import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    IRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
