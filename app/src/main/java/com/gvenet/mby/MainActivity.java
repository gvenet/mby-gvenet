package com.gvenet.mby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gvenet.mby.DAO.ItemDAO;
import com.gvenet.mby.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_LIST = "items";
    List<Item> itemList = new ArrayList<>();

    String txtValue;
    Context context;
    ListView mListView;
    ItemDAO itemDAO;
    ArrayAdapter<String> adapter;
    private RecyclerView rvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);

        rvItems = findViewById(R.id.rvItem);

        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        rvItems.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvItems.setLayoutManager(layoutManager);
        rvItems.setAdapter(itemAdapter);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                Intent intent = new Intent(context, AddItemActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        context = getApplicationContext();

        itemDAO = new ItemDAO(context);
        itemList = itemDAO.List();
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        rvItems.setAdapter(itemAdapter);
        Log.d("", String.valueOf(itemList));
    }


}