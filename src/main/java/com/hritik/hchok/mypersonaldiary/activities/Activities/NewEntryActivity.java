package com.hritik.hchok.mypersonaldiary.activities.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.hritik.hchok.mypersonaldiary.R;

import Data.DatabaseHandler;
import Model.Diary;

public class NewEntryActivity extends AppCompatActivity {

  //  private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private AlertDialog.Builder dialogueBuilder;
    private  AlertDialog dialog;
  //  private TextInputEditText cust_sno;
    private TextInputEditText cust_name;
    private TextInputEditText cust_address;
    private TextInputEditText cust_product;
    private TextInputEditText cust_quantity;
    private TextInputEditText cust_price;
    private TextInputEditText cust_paid;
    private TextInputEditText cust_balance;
    private TextInputEditText cust_gaurantee;
    private TextInputEditText cust_installment;
    private AppCompatButton saveButton;
    private DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_entry);

        db = new DatabaseHandler(this);
     //   byPassActivity();
       // toolbar=(Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floating_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(NewEntryActivity.this,"floating button clicked",Toast.LENGTH_LONG).show();

                createPopUpDialogue();
            }
        });


    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//     //   getMenuInflater().inflate(R.menu.main_menu,menu);
//       // MenuItem searchItem = menu.findItem(R.id.search_id);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        int id=item.getItemId();
////        if(id==R.id.item1_id){
////            Intent intent=new Intent(NewEntryActivity.this,secondActivity.class);
////            startActivity(intent);
////            super.finish();
////        }
////         else if(id==R.id.search_id){
////
////                Toast.makeText(NewEntryActivity.this,"search clicked",Toast.LENGTH_LONG).show();
////
////        }
////            else if(id==R.id.help){
////            Toast.makeText(NewEntryActivity.this,"We don't help nobody",Toast.LENGTH_LONG).show();
////        }
////        else if(id==R.id.contact_us){
////            Toast.makeText(NewEntryActivity.this,"Sup Nigga !!",Toast.LENGTH_LONG).show();
////        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    private void createPopUpDialogue(){
        dialogueBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialogue_layout,null);
        cust_name=(TextInputEditText)view.findViewById(R.id.dialogue_custname);
        cust_address=(TextInputEditText)view.findViewById(R.id.dialogue_custaddress);
        cust_product=(TextInputEditText)view.findViewById(R.id.dialogue_custproduct);
        cust_quantity=(TextInputEditText)view.findViewById(R.id.dialogue_custquantity);
        cust_price=(TextInputEditText)view.findViewById(R.id.dialogue_custprice);
        cust_paid=(TextInputEditText)view.findViewById(R.id.dialogue_custpaid);
        cust_balance=(TextInputEditText)view.findViewById(R.id.dialogue_custpayment_left);
        cust_gaurantee=(TextInputEditText)view.findViewById(R.id.dialogue_custgaurantee);
        cust_installment=(TextInputEditText)view.findViewById(R.id.dialogue_custnext_installment);
        saveButton=(AppCompatButton) view.findViewById(R.id.saveButton);
        dialogueBuilder.setView(view);
        dialog = dialogueBuilder.create();
        dialog.show();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cust_name.getText().toString().trim().isEmpty()
                        && ! cust_address.getText().toString().trim().isEmpty()
                        && !cust_product.getText().toString().trim().isEmpty()
                        &&!cust_quantity.getText().toString().trim().isEmpty()
                        &&!cust_price.getText().toString().trim().isEmpty()
                        &&!cust_paid.getText().toString().trim().isEmpty()
                        &&!cust_balance.getText().toString().trim().isEmpty()
                        &&!cust_gaurantee.getText().toString().trim().isEmpty()
                        &&!cust_installment.getText().toString().trim().isEmpty()) {

                      saveDiaryTodb(v);

                    // Snackbar.make(v, "entry saved !", Snackbar.LENGTH_LONG).show();

                }
            }

        });
    }

    private void saveDiaryTodb(View v){

        Diary diary = new Diary();

        String newName= cust_name.getText().toString();
        String newAddress=cust_address.getText().toString();
        String newProduct= cust_product.getText().toString();
        String newQuantity= cust_quantity.getText().toString();
        String newPrice= cust_price.getText().toString();
        String newPaid= cust_paid.getText().toString();
        String newBalance= cust_balance.getText().toString();
        String newGaurantee= cust_gaurantee.getText().toString();
        String newInstallment= cust_installment.getText().toString();


        diary.setName(newName);
        diary.setAddress(newAddress);
        diary.setProduct(newProduct);
        diary.setQuantity(newQuantity);
        diary.setPrice(newPrice);
        diary.setPaid(newPaid);
        diary.setBalance(newBalance);
        diary.setWarranty(newGaurantee);
        diary.setInstallment(newInstallment);

        db.addDiary(diary);

        Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_LONG).show();
        // Log.d("item ADDED :",String.valueOf(db.getDiaryCount() ));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                //start a new activity
                startActivity(new Intent(NewEntryActivity.this, ListActivity.class));
            }
        }, 1200);

    }



    }
