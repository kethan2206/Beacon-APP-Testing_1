package com.example.testing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Offer2 extends AppCompatActivity {
    Button couponButton;
    FirebaseAuth fAuth;
    String userId;
    TextView offer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer2);

        couponButton = findViewById(R.id.getCouponCode1);
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        offer2 = findViewById(R.id.offer2);

        DatabaseReference refOffer1Code = FirebaseDatabase.getInstance().getReference("Coupons/" +"/coupons2" +"/couponCode");
        refOffer1Code.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String code =snapshot.getValue().toString();
                offer2.setText(code);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        difference();
        visibility();


    }

    public void visibility(){
        DatabaseReference refCouponCode = FirebaseDatabase.getInstance().getReference("Coupons/" + "/coupons2" + "/couponCode");
        refCouponCode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String stringRefCouponCode = snapshot.getValue().toString();
                DatabaseReference refUserCouponCode = FirebaseDatabase.getInstance().getReference("users/" + userId + "/userCouponCode2");
                refUserCouponCode.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String stringRefUserCouponCode = snapshot.getValue().toString();
                        if (stringRefCouponCode.matches(stringRefUserCouponCode)){
                            couponButton.setVisibility(View.GONE);
                            offer2.setVisibility(View.VISIBLE);
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
    }

    public void difference(){
        DatabaseReference refCoins = FirebaseDatabase.getInstance().getReference("Coupons/"+"/coupons2"+"/coins");
        DatabaseReference refPoints = FirebaseDatabase.getInstance().getReference("users/"+userId+"/points_addition");
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

                        couponButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (points >= coin) {
                                    int newPoints = points - coin;
                                    refPoints.setValue(newPoints);
                                    DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("users/"+userId+"/userCouponCode2");
                                    DatabaseReference refCouponCode = FirebaseDatabase.getInstance().getReference("Coupons/" + "/coupons2" + "/couponCode");

                                    refCouponCode.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String stringRefCouponCode = snapshot.getValue().toString();
                                            myRef2.setValue(stringRefCouponCode);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                    //startActivity(new Intent(getApplicationContext(), Offer2CouponCode.class));
                                    //finish();
                                } else {
                                    Toast.makeText(Offer2.this, "You don't have sufficient Veez points.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
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
    }
}
