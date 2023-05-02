package com.example.write.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.write.Activity.InfoActivity;
import com.example.write.R;

public class FragmentSettings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        final SharedPreferences prefs = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SwitchCompat auto_save = view.findViewById(R.id.auto_save);
        SwitchCompat word_count = view.findViewById(R.id.word_count);
        auto_save.setChecked(false);
        word_count.setChecked(false);

        if (prefs.contains("autoSave") && prefs.getBoolean("autoSave", true))
            auto_save.setChecked(true);

        if (prefs.contains("wordCount") && prefs.getBoolean("wordCount", true))
            word_count.setChecked(true);

        auto_save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("autoSave", isChecked);
                editor.apply();
            }
        });

        word_count.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("wordCount", isChecked);
                editor.apply();
            }
        });

        view.findViewById(R.id.help_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InfoActivity.class).putExtra("item", 1));
            }
        });

        view.findViewById(R.id.privacy_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InfoActivity.class).putExtra("item", 2));
            }
        });

        return view;
    }
}
