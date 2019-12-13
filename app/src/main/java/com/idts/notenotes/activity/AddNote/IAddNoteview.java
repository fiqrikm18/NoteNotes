package com.idts.notenotes.activity.AddNote;

import android.widget.EditText;

public interface IAddNoteview {
    void onFocusChange(EditText editText, String message);
    void finishActivity();
}
