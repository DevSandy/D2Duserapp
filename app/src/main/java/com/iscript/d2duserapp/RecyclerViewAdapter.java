package com.iscript.d2duserapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iscript.d2duserapp.ui.home.HomeFragment;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private HomeFragment mcontext;

    public RecyclerViewAdapter(ArrayList<String> mImages, ArrayList<String> mNames, HomeFragment mcontext) {
        this.mImages = mImages;
        this.mNames = mNames;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Images Set");
        Glide.with(mcontext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.img);

        holder.txt.setText(mNames.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked");
//                Toast.makeText(mcontext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        RelativeLayout parent_layout;


        public ViewHolder( View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            txt = itemView.findViewById(R.id.text);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
