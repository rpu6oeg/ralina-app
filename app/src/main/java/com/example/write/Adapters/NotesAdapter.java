package com.example.write.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.write.Activity.EditActivity;
import com.example.write.Models.NoteModel;
import com.example.write.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteModel> notes;
    private Context context;
    private boolean isFirst = true;

    public NotesAdapter(List<NoteModel> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isFirst) {
            isFirst = false;
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_add, parent, false));
        } else
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position != 0) {
            NoteModel note = notes.get(position - 1);

            holder.date.setText(note.getDate());

            int idx = note.getText().indexOf('\n');
            if (idx != -1) {
                holder.text.setText(note.getText().substring(0, idx));
            } else {
                holder.text.setText(note.getText());
            }

            holder.lastEdit.setText(String.format("Last edit: %s", note.getLastEdit()));
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditActivity.class).putExtra("currentCard", position - 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView date;
        TextView text;
        TextView lastEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            date = itemView.findViewById(R.id.date);
            text = itemView.findViewById(R.id.text);
            lastEdit = itemView.findViewById(R.id.lastEdit);
        }
    }
}
