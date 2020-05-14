package com.iscript.d2duserapp.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.iscript.d2duserapp.R;
import com.iscript.d2duserapp.grocerylistactivity;
import com.iscript.d2duserapp.ui.home.Teacher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static java.lang.String.*;


public  class RecyclerAdapterrr extends RecyclerView.Adapter<RecyclerAdapterrr.RecyclerViewHolder>{
    private grocerylistactivity mContext;
    private List<Teacher> teachers;
    private OnItemClickListener mListener;
    private ElegantNumberButton btn1;



    public RecyclerAdapterrr(grocerylistactivity context, List<Teacher> uploads) {
        mContext = context;
        teachers = uploads;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_modell, parent, false);



        return new RecyclerViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final  Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.descriptionTextView.setText(currentTeacher.getDescription());
        holder.dateTextView.setText(getDateToday());

        holder.btn1.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String num = valueOf(newValue);
                //Toast.makeText(mContext, num, Toast.LENGTH_SHORT).show();
                String ItemName = currentTeacher.getName();
                String qty = num;
                Intent intent = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intent.putExtra("quantity",qty);
                intent.putExtra("item",ItemName);
                intent.putExtra("storename",holder.nameTextView.getText().toString());
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });

//        Picasso.with(mContext)
//                .load(currentTeacher.getImageUrl())
////                .placeholder(R.drawable.placeholder)
//                .fit()
//                .centerCrop()
//                .into(holder.teacherImageView);
        Glide.with(mContext)
                .asBitmap()
                .load(currentTeacher.getImageUrl())
                .into(holder.teacherImageView);





    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView nameTextView,descriptionTextView,dateTextView;
        public ImageView teacherImageView;
        public ElegantNumberButton btn1;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.nameTextView );
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            teacherImageView = itemView.findViewById(R.id.teacherImageView);
            btn1=itemView.findViewById(R.id.number_button);
//            btn1.setOnClickListener(new ElegantNumberButton.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String num = btn1.getNumber();
//                    Toast.makeText(mContext, num, Toast.LENGTH_SHORT).show();
//
//
//                }
//            });


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem showItem = menu.add( Menu.NONE, 1, 1, "Show");
            MenuItem deleteItem = menu.add(Menu.NONE, 2, 2, "Delete");

            showItem.setOnMenuItemClickListener(this);
            deleteItem.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            mListener.onShowItemClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteItemClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onShowItemClick(int position);
        void onDeleteItemClick(int position);
    }

    public void setOnItemClickListener(grocerylistactivity listener) {
        mListener = listener;
    }
    private String getDateToday(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date=new Date();
        String today= dateFormat.format(date);
        return today;
    }
}
