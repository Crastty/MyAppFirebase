package com.example.myappfirebase.Firebase;


import com.example.myappfirebase.Model.MyModel;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyFirebase {


    public void addFirebase (MyModel mDatos, DatabaseReference myRef) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ddMMyyyy");
        String currentDateandTime = sdf.format(new Date());
        myRef.child("message").child(currentDateandTime).setValue(mDatos);

    }



}
