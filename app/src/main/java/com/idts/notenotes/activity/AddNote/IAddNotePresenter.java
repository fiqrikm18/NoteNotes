package com.idts.notenotes.activity.AddNote;

import android.content.Context;

import com.idts.notenotes.models.NotesItem;

public interface IAddNotePresenter {
    void createNote(Context context, NotesItem note);
}
