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
import com.example.write.Adapters.NotesAdapter;
import com.example.write.Models.NoteModel;
import com.example.write.R;

import java.util.List;

public class FragmentDocs extends Fragment {
    public static RecyclerView docs_recycler;
    public List<NoteModel> notes = MainActivity.notes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docs, container, false);

        docs_recycler = view.findViewById(R.id.docs_recycler);
        docs_recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        docs_recycler.setAdapter(new NotesAdapter(notes, getContext()));

        return view;
    }
}
