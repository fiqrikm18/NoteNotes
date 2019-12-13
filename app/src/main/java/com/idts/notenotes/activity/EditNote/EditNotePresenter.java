package com.idts.notenotes.activity.EditNote;

import android.content.Context;

public class EditNotePresenter implements IEditNotePresenter {
    Context context;
    IEditNoteView view;

    public EditNotePresenter(Context context, IEditNoteView view) {
        this.context = context;
        this.view = view;
    }
}
