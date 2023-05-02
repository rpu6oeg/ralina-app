package com.example.write.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.write.Activity.MainActivity;
import com.example.write.Adapters.ReadAdapter;
import com.example.write.R;

public class FragmentRead extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        RecyclerView read_recycler = view.findViewById(R.id.read_recycler);
        read_recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        read_recycler.setAdapter(new ReadAdapter(MainActivity.notes, getContext()));

        return view;
    }
}
