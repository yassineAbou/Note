package com.example.note0.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note0.Controller.MainActivity;
import com.example.note0.Model.Note;
import com.example.note0.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Yassine Abou on 1/5/2021.
 */
@SuppressWarnings("ALL")
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder>{

    private static ArrayList<Note> mNotes;
    ArrayList<String> notes = null;
    public Context ctx;

    public MyAdapter(ArrayList<Note> notes) {
        mNotes = notes;

    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new NoteViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note n = mNotes.get(position);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(n.getTime()));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(calendar.getTime());


        holder.mItemTitle.setText(n.getTitle());
        holder.getItemDate.setText(date);
    }

    @Override
    public int getItemCount() {

       return mNotes.size();
    }

    public void addNewNote(Note note) {
        mNotes.add(note);
        notifyItemInserted(mNotes.size() - 1);
    }

    
    //Holder class for RecyclerView
    class NoteViewHolder extends RecyclerView.ViewHolder {

        final TextView mItemTitle;
        final TextView getItemDate;
        ImageButton mDelete;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemTitle = itemView.findViewById(R.id.activity_item_tvNote_txt);
            getItemDate = itemView.findViewById(R.id.activity_item_tvTime_time);
            mDelete = itemView.findViewById(R.id.activity_item_delete_btn);

           SharedPreferences.Editor editor = MainActivity.preferences.edit();

            //When the user clicks minus button delete the item
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note currentNote = mNotes.get((getAdapterPosition()));
                    editor.remove(currentNote.getTime()).apply();
                    editor.remove(currentNote.getTitle()).apply();
                    mNotes.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    }

            });

            }
       }


        }







