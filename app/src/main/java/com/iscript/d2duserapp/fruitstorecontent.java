package com.iscript.d2duserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.iscript.d2duserapp.Adapter.RecyclerAdapterrrrr;
import com.iscript.d2duserapp.ui.home.Teacher;

import java.util.ArrayList;
import java.util.List;

public class fruitstorecontent extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapterrrrr mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    //    private ElegantNumberButton btn1;
    public TextView textView;
    private Button add_cart;
    private String name;

    private FirebaseAuth mAuth;


//    FirebaseUser currentUser = mAuth.getCurrentUser();

    //    String uid = currentUser.getUid().toString();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uid;

    private FirebaseAuth mauth;


    private List<Teacher> mTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitstorecontent);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
//        Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        add_cart=(Button)findViewById(R.id.add_cart);
        Intent i=this.getIntent();
        name=i.getExtras().getString("NAME_KEY");
        final String description=i.getExtras().getString("DESCRIPTION_KEY");
        final String imageURL=i.getExtras().getString("IMAGE_KEY");
        final String childkey=i.getExtras().getString("CHILD_KEY");
        final String catogery = i.getExtras().getString("catogery");

        uid = i.getExtras().getString("uid");


        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fruitstorecontent.this, Cart.class);
                startActivity(intent);
            }
        });


        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
//        mProgressBar.setVisibility(View.VISIBLE);

        mTeachers = new ArrayList<>();
        mAdapter = new RecyclerAdapterrrrr (fruitstorecontent.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener((RecyclerAdapterrrrr.OnItemClickListener) grocerylistactivity.this);
        mAdapter.setOnItemClickListener(fruitstorecontent.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Fruitstore").child(childkey).child("Fruitsandvej");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mTeachers.clear();

                for (DataSnapshot teacherSnapshot : dataSnapshot.getChildren()) {
                    Teacher upload = teacherSnapshot.getValue(Teacher.class);
                    upload.setKey(teacherSnapshot.getKey());
                    mTeachers.add(upload);
                }
                mAdapter.notifyDataSetChanged();
//                mProgressBar.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(ItemsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

//        btn1=(ElegantNumberButton)findViewById(R.id.number_button);
//        btn1.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
//            @Override
//            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
//                Toast.makeText(grocerylistactivity.this, newValue, Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String ItemName = intent.getStringExtra("item");
            String qty = intent.getStringExtra("quantity");
            String storename = intent.getStringExtra("storename");

            DatabaseReference myRef = database.getReference("Users").child(uid).child("cache");

            myRef.child(name).child(ItemName).setValue(qty);






        }
    };

//    public void onItemClick(int position) {
//        Teacher clickedTeacher=mTeachers.get(position);
//        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getKey()};
//        openDetailActivity(teacherData);
//    }
//
//    public void onShowItemClick(int position) {
//        Teacher clickedTeacher=mTeachers.get(position);
//        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl(),clickedTeacher.getKey()};
//        openDetailActivity(teacherData);
//    }

    public void onItemClick(int position) {

    }

    public void onShowItemClick(int position) {

    }

    public void onDeleteItemClick(int position) {
        Teacher selectedItem = mTeachers.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
//                Toast.makeText(ItemsActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

