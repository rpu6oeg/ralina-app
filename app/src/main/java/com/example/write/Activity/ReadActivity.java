package com.example.write.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.write.R;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        final TextView text = findViewById(R.id.text);
        text.setText(MainActivity.notes.get(getIntent().getExtras().getInt("currentCard")).getText());

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
                i.setType("text/plain");

                Intent.createChooser(i, "Share with");
                startActivity(i);
            }
        });
    }
}