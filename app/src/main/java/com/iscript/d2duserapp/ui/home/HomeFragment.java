package com.iscript.d2duserapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iscript.d2duserapp.Adapter.RecyclerAdapter;
import com.iscript.d2duserapp.R;
import com.iscript.d2duserapp.RecyclerViewAdapter;
import com.iscript.d2duserapp.appliance;
import com.iscript.d2duserapp.car_service;
import com.iscript.d2duserapp.courier_package;
import com.iscript.d2duserapp.fruitstore;
import com.iscript.d2duserapp.groceriesstore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseReff;

    private ValueEventListener mDBListener;
    private ValueEventListener mdbl;
    private CardView groceriescard,fruitcard,carservice,couriercard,appliancecard;

    private LinearLayout linearcard;
    private List<Teacher> mTeachers;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Homebanners");

    public DatabaseReference getMyRef() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                url1 = dataSnapshot.child("url1").getValue().toString();
                url2 = dataSnapshot.child("url2").getValue().toString();
                url3= dataSnapshot.child("url3").getValue().toString();
                url4= dataSnapshot.child("url4").getValue().toString();
                url5= dataSnapshot.child("url5").getValue().toString();
                initImages();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return myRef;
    }

    private String url1,url2,url3,url4,url5;


//    private void openDetailActivity(String[] data){
//        Intent intent = new Intent(this, DetailsActivity.class);
//        intent.putExtra("NAME_KEY",data[0]);
//        intent.putExtra("DESCRIPTION_KEY",data[1]);
//        intent.putExtra("IMAGE_KEY",data[2]);
//        startActivity(intent);
//    }

    private ArrayList<String> ImageUrls = new ArrayList<>();
    private ArrayList<String> ImageNames = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        linearcard = root.findViewById(R.id.linearcards);

        linearcard.setVisibility(View.GONE);


        getMyRef();



        mRecyclerView =root.findViewById(R.id.mRecyclerView);
        groceriescard = root.findViewById(R.id.groceriescard);
        couriercard = root.findViewById(R.id.couriercard);

        carservice = root.findViewById(R.id.carcard);
        fruitcard = root.findViewById(R.id.fruitcard);
        appliancecard =root.findViewById(R.id.appliancecard);

        appliancecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), appliance.class);
                startActivity(intent);
            }
        });
        fruitcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), fruitstore.class);
                startActivity(intent);
            }
        });

        carservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), car_service.class);
                startActivity(intent);
            }
        });

        couriercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), courier_package.class);
                startActivity(intent);
            }
        });


        groceriescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), groceriesstore.class);
                startActivity(intent);
            }
        });


//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager (getActivity()));

//        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
//        mProgressBar.setVisibility(View.VISIBLE);

        mTeachers = new ArrayList<> ();
        mAdapter = new RecyclerAdapter (HomeFragment.this, mTeachers);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener((RecyclerAdapter.OnItemClickListener) HomeFragment.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Recomendations");
        mDatabaseReff = FirebaseDatabase.getInstance().getReference("Homebanners");


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



        return root;
    }
    private void initImages(){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        final RecyclerView recyclerView =getView().findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ImageUrls, ImageNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearcard.setVisibility(View.VISIBLE);


        ImageUrls.add(url1);
        ImageNames.add("Happy Vinayaka");

        ImageUrls.add(url2);
        ImageNames.add("Hot Dogs");

        ImageUrls.add(url3);
        ImageNames.add("Restaurants");


        ImageUrls.add(url4);
        ImageNames.add("Pani Puri");

        ImageUrls.add(url5);
        ImageNames.add("KFC Chicken");


    }
    public void onItemClick(int position) {
        Teacher clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl()};
//        openDetailActivityivity(teacherData);
    }

    public void onShowItemClick(int position) {
        Teacher clickedTeacher=mTeachers.get(position);
        String[] teacherData={clickedTeacher.getName(),clickedTeacher.getDescription(),clickedTeacher.getImageUrl()};
//        openDetailActivity(teacherData);
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
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }


}