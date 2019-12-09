package com.example.katnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.LayoutManager layoutManager;
    private List<KatNotes> dataSet = new ArrayList<>();
    Context context;

    DatabaseHelper daHel = new DatabaseHelper(this);

    EditText defaultEditText;
    ImageButton defaultSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvView = (RecyclerView) findViewById(R.id.recyclera);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        defaultEditText = (EditText) findViewById(R.id.defaultNotesInput);
        defaultSaveButton = (ImageButton) findViewById(R.id.defaultNotesSave);

        initData();

        defaultSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                }
        });


    }

    private void initData() {
        DatabaseHelper db = new DatabaseHelper(this);
        dataSet = db.selectData();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,dataSet);
        rvView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void addData(){
        DatabaseHelper db = new DatabaseHelper(this);

        KatNotes knotes = new KatNotes();

        int random = new Random().nextInt(99999999) + 10;
        int test = 1;

        knotes.setNotesId(random);
        knotes.setNotesContent(defaultEditText.getText().toString());

        db.insert(knotes);
        //db.getDbtest();
        startActivity(getIntent());

    }


}
