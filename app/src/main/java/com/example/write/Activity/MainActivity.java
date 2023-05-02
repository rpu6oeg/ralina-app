package com.example.write.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.write.Fragments.FragmentDocs;
import com.example.write.Fragments.FragmentRead;
import com.example.write.Fragments.FragmentSettings;
import com.example.write.Models.NoteModel;
import com.example.write.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<NoteModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setItemIconTintList(null);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int item_id = item.getItemId();

                if (bottom_nav.getSelectedItemId() == item_id)
                    return false;

                switch (item_id) {
                    case R.id.menu_1: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, new FragmentDocs())
                                .commit();
                        break;
                    }
                    case R.id.menu_2: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, new FragmentRead())
                                .commit();
                        break;
                    }
                    case R.id.menu_3: {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main, new FragmentSettings())
                                .commit();
                        break;
                    }
                }

                return true;
            }
        });

        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        if (prefs.contains("notes"))
            notes = new Gson().fromJson(prefs.getString("notes", null), new TypeToken<List<NoteModel>>() {
            }.getType());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main, new FragmentDocs())
                .commit();
    }
}
