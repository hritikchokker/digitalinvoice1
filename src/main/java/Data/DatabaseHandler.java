package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Diary;
import Util.Constants;

/**
 * Created by hchok on 28-01-2018.
 */



public class DatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DIARY_TABLE = "CREATE TABLE " +  Constants.TABLE_NAME  + "( "
                +  Constants.KEY_ID  + " INTEGER PRIMARY KEY, "
                +  Constants.KEY_NAME  + " TEXT NOT NULL, "
                +  Constants.KEY_ADDRESS  + " TEXT NOT NULL, "
                +  Constants.KEY_PRODUCT  + " TEXT NOT NULL, "
                +  Constants.KEY_QUANTITY  + " TEXT NOT NULL, "
                +  Constants.KEY_PRICE  + " TEXT NOT NULL, "
                +  Constants.KEY_PAID + " TEXT NOT NULL, "
                +  Constants.KEY_BALANCE + " TEXT NOT NULL, "
                + Constants.KEY_WARRANTY + " TEXT NOT NULL,"
                +  Constants.KEY_INSTALLMENT + " TEXT NOT NULL, "
                +  Constants.KEY_DATE_ADDED  +  " LONG" + " );" ;

                  db.execSQL(CREATE_DIARY_TABLE);
    }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){



            db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

            onCreate(db);


        }
        public void addDiary(Diary diary){
            //ADD ENTRY TO DIARY
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
           // values.put(Constants.KEY_ID,diary.getID());
            values.put(Constants.KEY_NAME, diary.getName());
            values.put(Constants.KEY_ADDRESS,diary.getAddress());
            values.put(Constants.KEY_PRODUCT, diary.getProduct());
            values.put(Constants.KEY_QUANTITY, diary.getQuantity());
            values.put(Constants.KEY_PRICE, diary.getPrice());
            values.put(Constants.KEY_PAID, diary.getPaid());
            values.put(Constants.KEY_BALANCE, diary.getBalance());
            values.put(Constants.KEY_WARRANTY,diary.getWarranty());
            values.put(Constants.KEY_INSTALLMENT, diary.getInstallment());
           values.put(Constants.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

            db.insert(Constants.TABLE_NAME ,null, values);
            db.close();


            Log.d("Saved!!", "Saved to DB");

        }

        public Diary getDiary(int id){
            //GET A ENTRY FROM DIARY
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query(Constants.TABLE_NAME, new String[] { Constants.KEY_ID ,
                    Constants.KEY_NAME , Constants.KEY_ADDRESS , Constants.KEY_PRODUCT , Constants.KEY_QUANTITY , Constants.KEY_PRICE ,
                    Constants.KEY_PAID , Constants.KEY_BALANCE , Constants.KEY_INSTALLMENT , Constants.KEY_DATE_ADDED
                    } ,Constants.KEY_ID + "=?" ,
                    new String[]{String.valueOf(id)},null,null,null,null);

            if(cursor != null)
                cursor.moveToFirst();
            Diary diary = new Diary();
            diary.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            diary.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            diary.setAddress(cursor.getString(cursor.getColumnIndex(Constants.KEY_ADDRESS)));
            diary.setProduct(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRODUCT)));
            diary.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QUANTITY)));
            diary.setPrice(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRICE)));
            diary.setPaid(cursor.getString(cursor.getColumnIndex(Constants.KEY_PAID)));
            diary.setBalance(cursor.getString(cursor.getColumnIndex(Constants.KEY_BALANCE)));
            diary.setInstallment(cursor.getString(cursor.getColumnIndex(Constants.KEY_INSTALLMENT)));
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ADDED)))
                    .getTime());

            diary.setDateAdded(formatedDate);
            cursor.close();

            return diary;
        }
        public List<Diary> getALLDiary(){
             //GET ALL ENTRY FROM DIARY
            SQLiteDatabase db = this.getReadableDatabase();

            List<Diary> diaryList = new ArrayList<>();
            Cursor cursor=db.query(Constants.TABLE_NAME,new String[]{
                    Constants.KEY_ID,Constants.KEY_NAME,Constants.KEY_ADDRESS,Constants.KEY_PRODUCT,
                    Constants.KEY_QUANTITY,Constants.KEY_PRICE,Constants.KEY_PAID,
                    Constants.KEY_BALANCE,Constants.KEY_WARRANTY,Constants.KEY_INSTALLMENT,Constants.KEY_DATE_ADDED
            }, null,null,null,null,Constants.KEY_DATE_ADDED + " DESC");

            if (cursor.moveToFirst()){
                do {
                    Diary diary =new Diary();
                    diary.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                    diary.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                    diary.setAddress(cursor.getString(cursor.getColumnIndex(Constants.KEY_ADDRESS)));
                    diary.setProduct(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRODUCT)));
                    diary.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QUANTITY)));
                    diary.setPrice(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRICE)));
                    diary.setPaid(cursor.getString(cursor.getColumnIndex(Constants.KEY_PAID)));
                    diary.setBalance(cursor.getString(cursor.getColumnIndex(Constants.KEY_BALANCE)));
                    diary.setWarranty(cursor.getString(cursor.getColumnIndex(Constants.KEY_WARRANTY)));
                    diary.setInstallment(cursor.getString(cursor.getColumnIndex(Constants.KEY_INSTALLMENT)));
                    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                    String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ADDED)))
                            .getTime());

                    diary.setDateAdded(formatedDate);
                    //ADD TO THE ARRAY LIST
                    diaryList.add(diary);

                }while(cursor.moveToNext());
            }
            cursor.close();

            return diaryList;
        }

        public int updateDiary(Diary diary){
            //UPDATE A ENTRY FROM DIARY
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constants.KEY_ID,diary.getID());
            values.put(Constants.KEY_NAME, diary.getName());
            values.put(Constants.KEY_ADDRESS,diary.getAddress());
            values.put(Constants.KEY_PRODUCT, diary.getProduct());
            values.put(Constants.KEY_QUANTITY, diary.getQuantity());
            values.put(Constants.KEY_PRICE, diary.getPrice());
            values.put(Constants.KEY_PAID, diary.getPaid());
            values.put(Constants.KEY_BALANCE, diary.getBalance());
            values.put(Constants.KEY_WARRANTY,diary.getWarranty());
            values.put(Constants.KEY_INSTALLMENT, diary.getInstallment());
            values.put(Constants.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

            return db.update(Constants.TABLE_NAME,values,Constants.KEY_ID + "=?" , new String[]{String.valueOf(diary.getID())});
        }
        public int deleteDiary(int id){
            SQLiteDatabase db = this.getWritableDatabase();
          return   db.delete(Constants.TABLE_NAME,Constants.KEY_ID + "=?",
                    new String[]{String.valueOf(id)});

        }
        public int getDiaryCount(){
            String countQuery=" SELECT * FROM " + Constants.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery,null);
            return cursor.getCount();
        }


    }

