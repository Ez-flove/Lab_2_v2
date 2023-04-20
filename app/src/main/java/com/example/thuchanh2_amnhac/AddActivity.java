package com.example.thuchanh2_amnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.thuchanh2_amnhac.database.SQLiteHelper;
import com.example.thuchanh2_amnhac.model.Music;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner spAlbum, spCate;
    private EditText eName, eSinger, eLiked;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView() {
        spAlbum = findViewById(R.id.spAlbum);
        spCate = findViewById(R.id.spCategory);
        eName = findViewById(R.id.tvName);
        eSinger = findViewById(R.id.tvSinger);
        eLiked = findViewById(R.id.tvLiked);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
        spCate.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            String name = eName.getText().toString();
            String singer = eSinger.getText().toString();
            String liked = eLiked.getText().toString();
            String cate = spCate.getSelectedItem().toString();
            String al = spAlbum.getSelectedItem().toString();

            if (!name.isEmpty() && !singer.isEmpty() && !liked.isEmpty()) {
                Music i = new Music(name, singer, al, cate, liked);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}