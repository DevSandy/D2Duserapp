package com.iscript.d2duserapp.ui.profile;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iscript.d2duserapp.R;
import com.iscript.d2duserapp.aboutus;
import com.iscript.d2duserapp.contactus;
import com.iscript.d2duserapp.edit_profile;
import com.iscript.d2duserapp.privacy_policy;
import com.iscript.d2duserapp.ui.home.HomeViewModel;
import com.squareup.picasso.Picasso;

public class profile extends Fragment {

    private ProfileViewModel mViewModel;
    private CardView profile;
    private TextView name,phone,mail;
    private ImageView image;
    private Object ProfileViewModel;
    FirebaseStorage storage;
    StorageReference storageReference;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    DatabaseReference myRef = database.getReference("profiles").child(uid);


    public static profile newInstance() {
        return new profile();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProfileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.profile_fragment, container, false);


        profile = root.findViewById(R.id.edprof);
        name=root.findViewById(R.id.prof_name);
        phone=root.findViewById(R.id.prof_contact);
        mail=root.findViewById(R.id.prof_mail);
        image=root.findViewById(R.id.prof_img);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mAuth = FirebaseAuth.getInstance();


        try
        {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String jphone = dataSnapshot.child("Phone").getValue().toString();
                    String jemail = dataSnapshot.child("Email").getValue().toString();
                    mail.setText(jemail);
                    String jname = dataSnapshot.child("Name").getValue().toString();
                    name.setText(jname);
                    phone.setText(jphone);
                    String jpoto = dataSnapshot.child("photoUrl").getValue().toString();
                    Picasso.with(getContext())
                            .load(jpoto)
                            .fit()
                            .centerInside()
                            .into(image);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e) {


        }



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), edit_profile.class);
                startActivity(intent);
            }
        });
        profile=root.findViewById(R.id.edcon);
    profile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), contactus.class);
            startActivity(intent);
        }
    });
        profile=root.findViewById(R.id.edpol);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), privacy_policy.class);
                startActivity(intent);
            }
        });
        profile=root.findViewById(R.id.edabt);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), aboutus.class);
                startActivity(intent);
            }
        });

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        // TODO: Use the ViewModel
    }

}
