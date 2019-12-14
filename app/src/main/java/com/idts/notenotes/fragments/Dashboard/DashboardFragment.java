package com.idts.notenotes.fragments.Dashboard;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.idts.notenotes.R;
import com.idts.notenotes.libs.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements IDashboardView {
    private IDashboardPresenter presenter;

    @BindView(R.id.progressDialogBar)
    ProgressBar progressBar;

    @BindView(R.id.rv_notes_card)
    RecyclerView rvNotesCard;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_dashboard, container, false);

        ButterKnife.bind(this, view);
        presenter = new DashboardPresenter(this);

        presenter.showData(
                getActivity(),
                rvNotesCard,
                SessionManager.getUserId(getActivity().getApplicationContext())
        );

        Log.d("MainActivity", "onCreate()");

        return view;
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
        rvNotesCard.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
        rvNotesCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume()");
        presenter.showData(
                getActivity(),
                rvNotesCard,
                SessionManager.getUserId(getActivity().getApplicationContext())
        );
    }
}
