package com.idts.notenotes.dialog.ConfirmDialog;

import android.content.Context;

public interface IConfirmDialogPreserenter {
    void onDeleteButtonClicked(Context context, int noteId);
    void onEditButtonClicked(Context context, int noteId);
}
