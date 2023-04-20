package com.example.thuchanh2_amnhac.model;

import java.io.Serializable;

public class Music implements Serializable {
    private int id;
    private String name, singer, album, category;
    private String liked;

    public Music() {
    }

    public Music(String name, String singer, String album, String category, String liked) {
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.category = category;
        this.liked = liked;
    }

    public Music(int id, String name, String singer, String album, String category, String liked) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.category = category;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }
}
