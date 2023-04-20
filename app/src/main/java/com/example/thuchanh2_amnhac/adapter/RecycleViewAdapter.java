package com.example.thuchanh2_amnhac.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh2_amnhac.R;
import com.example.thuchanh2_amnhac.model.Music;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Music> list;
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecycleViewAdapter(List<Music> list) {
        this.list = list;
    }

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Music> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Music getMusic(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Music music = list.get(position);
        holder.name.setText(music.getName());
        holder.singer.setText(music.getSinger());
        holder.album.setText(music.getAlbum());
        holder.category.setText(music.getCategory());
        holder.liked.setText(music.getLiked());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, singer, album, category, liked;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            singer = view.findViewById(R.id.tvSinger);
            album = view.findViewById(R.id.tvAlbum);
            category = view.findViewById(R.id.tvCategory);
            liked = view.findViewById(R.id.tvLiked);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void onItemClick(View view, int position);
    }
}
