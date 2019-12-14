package com.idts.notenotes.activity.EditNote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.idts.notenotes.R;
import com.idts.notenotes.libs.Constants;

public class EditNoteActivity extends AppCompatActivity implements IEditNoteView {
    IEditNotePresenter presenter;

    // TODO: logical for edit note here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        int noteId = getIntent().getIntExtra(Constants.TAG_NOTE_ID, 0);

        Log.d("NoteID", String.valueOf(noteId));

        presenter = new EditNotePresenter(this, this);
    }
}
