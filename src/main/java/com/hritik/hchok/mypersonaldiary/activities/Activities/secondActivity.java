package com.hritik.hchok.mypersonaldiary.activities.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import Model.Diary;
import com.google.firebase.auth.FirebaseAuth;
import com.hritik.hchok.mypersonaldiary.R;

import Data.DatabaseHandler;

public class secondActivity extends AppCompatActivity {

    private Button newEntry;
    private Button viewEntry;
    private Button goBack;
    private TextView smartTextview;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseHandler db;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(secondActivity.this,"Log out First",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        db = new DatabaseHandler(secondActivity.this);


        newEntry=(Button)findViewById(R.id.newentry);
        viewEntry=(Button)findViewById(R.id.viewallentry);
        smartTextview = (TextView) findViewById(R.id.smart_diary_welcome);
        goBack=(Button)findViewById(R.id.mainpage);

        smartTextview.setTextColor(Color.parseColor("#FFFFFF"));



     newEntry=(Button)findViewById(R.id.newentry);
     newEntry.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             // Toast.makeText(secondActivity.this, "New entry clicked", Toast.LENGTH_LONG).show();
             Intent intent =new Intent(secondActivity.this,NewEntryActivity.class);
             startActivity(intent);
         }

     });
     goBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             mAuth.signOut();
             Intent intent=new Intent(secondActivity.this,MainActivity.class);
             startActivity(intent);
             Toast.makeText(secondActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
             secondActivity.super.finish();
         }
     });

     viewEntry.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             // Toast.makeText(secondActivity.this, "View ALL entry clicked", Toast.LENGTH_LONG).show();
             byPassActivity();
         }
     });

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent intent=new Intent(secondActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };




    }
      public void byPassActivity() {
   // Checks if database is empty; if not, then we just
   // go to ListActivity and show all added items


     if (db.getDiaryCount() > 0) {
            startActivity(new Intent(secondActivity.this, ListActivity.class));
            finish();
        }

      }


}



