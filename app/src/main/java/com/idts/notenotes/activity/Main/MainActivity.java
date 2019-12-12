package com.idts.notenotes.activity.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.idts.notenotes.R;
import com.idts.notenotes.libs.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView {
    IMainPresenter presenter;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @BindView(R.id.txv)
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter = new MainPresenter(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout(MainActivity.this);
            }
        });

        txv.setText(SessionManager.getToken(this));
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
