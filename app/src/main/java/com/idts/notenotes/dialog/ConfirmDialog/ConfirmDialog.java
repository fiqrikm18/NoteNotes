package com.idts.notenotes.dialog.ConfirmDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.idts.notenotes.R;

public class ConfirmDialog extends Dialog implements IConfirmDialogView{
    Context context;

    TextView editBtn, deleteBtn;
    ProgressDialog progressDialog;

    IConfirmDialogPreserenter preserenter;

    int noteId;

    public ConfirmDialog(@NonNull Context context, int noteId) {
        super(context);
        this.context = context;
        this.noteId = noteId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        preserenter = new ConfirmDialogPreserenter(context, this);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        editBtn = findViewById(R.id.txt_edit);
        deleteBtn = findViewById(R.id.txt_delete);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preserenter.onEditButtonClicked(context, noteId);
                dismiss();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preserenter.onDeleteButtonClicked(context, noteId);
                dismiss();
            }
        });
    }

    @Override
    public void showLoading(Context context) {
        progressDialog.show();
    }

    @Override
    public void hideLoading(Context context) {
        progressDialog.dismiss();
    }
}
