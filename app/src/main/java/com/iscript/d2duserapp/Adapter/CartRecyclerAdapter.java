package com.iscript.d2duserapp.Adapter;

import android.content.Context;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.iscript.d2duserapp.CartModel;
import com.iscript.d2duserapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public  class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerViewHolder>{
    private Context mContext;
    private List<CartModel> mcartmodels;
    private OnItemClickListener mListener;

    public CartRecyclerAdapter(Context context, List<CartModel> uploads) {
        mContext = context;
        mcartmodels = uploads;
    }

    @Override
    public CartRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cart_row_model, parent, false);
        return new CartRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CartRecyclerViewHolder holder, int position) {
        CartModel currentOrder = mcartmodels.get(position);
        holder.nameTextView.setText(currentOrder.getName());
        holder.supplimentTextView.setText(currentOrder.getDescription());
//        holder.dateTextView.setText(getDateToday());
        holder.priceTextView.setText(currentOrder.getPrice());
        Picasso.with(mContext)
                .load(currentOrder.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop()
                .into(holder.userImageView);

        Picasso.with(mContext)
                .load(currentOrder.getImageUrl())
                .error(R.drawable.placeholder)
                .into(holder.userImageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        // will load image
                    }

                    @Override
                    public void onError() {
                        // will not load image from url
                    }
                });
//        Toast.makeText(mContext, "url"+currentOrder.getImageUrl(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public int getItemCount() {
        return mcartmodels.size();
    }

    public class CartRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView nameTextView,supplimentTextView,dateTextView,priceTextView;
        public ImageView userImageView;

        public CartRecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.nameTextView );
            supplimentTextView = itemView.findViewById(R.id.supplimentTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            userImageView = itemView.findViewById(R.id.userImg);
            priceTextView = itemView.findViewById(R.id.priceTextView);

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

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
//    private String getDateToday(){
//        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
//        Date date=new Date();
//        String today= dateFormat.format(date);
//        return today;
//    }
}

