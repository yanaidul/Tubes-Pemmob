package com.example.aplikasiperekamsuara.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aplikasiperekamsuara.Adapters.FileViewerAdapter;
import com.example.aplikasiperekamsuara.Database.DBHelper;
import com.example.aplikasiperekamsuara.Models.RecordingItem;
import com.example.aplikasiperekamsuara.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileViewerFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    DBHelper dbHelper;
    private FileViewerAdapter fileViewerAdapter;

    ArrayList<RecordingItem> arrayListAudios;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_viewer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DBHelper(getContext());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        arrayListAudios = dbHelper.getAllAudios();

        if(arrayListAudios==null)
        {
            Toast.makeText(getContext(),"Tidak ada file audio",Toast.LENGTH_LONG).show();
        }
        else
        {
            fileViewerAdapter = new FileViewerAdapter(getActivity(),arrayListAudios,llm);
            recyclerView.setAdapter(fileViewerAdapter);
        }

    }
}
