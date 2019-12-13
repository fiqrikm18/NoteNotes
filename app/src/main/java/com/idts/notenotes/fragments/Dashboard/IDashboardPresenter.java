package com.idts.notenotes.fragments.Dashboard;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface IDashboardPresenter {
    void showData(Context context, RecyclerView recyclerView, int userId);
}
