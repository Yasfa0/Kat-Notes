package com.example.katnotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<KatNotes> rvData;

    public RecyclerViewAdapter(Context context, List<KatNotes> inputan){
        this.context = context;
        this.rvData = inputan;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView isi_notes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isi_notes = itemView.findViewById(R.id.notes_content);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        final KatNotes notesIni = rvData.get(position);
        holder.isi_notes.setText(notesIni.getNotesContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenNotes();
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public void OpenNotes(){
        Intent toNotes = new Intent(context,UpdateActivity.class);
        context.startActivity(toNotes);
    }
}
