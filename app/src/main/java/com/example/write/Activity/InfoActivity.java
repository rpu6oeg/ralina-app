package com.example.write.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.write.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView title = findViewById(R.id.title);
        TextView text = findViewById(R.id.text);

        if (getIntent().getExtras().getInt("item") == 1) {
            title.setText("Help and Feedback");
            text.setText("Help content provides answers to common questions about your app.\n\nQ: How to create a new document?\nA: You need to click an image with plus in section 'Documents', write text to opened window and click 'Save'.\n\nQ: How I can read my documents?\nA: You can choose needed document in section 'Reader Mode' and click at them.\n\nQ: How to delete a document?\nA: Click at document in section 'Documents' and in opened window click at 'Delete' label.\n\nQ: Is app saving my documents and send they to third persons?\nA: No, all documents saving only on your device and nobody except you can read they. We'r not sending your documents to third persons.");
        } else {
            title.setText("Privacy Policy");
            text.setText("Daniel Shepkin built the 'Write' as a Free app. This app is provided by developer at no cost and is intended for use as is.\n\nIf you choose to use my app, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for improving the app. I will not use or share your information with anyone.");
        }

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}