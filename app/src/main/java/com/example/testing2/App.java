package com.example.testing2;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class App extends Application {

    //Config
    public static Config mConfig;

    //Beacon
    public static Config.Beacon mBeacon;

    @Override
    public void onCreate(){
        super.onCreate();
        /*DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("configuration");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    Config mConfig = snapshot.getValue(Config.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
