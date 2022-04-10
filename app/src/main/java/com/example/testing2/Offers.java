package com.example.testing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Offers extends AppCompatActivity {
    ListView listOffers;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        listOffers=findViewById(R.id.offersList);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        ArrayList<String> arrayOffers = new ArrayList<>();
        ArrayAdapter adapterOffers = new ArrayAdapter<String>(this,R.layout.list_item,arrayOffers);
        listOffers.setAdapter(adapterOffers);

        DatabaseReference refOffers = FirebaseDatabase.getInstance().getReference().child("Coupons");
        refOffers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayOffers.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Coupons cop = dataSnapshot.getValue(Coupons.class);
                    String stringCop = cop.getBrand()+ " coupons for " + cop.getCoins() + " Veez points";
                    arrayOffers.add(stringCop);
                }
                adapterOffers.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        listOffers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    /*DatabaseReference refCoins = FirebaseDatabase.getInstance().getReference("Coupons/" + "/coupons1" + "/coins");
                    DatabaseReference refPoints = FirebaseDatabase.getInstance().getReference("users/" + userId + "/points_addition");
                    refPoints.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String stringPoints = snapshot.getValue().toString();
                            int points = Integer.parseInt(stringPoints);
                            refCoins.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String stringCoin = snapshot.getValue().toString();
                                    int coin = Integer.parseInt(stringCoin);

                                    if (points >= coin) {*/
                                        Intent intent = new Intent(view.getContext(), Offer1.class);
                                        startActivity(intent);
                                        //startActivity(new Intent(getApplicationContext(),Offer1.class));

                                    }
                                    //arrayOffers.remove(0);
                                    /*else {
                                        Toast.makeText(Offers.this, "You don't have sufficient Veez points.", Toast.LENGTH_SHORT).show();
                                    }
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }*/


                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), Offer2.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(view.getContext(), Offer3.class);
                    startActivity(intent);
                }


            }
        });
    }
}







