package com.hritik.hchok.mypersonaldiary.activities.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hritik.hchok.mypersonaldiary.R;

import Data.DatabaseHandler;
import Model.Diary;
import UI.RecyclerViewAdapter;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView detail_name;
    private Context context;
    private TextView detail_address;
    private TextView detail_product;
    private TextView detail_quantity;
    private TextView detail_price;
    private TextView detail_paid;
    private TextView detail_balance;
    private TextView detail_warranty;
    private TextView detail_installment;
    private TextView detail_date_added;
    private Button editButtondetail;
    private Button deleteButtondetail;
    private DatabaseHandler db;
    private Button noButton;
    private Button yesButton;
    public int diaryID;
    private AlertDialog.Builder alertDialogueBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        db = new DatabaseHandler(this);
        detail_name = (TextView) findViewById(R.id.item_detail_name);
        detail_address = (TextView) findViewById(R.id.item_detail_address);
        detail_product = (TextView) findViewById(R.id.item_detail_product);
        detail_quantity = (TextView) findViewById(R.id.item_detail_quantity);
        detail_price = (TextView) findViewById(R.id.item_detail_price);
        detail_paid = (TextView) findViewById(R.id.item_detail_paid);
        detail_balance = (TextView) findViewById(R.id.item_detail_balance);
        detail_warranty = (TextView) findViewById(R.id.item_detail_warranty);
        detail_installment = (TextView) findViewById(R.id.item_detail_installment);
        detail_date_added = (TextView) findViewById(R.id.item_detail_date_added);
        editButtondetail = (Button) findViewById(R.id.edit_button_detail);
        deleteButtondetail = (Button) findViewById(R.id.delete_button_detail);
        noButton = (Button) findViewById(R.id.noButton);
        yesButton = (Button) findViewById(R.id.yesButton);

        editButtondetail.setOnClickListener(this);
        deleteButtondetail.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detail_name.setText(bundle.getString("name"));
            detail_address.setText(bundle.getString("address"));
            detail_product.setText(bundle.getString("product"));
            detail_quantity.setText(bundle.getString("quantity"));
            detail_price.setText(bundle.getString("price"));
            detail_paid.setText(bundle.getString("paid"));
            detail_balance.setText(bundle.getString("balance"));
            detail_warranty.setText(bundle.getString("warranty"));
            detail_installment.setText(bundle.getString("installment"));
            detail_date_added.setText(bundle.getString("date"));
            diaryID = bundle.getInt("id");
        }

    }

//
//               }
//       noButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               dialog.dismiss();
//           }
//       });
//
//       yesButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               //delete the item.
//               int deletedRows =db.deleteDiary(diaryID);
//               if(deletedRows > 0){
//                   Toast.makeText(DetailsActivity.this,"deleted",Toast.LENGTH_SHORT).show();
////               //    startActivity(new Intent(DetailsActivity.this,RecyclerViewAdapter.class));
////
////                   dialog.dismiss();
////
////
////           }
////       }} );
//
//   }
////        else {
////            Toast.makeText(DetailsActivity.this,"not deleted",Toast.LENGTH_SHORT).show();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_button_detail:
                //dialog.dismiss();
                break;
            case R.id.delete_button_detail:
                deleteFromDiary(diaryID);
                break;
        }
    }


    public void deleteFromDiary(final int diaryID) {

        alertDialogueBuilder = new AlertDialog.Builder(context);
//
        inflater = LayoutInflater.from(context);
//
        final View view = inflater.inflate(R.layout.confirmation_dialogue, null);
        alertDialogueBuilder.setView(view);
        dialog = alertDialogueBuilder.create();
        dialog.show();
//
//
//                   dialog.dismiss();
        int deletedRows = db.deleteDiary(diaryID);
        if (deletedRows > 0) {
            Toast.makeText(DetailsActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DetailsActivity.this, RecyclerViewAdapter.class));
        }

    }

}




