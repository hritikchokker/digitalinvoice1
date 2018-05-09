package com.hritik.hchok.mypersonaldiary.activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hritik.hchok.mypersonaldiary.R;

import java.util.ArrayList;
import java.util.List;

import Data.DatabaseHandler;
import Model.Diary;
import UI.RecyclerViewAdapter;

public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Diary> diaryList;
    private Toolbar toolbar;
    private List<Diary> listItems;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not Now", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        db = new DatabaseHandler(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        diaryList=new ArrayList<>();
        listItems=new ArrayList<>();
        //get items from database
        diaryList = db.getALLDiary();

        for(Diary d : diaryList){
            Diary diary=new Diary();
            diary.setName("" + d.getName());
            diary.setAddress("" + d.getAddress());
            diary.setProduct("" + d.getProduct());
            diary.setQuantity("" + d.getQuantity());
            diary.setPrice("" + d.getPrice());
            diary.setPaid("" + d.getPaid());
            diary.setBalance("" + d.getBalance());
            diary.setWarranty("" + d.getWarranty());
            diary.setInstallment("" + d.getInstallment());
            diary.setDateAdded("" + d.getDateAdded());
            diary.setID(d.getID());
            listItems.add(diary);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(this,listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_id);
        SearchView searchItem = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchItem.setOnQueryTextListener(this);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();

            if(id==R.id.entry_recycler_menu){

                Intent intent=new Intent(ListActivity.this,NewEntryActivity.class);
                startActivity(intent);
                super.finish();

        }  else if(id==R.id.item1_id){
            Intent intent=new Intent(ListActivity.this,secondActivity.class);
            startActivity(intent);
            super.finish();
        }
         else if(id==R.id.search_id){

                Toast.makeText(ListActivity.this,"search clicked",Toast.LENGTH_LONG).show();

        }
            else if(id==R.id.help){
            Toast.makeText(ListActivity.this,"We don't help nobody",Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.contact_us){
            Toast.makeText(ListActivity.this,"Sup Nigga !!",Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText=newText.toLowerCase();
        ArrayList<Diary> newList = new ArrayList<>();
        for (Diary diary : diaryList){
            String name = diary.getName().toLowerCase();
            if(name.contains(newText))
                newList.add(diary);
        }
        recyclerViewAdapter.setFilter(newList);
        return true;
    }
}
