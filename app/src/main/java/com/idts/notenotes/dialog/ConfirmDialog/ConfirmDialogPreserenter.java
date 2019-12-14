package com.idts.notenotes.dialog.ConfirmDialog;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idts.notenotes.activity.EditNote.EditNoteActivity;
import com.idts.notenotes.activity.Main.MainActivity;
import com.idts.notenotes.adapter.NoteItemAdapter;
import com.idts.notenotes.fragments.Dashboard.DashboardFragment;
import com.idts.notenotes.fragments.Dashboard.DashboardPresenter;
import com.idts.notenotes.libs.ApiRequest;
import com.idts.notenotes.libs.Constants;
import com.idts.notenotes.libs.RetrofitBuilder;
import com.idts.notenotes.libs.SessionManager;
import com.idts.notenotes.models.NoteData;
import com.idts.notenotes.models.NotesItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmDialogPreserenter implements IConfirmDialogPreserenter {
    private Context context;
    private IConfirmDialogView view;

    NoteItemAdapter adapter;

    ArrayList<NoteData> notes = new ArrayList<>();

    ConfirmDialogPreserenter(Context context, IConfirmDialogView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void onDeleteButtonClicked(Context context, int noteId) {
        new AlertDialog.Builder(context)
                .setTitle("Delete confirmation")
                .setMessage("Are you sure you want to delete this note?")
                .setNegativeButton("No", null)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteNote(context, noteId);
                    }
                })
                .show();
    }

    private void deleteNote(Context context, int noteId) {
        view.showLoading(context);
        ApiRequest request = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        Call<NoteData> call = request.deleteNote(noteId, SessionManager.getUserId(context));
        call.enqueue(new Callback<NoteData>() {
            @Override
            public void onResponse(Call<NoteData> call, Response<NoteData> response) {
                view.hideLoading(context);
                try {
                    NoteData data = response.body();
                    assert data != null;
                    if(data.isSuccess()) {
                        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoteData> call, Throwable t) {
                view.hideLoading(context);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditButtonClicked(Context context, int noteId) {
        Intent i = new Intent(context, EditNoteActivity.class);
        i.putExtra(Constants.TAG_NOTE_ID, noteId);
        context.startActivity(i);
    }
}
