package com.idts.notenotes.dialog.ConfirmDialog;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.idts.notenotes.activity.EditNote.EditNoteActivity;
import com.idts.notenotes.libs.ApiRequest;
import com.idts.notenotes.libs.Constants;
import com.idts.notenotes.libs.RetrofitBuilder;
import com.idts.notenotes.libs.SessionManager;
import com.idts.notenotes.models.NoteData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmDialogPreserenter implements IConfirmDialogPreserenter {
    private Context context;
    private IConfirmDialogView view;

    ConfirmDialogPreserenter(Context context, IConfirmDialogView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void onDeleteButtonClicked(Context context, int noteId) {
        ApiRequest request = RetrofitBuilder.getInstance(context).create(ApiRequest.class);
        Call<NoteData> call = request.deleteNote(noteId, SessionManager.getUserId(context));
        call.enqueue(new Callback<NoteData>() {
            @Override
            public void onResponse(Call<NoteData> call, Response<NoteData> response) {
                try {
                    //TODO: logical delete call back here
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoteData> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditButtonClicked(Context context, int noteId) {
        Intent i = new Intent(context, EditNoteActivity.class);
        i.putExtra(Constants.TAG_NOTEID, noteId);
        context.startActivity(i);
    }
}
