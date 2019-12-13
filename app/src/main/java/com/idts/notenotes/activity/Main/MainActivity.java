package com.idts.notenotes.activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.idts.notenotes.R;
import com.idts.notenotes.activity.AddNote.AddNoteActivity;
import com.idts.notenotes.fragments.CompleteTask.CompleteTaskFragment;
import com.idts.notenotes.fragments.Dashboard.DashboardFragment;
import com.idts.notenotes.fragments.Profile.ProfileFragment;
import com.idts.notenotes.libs.SessionManager;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView {
    IMainPresenter presenter;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.txt_username)
    TextView tvUsername;

    @BindView(R.id.add_note)
    ImageView addNote;

    ImageView iv1;
    TextView tv1;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter = new MainPresenter(this);

        tvUsername.setText(SessionManager.getUsername(this));

        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_background, null);
        iv1 = view.findViewById(R.id.iv1);
        tv1 = view.findViewById(R.id.tv1);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        setCustomView(0, 1, 2);
//        setTextAndAnimation("Home", R.drawable.ic_dashboard);
        handleFragment(new DashboardFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setCustomView(0, 1, 2);
                        setTextAndAnimation("Home", R.drawable.ic_dashboard);
                        handleFragment(new DashboardFragment());
                        break;
                    case 1:
                        setCustomView(1, 0, 2);
                        setTextAndAnimation("Completed", R.drawable.ic_playlist_add);
                        handleFragment(new CompleteTaskFragment());
                        break;
                    case 2:
                        setCustomView(2, 1, 0);
                        setTextAndAnimation("Profile", R.drawable.ic_person);
                        handleFragment(new ProfileFragment());
                        break;
                    case 3:
                        presenter.logout(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void handleFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();
    }

    @Override
    public void setCustomView(int selectedTan, int non1, int non2) {
        Objects.requireNonNull(tabLayout.getTabAt(selectedTan)).setCustomView(view);
        Objects.requireNonNull(tabLayout.getTabAt(non1)).setCustomView(null);
        Objects.requireNonNull(tabLayout.getTabAt(non2)).setCustomView(null);
    }

    @Override
    public void setTextAndAnimation(String text, int image) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);
        animation.setInterpolator(new AccelerateInterpolator());
        iv1.setImageResource(image);
        tv1.setText(text);

        iv1.setAnimation(animation);
        tv1.setAnimation(animation);
    }
}
