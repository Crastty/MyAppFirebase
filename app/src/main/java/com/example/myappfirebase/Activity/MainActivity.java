package com.example.myappfirebase.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myappfirebase.Adapter.MyAdapter;
import com.example.myappfirebase.Firebase.MyFirebase;
import com.example.myappfirebase.Model.MyModel;
import com.example.myappfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCell;
    Button btAdd, btMove, btDelete, btLogOut;
    EditText etNombre, etApellido;
    DatabaseReference myRef;
    MyModel mDatos;
    ArrayList<MyModel> myModelArrayList = new ArrayList<>();
    MyAdapter mAdapter = new MyAdapter(myModelArrayList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        String uuid = getIntent().getExtras().getString("uuid");

        myRef = database.getReference(uuid);

        rvCell = findViewById(R.id.myReciclerView);
        btAdd = findViewById(R.id.btAdd);
        btMove = findViewById(R.id.btMove);
        btDelete = findViewById(R.id.btDelete);
        etNombre = findViewById(R.id.etName);
        etApellido = findViewById(R.id.etApellido);
        btLogOut = findViewById(R.id.btLogOut);


        rvCell.setLayoutManager(new LinearLayoutManager(this));
        rvCell.setAdapter(mAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatos = new MyModel();
                mDatos.setNombre(etNombre.getText().toString());
                mDatos.setApellido(etApellido.getText().toString());
                MyFirebase firebase = new MyFirebase();
                firebase.addFirebase(mDatos, myRef);
            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.getKey().equals("message")) {
                        myModelArrayList = new ArrayList<>();
                        for (DataSnapshot newSnapshot : postSnapshot.getChildren()) {
                            MyModel recoveredData = newSnapshot.getValue(MyModel.class);
                            myModelArrayList.add(recoveredData);

                        }
                        mAdapter.loadData(myModelArrayList);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.e("onCancelled", "Failed to read value.");
            }
        });

        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }



}
