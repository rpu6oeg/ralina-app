package com.example.write.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.write.Fragments.FragmentDocs;
import com.example.write.Models.NoteModel;
import com.example.write.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    private int currentCard;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        currentCard = getIntent().getExtras().getInt("currentCard");
        final SharedPreferences prefs = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        final TextView word_count = findViewById(R.id.word_count);

        text = findViewById(R.id.text);
        if (currentCard != -1)
            text.setText(MainActivity.notes.get(currentCard).getText());
        else
            text.setText(null);

        if (!(prefs.contains("wordCount") && prefs.getBoolean("wordCount", true)))
            word_count.setVisibility(View.GONE);
        else {
            word_count.setVisibility(View.VISIBLE);
            word_count.setText(text.getText().length() + " / 500");
        }

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (prefs.contains("autoSave") && prefs.getBoolean("autoSave", true)) {
                    if (currentCard != -1 && text.getText().length() != 0) {
                        saveNotes();

                        FragmentDocs.docs_recycler.getAdapter().notifyDataSetChanged();
                    }
                }

                if (word_count.getVisibility() == View.VISIBLE)
                    word_count.setText(String.format("%d / 500", text.getText().length()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNotes();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }
        });

        TextView delete = findViewById(R.id.delete);
        if (currentCard == -1)
            delete.setText("Cancel");
        else
            delete.setText("Delete");

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCard != -1) {
                    MainActivity.notes.remove(currentCard);

                    saveNotes();
                }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }
        });
    }

    private void saveNotes() {
        try {
            Date date = new Date();

            DateFormat dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            String day = dateFormat.format(date);

            dateFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            String year = dateFormat.format(date);

            dateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
            int month = Integer.parseInt(dateFormat.format(date));

            dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            String time = dateFormat.format(date);

            String dateTime = String.format("%s %s %s, %s", day, new DateFormatSymbols().getMonths()[month - 1], year, time);

            if (currentCard != -1) {
                MainActivity.notes.get(currentCard).setText(text.getText().toString());
                MainActivity.notes.get(currentCard).setLastEdit(dateTime);
            } else {
                MainActivity.notes.add(new NoteModel(text.getText().toString(), dateTime, dateTime));
            }
        } catch (Exception ignored) {
        }

        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.notes);
        editor.putString("notes", json);
        editor.apply();
    }
}