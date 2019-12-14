package com.idts.notenotes.fragments.Dashboard;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idts.notenotes.adapter.NoteItemAdapter;
import com.idts.notenotes.libs.ApiRequest;
import com.idts.notenotes.libs.RetrofitBuilder;
import com.idts.notenotes.models.NoteData;
import com.idts.notenotes.models.NotesItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter implements IDashboardPresenter {
    IDashboardView view;
    ArrayList<NoteData> notes = new ArrayList<>();

    NoteItemAdapter adapter;

    public DashboardPresenter(IDashboardView view) {
        this.view = view;
    }

    @Override
    public void showData(Context context, RecyclerView recyclerView, int userId) {
        view.showProgressDialog();
        ApiRequest request = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        Call<NoteData> call = request.getNotes(userId);
        call.enqueue(new Callback<NoteData>() {
            @Override
            public void onResponse(Call<NoteData> call, Response<NoteData> response) {
                view.hideProgressDialog();
                try {
                    ArrayList<NotesItem> data = new ArrayList<>();
                    data.addAll(response.body().getNotes());
                    setRecyclerAdapter(context, recyclerView, data);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoteData> call, Throwable t) {
                view.hideProgressDialog();
                Log.d("FRAGMENT_DASHBOARDERROR", t.getMessage());
            }
        });
    }

    public void setRecyclerAdapter(Context context, RecyclerView recyclerView, ArrayList<NotesItem> data) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter = new NoteItemAdapter(context, data);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
