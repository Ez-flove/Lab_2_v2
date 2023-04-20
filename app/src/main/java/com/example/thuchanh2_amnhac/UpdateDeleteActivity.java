package com.example.thuchanh2_amnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thuchanh2_amnhac.database.SQLiteHelper;
import com.example.thuchanh2_amnhac.model.Music;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner spAlbum, spCate;
    private EditText eName, eSinger, eLiked;
    private Button btUpdate, btRemove, btBack;
    private Music music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btBack.setOnClickListener(this);
        Intent intent = getIntent();
        music = (Music) intent.getSerializableExtra("music");
        eName.setText(music.getName());
        eSinger.setText(music.getSinger());
        eLiked.setText(music.getLiked());
        int p = 0;
        for (int i = 0; i < spAlbum.getCount(); i++) {
            if (spAlbum.getItemAtPosition(i).toString().equalsIgnoreCase(music.getAlbum())) {
                p = i;
                break;
            }
        }
        spAlbum.setSelection(p);
        int x = 0;
        for (int i = 0; i < spCate.getCount(); i++) {
            if (spCate.getItemAtPosition(i).toString().equalsIgnoreCase(music.getCategory())) {
                x = i;
                break;
            }
        }
        spCate.setSelection(x);
    }

    private void initView() {
        spAlbum = findViewById(R.id.spAlbum);
        spCate = findViewById(R.id.spCategory);
        eName = findViewById(R.id.tvName);
        eSinger = findViewById(R.id.tvSinger);
        eLiked = findViewById(R.id.tvLiked);
        btUpdate = findViewById(R.id.btUpdate);
        btRemove = findViewById(R.id.btRemove);
        btBack = findViewById(R.id.btBack);
        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
        spCate.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if (view == btBack) {
            finish();
        }
        if (view == btUpdate) {
            String name = eName.getText().toString();
            String singer = eSinger.getText().toString();
            String liked = eLiked.getText().toString();
            String cate = spCate.getSelectedItem().toString();
            String al = spAlbum.getSelectedItem().toString();

            if (!name.isEmpty() && !singer.isEmpty() && !liked.isEmpty()) {
                int id = music.getId();
                Music i = new Music(id, name, singer, al, cate, liked);
                db.updateItem(i);
                finish();
            }
        }
        if (view == btRemove) {
            int id = music.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa!");
            builder.setTitle("Ban co chac muon xoa " + music.getName() + " khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper bb = new SQLiteHelper(getApplicationContext());
                    bb.deleteMusic(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}