package com.idts.notenotes.activity.AddNote;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.idts.notenotes.R;
import com.idts.notenotes.libs.SessionManager;
import com.idts.notenotes.models.NotesItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity implements IAddNoteview {
    @BindView(R.id.note_title)
    EditText noteTitle;

    @BindView(R.id.note_body)
    EditText noteBody;

    @BindView(R.id.btn_save_note)
    Button btnSave;

    IAddNotePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ButterKnife.bind(this);
        presenter = new AddNotePresenter(this);

        onFocusChange(noteTitle, "Note Title");
        onFocusChange(noteBody, "Note");

        btnSave.setOnClickListener(v -> presenter.createNote(
                AddNoteActivity.this, new NotesItem(
                        noteTitle.getText().toString(),
                        noteBody.getText().toString(),
                        SessionManager.getUserId(AddNoteActivity.this)
                )
        ));
    }

    @Override
    public void onFocusChange(EditText editText, String message) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) editText.setHint("");
            else editText.setHint(message);
        });
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
