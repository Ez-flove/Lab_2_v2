package com.example.thuchanh2_amnhac.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.thuchanh2_amnhac.model.Music;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Amnhac.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE musics(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,singer TEXT, album TEXT, category TEXT, liked TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Music> getAll() {
        List<Music> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "category DESC";
        Cursor rs = st.query("musics", null, null, null, null, null, order);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String singer = rs.getString(2);
            String album = rs.getString(3);
            String category = rs.getString(4);
            String liked = rs.getString(5);
            list.add(new Music(id, name, singer, album, category, liked));
        }
        return list;
    }

    public long addItem(Music i) {
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("singer", i.getSinger());
        values.put("album", i.getAlbum());
        values.put("category", i.getCategory());
        values.put("liked", i.getLiked());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("musics", null, values);
    }

    //lay cac music theo category
    public List<Music> getByAlbum(String category) {
        List<Music> list = new ArrayList<>();
        String whereClause = "category like ?";
        String[] whereArgs = {category};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("musics",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String singer = rs.getString(2);
            String album = rs.getString(3);
            String liked = rs.getString(5);
            list.add(new Music(id, name, singer, album, category, liked));
        }
        return list;
    }

    //update
    public int updateItem(Music i) {
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("singer", i.getSinger());
        values.put("album", i.getAlbum());
        values.put("category", i.getCategory());
        values.put("liked", i.getLiked());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("musics",
                values, whereClause, whereArgs);
    }

    //delete
    public int deleteMusic(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("musics",
                whereClause, whereArgs);
    }

    public List<Music> searchByAlbum(String key) {
        List<Music> list = new ArrayList<>();
        String where = "album like ?";
        String[] args = {"%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("musics", null, where,
                args, null, null, null);
        while (rs != null && rs.moveToNext()) {
            list.add(new Music(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)));
        }
        return list;
    }

    public List<Music> searchByCategory(String category) {
        List<Music> list = new ArrayList<>();
        String where = "category like ?";
        String[] args = {category};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("musics", null, where,
                args, null, null, null);
        while (rs != null && rs.moveToNext()) {
            list.add(new Music(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)));
        }
        return list;
    }
}
