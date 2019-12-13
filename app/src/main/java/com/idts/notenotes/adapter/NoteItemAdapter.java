package com.idts.notenotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idts.notenotes.R;
import com.idts.notenotes.dialog.ConfirmDialog.ConfirmDialog;
import com.idts.notenotes.models.NotesItem;

import java.util.ArrayList;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {
    ArrayList<NotesItem> notesItems;
    Context context;

    public NoteItemAdapter(Context context, ArrayList<NotesItem> notesItems) {
        this.notesItems = notesItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesItem item = notesItems.get(position);
        holder.noteTitle.setText(item.getTitle());
        holder.noteBody.setText(item.getBody().length() > 20 ? item.getBody().substring(0, 20) : item.getBody());
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ConfirmDialog cfd = new ConfirmDialog(context, item.getId());
                cfd.show();
//                Log.d("OnPressed", String.valueOf(item.getId()));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (notesItems != null ? notesItems.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        TextView noteBody;
        View v;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.note_title);
            noteBody = itemView.findViewById(R.id.note_body);
            this.v = itemView;
        }
    }
}
