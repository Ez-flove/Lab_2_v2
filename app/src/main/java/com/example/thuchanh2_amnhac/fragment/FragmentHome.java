package com.example.thuchanh2_amnhac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2_amnhac.AddActivity;
import com.example.thuchanh2_amnhac.R;
import com.example.thuchanh2_amnhac.UpdateDeleteActivity;
import com.example.thuchanh2_amnhac.adapter.RecycleViewAdapter;
import com.example.thuchanh2_amnhac.database.SQLiteHelper;
import com.example.thuchanh2_amnhac.model.Music;

import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Music> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Music music = adapter.getMusic(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("music", music);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Music> list = db.getAll();
        adapter.setList(list);
    }
}
