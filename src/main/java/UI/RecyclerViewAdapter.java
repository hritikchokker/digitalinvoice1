package UI;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hritik.hchok.mypersonaldiary.R;
import com.hritik.hchok.mypersonaldiary.activities.Activities.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import Data.DatabaseHandler;
import Model.Diary;

/**
 * Created by hchok on 29-01-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    public List<Diary> diaryItems;

    private int diaryID;
    private AlertDialog.Builder alertDialogueBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private DatabaseHandler db;

    public RecyclerViewAdapter(Context context, List<Diary> diaryItems) {
        this.context = context;
        this.diaryItems = diaryItems;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        Diary diary =diaryItems.get(position);

        holder.diarycustname.setText(diary.getName());
        holder.diarycustaddress.setText(diary.getAddress());
        holder.diarycustproduct.setText(diary.getProduct());
        holder.diarycustquantity.setText(diary.getQuantity());
        holder.diarycustprice.setText(diary.getPrice());
        holder.diarycustpaid.setText(diary.getPaid());
        holder.diarycustbalance.setText(diary.getBalance());
        holder.diarycustwarranty.setText(diary.getWarranty());
        holder.diarycustinstallment.setText(diary.getInstallment());
        holder.diarycustdateadded.setText(diary.getDateAdded());

    }

    @Override
    public int getItemCount() {
        return diaryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       final   public TextView diarycustname;
        final  public TextView diarycustaddress;
        final  public TextView diarycustproduct;
        final   public TextView diarycustquantity;
        final    public TextView diarycustprice;
        final  public TextView diarycustpaid;
        final  public TextView diarycustbalance;
        final   public TextView diarycustwarranty;
        final   public TextView diarycustinstallment;
        final  public TextView diarycustdateadded;
        final    public Button editButtondetail;
        final   public Button deleteButtondetail;
      // public CardView cardView;
        public int id;

        public ViewHolder(View view,Context ctx){
            super(view);
            context = ctx;

           diarycustname =(TextView) view.findViewById(R.id.recycler_name);
            diarycustaddress =(TextView) view.findViewById(R.id.recyler_address);
            diarycustproduct =(TextView) view.findViewById(R.id.recycler_product);
            diarycustquantity=(TextView) view.findViewById(R.id.recycler_quantity);
            diarycustprice=(TextView) view.findViewById(R.id.recycler_price);
            diarycustpaid=(TextView) view.findViewById(R.id.recycler_paid);
            diarycustbalance=(TextView) view.findViewById(R.id.recycler_balance);
            diarycustwarranty=(TextView) view.findViewById(R.id.recycler_warranty);
            diarycustinstallment=(TextView) view.findViewById(R.id.recycler_installment);
            diarycustdateadded =(TextView) view.findViewById(R.id.recycler_date_added);

            editButtondetail = (Button) view.findViewById(R.id.edit_button_detail);
            deleteButtondetail = (Button) view.findViewById(R.id.delete_button_detail);

            editButtondetail.setOnClickListener(this);
            deleteButtondetail.setOnClickListener(this);

            //  cardView=(CardView)view.findViewById(R.id.cardView);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //go to next screen
//                    int position = getAdapterPosition();
//                    Diary diary = diaryItems.get(position);
//                    Intent intent = new Intent(context, DetailsActivity.class);
//                    intent.putExtra("name",diary.getName());
//                    intent.putExtra("address",diary.getAddress());
//                    intent.putExtra("product",diary.getProduct());
//                    intent.putExtra("quantity",diary.getQuantity());
//                    intent.putExtra("price",diary.getPrice());
//                    intent.putExtra("paid",diary.getPaid());
//                    intent.putExtra("balance",diary.getBalance());
//                    intent.putExtra("warranty",diary.getWarranty());
//                    intent.putExtra("installment",diary.getInstallment());
//                    intent.putExtra("date",diary.getDateAdded());
//                    intent.putExtra("id",diary.getID());
//                    context.startActivity(intent);
//                }
//            });
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.edit_button_detail:
                    int position = getAdapterPosition();
                    Diary diary = diaryItems.get(position);
                    Snackbar.make(view, "edit button clicked", Snackbar.LENGTH_LONG).show();
                   editItem(diary);

                    break;
                case R.id.delete_button_detail:
                    position = getAdapterPosition();
                    diary = diaryItems.get(position);
                    Snackbar.make(view, "delete button clicked", Snackbar.LENGTH_LONG).show();
                    deleteItem(diary.getID());

                    break;


            }
        }

        public void deleteItem(final int id) {

            //create an AlertDialog
            alertDialogueBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
          final   View view = inflater.inflate(R.layout.confirmation_dialogue, null);

            Button noButton = (Button) view.findViewById(R.id.noButton);
            Button yesButton = (Button) view.findViewById(R.id.yesButton);

            alertDialogueBuilder.setView(view);
            dialog = alertDialogueBuilder.create();
            dialog.show();


            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //delete the item.
                    DatabaseHandler db = new DatabaseHandler(context);
                    //delete item
                    db.deleteDiary(id);
                    Snackbar.make(view, "deleted", Snackbar.LENGTH_LONG).show();
                    diaryItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();


                }
            });

        }

        public void editItem(final Diary diary) {

            alertDialogueBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.dialogue_layout, null);

       final    TextView  dialogue_name=(TextInputEditText)view.findViewById(R.id.dialogue_custname);
            final    TextView  dialogue_address=(TextInputEditText)view.findViewById(R.id.dialogue_custaddress);
            final       TextView  dialogue_product=(TextInputEditText)view.findViewById(R.id.dialogue_custproduct);
            final     TextView  dialogue_quantity =(TextInputEditText)view.findViewById(R.id.dialogue_custquantity);
            final    TextView  dialogue_price=view.findViewById(R.id.dialogue_custprice);
            final    TextView   dialogue_paid=(TextInputEditText)view.findViewById(R.id.dialogue_custpaid);
            final   TextView   dialogue_balance=(TextInputEditText)view.findViewById(R.id.dialogue_custpayment_left);
            final    TextView dialogue_warranty=(TextInputEditText)view.findViewById(R.id.dialogue_custgaurantee);
            final   TextView   dialogue_installment=(TextInputEditText)view.findViewById(R.id.dialogue_custnext_installment);

            Button saveButton = (Button) view.findViewById(R.id.saveButton);

            alertDialogueBuilder.setView(view);
            dialog = alertDialogueBuilder.create();
            dialog.show();

//            diary.setName(dialogue_name.getText().toString());
//            diary.setAddress(dialogue_address.getText().toString());
//            diary.setProduct(dialogue_product.getText().toString());
//            diary.setQuantity(dialogue_quantity.getText().toString());
//            diary.setPrice(dialogue_price.getText().toString());
//            diary.setPaid(dialogue_paid.getText().toString());
//            diary.setBalance(dialogue_balance.getText().toString());
//            diary.setWarranty(dialogue_warranty.getText().toString());
//            diary.setInstallment(dialogue_installment.getText().toString());
//            diary.setDateAdded(diarycustdateadded.getText().toString());

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHandler db = new DatabaseHandler(context);

                 //   Update item
                    diary.setName(dialogue_name.getText().toString());
                    diary.setAddress(dialogue_address.getText().toString());
                    diary.setProduct(dialogue_product.getText().toString());
                    diary.setQuantity(dialogue_quantity.getText().toString());
                    diary.setPrice(dialogue_price.getText().toString());
                    diary.setPaid(dialogue_paid.getText().toString());
                    diary.setBalance(dialogue_balance.getText().toString());
                    diary.setWarranty(dialogue_warranty.getText().toString());
                    diary.setInstallment(dialogue_installment.getText().toString());
                    diary.setDateAdded(diarycustdateadded.getText().toString());


                    if (!dialogue_name.getText().toString().isEmpty()
                            && ! dialogue_address.getText().toString().isEmpty()
                            && ! dialogue_product.getText().toString().isEmpty()
                            && ! dialogue_quantity.getText().toString().isEmpty()
                            && ! dialogue_price.getText().toString().isEmpty()
                            && ! dialogue_paid.getText().toString().isEmpty()
                            && ! dialogue_balance.getText().toString().isEmpty()
                            && ! dialogue_warranty.getText().toString().isEmpty()
                            && ! dialogue_installment.getText().toString().isEmpty()

                            ) {
                        db.updateDiary(diary);
                        notifyItemChanged(getAdapterPosition(),diary);
                    }else {
                        Snackbar.make(view, "Add All the values", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();

                }
            });

        }

    }

    public void setFilter(ArrayList<Diary> newList){
        diaryItems= new ArrayList<>();
        diaryItems.addAll(newList);
        notifyDataSetChanged();
    }

}
