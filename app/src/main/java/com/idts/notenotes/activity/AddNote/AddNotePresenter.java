package com.idts.notenotes.activity.AddNote;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.idts.notenotes.activity.Main.MainActivity;
import com.idts.notenotes.libs.ApiRequest;
import com.idts.notenotes.libs.RetrofitBuilder;
import com.idts.notenotes.models.NoteData;
import com.idts.notenotes.models.NotesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNotePresenter implements IAddNotePresenter {
    IAddNoteview view;

    public AddNotePresenter(IAddNoteview view) {
        this.view = view;
    }

    @Override
    public void createNote(Context context, NotesItem note) {
        ApiRequest request = RetrofitBuilder.getInstance(context.getApplicationContext()).create(ApiRequest.class);
        Call<NoteData> call = request.createNote(note);
        call.enqueue(new Callback<NoteData>() {
            @Override
            public void onResponse(Call<NoteData> call, Response<NoteData> response) {
                try {
                    NoteData data = response.body();

                    if(data.isIsSuccess()) {
                        context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
                        view.finishActivity();
                    }
                    else {
                        Snackbar.make((View) view,data.getMessage(), Snackbar.LENGTH_SHORT)
                                .setAction("Close", v -> {})
                                .setTextColor(context.getResources().getColor(android.R.color.white))
                                .show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoteData> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
