package com.example.lesson4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {

    public RecyclerView recyclerView;
    private MainAdapter adapter;
    public List<String> list;
    public static final String EXTRA_TEXT1 = "text";
    private Integer currentPosition = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        list.add("Мадрид");
        list.add("Лондон");
        list.add("Париж");
        list.add("Амстердам");
        list.add("Ливерпуль");
        list.add("Турин");
        list.add("Барселона");
        list.add("Прага");
        list.add("Милан");
        list.add("Москва");
        list.add("Казань");
        list.add("Манчестер");
        list.add("Рим");
        list.add("Лиссабон");
        list.add("Санкт-Петербург");
        list.add("Дубай");
        list.add("Севилья");
        adapter = new MainAdapter(list, this, this);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }
    ItemTouchHelper.SimpleCallback simpleCallback =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                      @NonNull RecyclerView.ViewHolder target) {
                    int positionDrag = viewHolder.getAdapterPosition();
                    int positionTarget = target.getAdapterPosition();
                    Collections.swap(adapter.list, positionTarget, positionDrag);
                    adapter.notifyItemMoved(positionDrag, positionTarget);

                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.this.onClick(viewHolder.getAdapterPosition());
                        }
                    });

                    return true;
                }
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    adapter.list.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            };

    @Override
    public void onClick(int position) {
        currentPosition = position;
        Log.d("TAG", "onClick: " + position);
        Intent intent1 = new Intent(this, MainActivity2.class);
        intent1.putExtra(MainActivity2.EXTRA_TEXT, list.get(position));
        startActivityForResult(intent1,position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == currentPosition && resultCode == RESULT_OK && data != null){
            String text = data.getStringExtra(MainActivity2.EXTRA_TEXT1);
            adapter.updateItem(requestCode,text);
            Toast.makeText(this,"success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "failer", Toast.LENGTH_SHORT).show();

    }
}
