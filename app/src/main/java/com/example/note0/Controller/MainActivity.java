package com.example.note0.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note0.Adapter.MyAdapter;
import com.example.note0.Model.Note;
import com.example.note0.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    /* Declaring :
     the features of Note0
     ArrayList
     RecyclerView
     SharedPreferences
     keys to store notes
 */
    private  ArrayList<Note> mArrayList = new ArrayList<>();
    private EditText mText;
    private ImageButton mAdd;
    private RecyclerView mRecyclerView;
    ArrayList<String> notes = null;
    public static SharedPreferences preferences;
    Context mContext;
    MyAdapter adapter = new MyAdapter( mArrayList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //inflate the elements of the layout
     mText = findViewById(R.id.activity_main_Note_input);
     mAdd = findViewById(R.id.activity_main_Add_btn);
     mRecyclerView = findViewById(R.id.activity_maim_items_recyclerView);


        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        Map<String, String> map = (Map<String, String>) preferences.getAll();
        Set<String> keySet = map.keySet();

        notes = new ArrayList<>(keySet);

        for (int i = 0; i < notes.size(); i++) {
            String title = map.get(notes.get(i));
            String timeStamp = notes.get(i);
            mArrayList.add(new Note(title, timeStamp));
        }





     /* when the use clicks add button :
     show editText with current Date in recyclerView
     disable editText
     Store the note
      */
     mAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             String etText = mText.getText().toString();
             long sec = System.currentTimeMillis();

             if (etText.equals("")) {
                 return;
             }

             adapter.addNewNote(
                     new Note(etText, "" + sec));
            // mArrayList.add(new Note(etText, "" + sec));
             editor.putString("" + sec, etText);
             adapter.notifyDataSetChanged();
             InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
             imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
             editor.apply();
             mText.setText("");
         }
     });


     //Sticking MyAdapter to mainActivity

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(adapter);





    }


}