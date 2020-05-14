package com.iscript.d2duserapp;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iscript.d2duserapp.Adapter.CartRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;



public class Cart extends AppCompatActivity implements CartRecyclerAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private CartRecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<CartModel> mCartModels;

    private void openDetailActivity(String[] data){
//        Intent intent = new Intent(this, DetailsActivity.class);
//        intent.putExtra("NAME_KEY",data[0]);
//        intent.putExtra("DESCRIPTION_KEY",data[1]);
//        intent.putExtra("IMAGE_KEY",data[2]);
//        intent.putExtra("PRICE_KEY",data[3]);
//        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        mRecyclerView = findViewById(R.id.mRecyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mCartModels = new ArrayList<>();
        mAdapter = new CartRecyclerAdapter (Cart.this, mCartModels);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(Cart.this);

        mStorage = FirebaseStorage.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users/"+uid+"/cache");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mCartModels.clear();

                for (DataSnapshot CartModelSnapshot : dataSnapshot.getChildren()) {
                    CartModel upload = CartModelSnapshot.getValue(CartModel.class);
                    upload.setKey(CartModelSnapshot.getKey());



                    mCartModels.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Cart.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
    public void onItemClick(int position) {
        CartModel clickedCartModel=mCartModels.get(position);
        String[] CartModelData={clickedCartModel.getName(),clickedCartModel.getDescription(),clickedCartModel.getImageUrl(),clickedCartModel.getPrice()};
        openDetailActivity(CartModelData);
    }

    @Override
    public void onShowItemClick(int position) {
        CartModel clickedCartModel=mCartModels.get(position);
        String[] CartModelData={clickedCartModel.getName(),clickedCartModel.getDescription(),clickedCartModel.getImageUrl()};
        openDetailActivity(CartModelData);
    }

    @Override
    public void onDeleteItemClick(int position) {
        CartModel selectedItem = mCartModels.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(Cart.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

}


