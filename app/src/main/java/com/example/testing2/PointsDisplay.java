package com.example.testing2;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.content.Context;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.testing2.databinding.LayoutPromotionBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firestore.v1.WriteResult;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


public class PointsDisplay  extends AppCompatActivity{

    private static final String TAG = "TAG";
    private Context mCx;
    //TextView points;
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore fStore;

    SplashScreenLauncherActivity splash;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }







    public PointsDisplay(Context cx){
        this.mCx = cx;


    }
    /*public class User {

        public int userPoints;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(int userPoints) {
            this.userPoints = userPoints;

        }

        public int getUserPoints() {
            return userPoints;
        }

        public void setUserPoints(int userPoints) {
            this.userPoints = userPoints;
        }
    }
    User user1 = new User(10);*/





    /*DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("points");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for (DataSnapshot snapshot: dataSnapshot.getChildren() ) {
                //Button button = (Button)findViewById(R.id.button01);
                //button.setOnClickListener(new View.OnClickListener() {
                //@Override
                //public void onClick(View v) {


                String rewards = dataSnapshot.getValue().toString();
                final int intDisplayRewards = Integer.parseInt(rewards);
                //String displayRewards = String.valueOf(intDisplayRewards);
                final int newValue = intDisplayRewards + 10;
                String displayRewards = String.valueOf(newValue);

                points.setText(displayRewards);
                final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("points");
                reference1.setValue(displayRewards);
                //}
                //});

                //points.notify();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //String rewards = reference.toString();
        //points.setText(rewards);

        //public void displayPoint () {
        //if (i<100){
        // i+=10;
        //}
    }

    /*private void displayPoints() {
        final TextView pointsView = (TextView) findViewById(R.id.displayPoints);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double points = 0.0;
                for (points = 0.0; points < 100; points += 10) {

                    String pointsStr = String.format(Locale.getDefault(), "%1$,.2f points", points);
                    pointsView.setText(pointsStr);
                    handler.postDelayed(this, 1000);
                }
            }

        });
    }*/





    public void entries(){

        /*int newRewards = rewards + 10;
        String stringNewRewards = String.valueOf(newRewards);
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("pointsAddition");
        reference1.setValue(stringNewRewards);
        points.setText(stringNewRewards);*/
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        //FirebaseDatabase.getInstance().getReference().child("users").child("points_addition").setValue(10);



        //DatabaseReference setPoint = FirebaseDatabase.getInstance().getReference();
        //setPoint.child("users").child(userId).child("points_addition").setValue(10);
       /* DocumentReference pointsReference = fStore.collection("users").document(userId);
        pointsReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                assert value != null;
                String rewards = value.getString("initial_points");
                assert rewards != null;
                int intDisplayRewards = Integer.parseInt(rewards);
                int newValue = intDisplayRewards + 10;
                String stringNewValue = String.valueOf(newValue);
                Map<String,Object> user = new HashMap<>();
                user.replace("initial_points",stringNewValue);
                pointsReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       Log.d(TAG,"points are added");
                    }
                });

            }
        });*/

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users/" + userId + "/points_addition");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //for (DataSnapshot snapshot: dataSnapshot.getChildren() ) {
                    //Button button = (Button)findViewById(R.id.button01);
                    //button.setOnClickListener(new View.OnClickListener() {
                    //@Override
                    //public void onClick(View v) {
                    //for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String rewards =dataSnapshot.getValue().toString();
                        //String rewards = dataSnapshot.getValue().toString();
                        int intDisplayRewards = Integer.parseInt(rewards);
                        //String displayRewards = String.valueOf(intDisplayRewards);
                        int newValue = intDisplayRewards + 10;


                        String displayRewards = String.valueOf(newValue);
                        //points.setText(displayRewards);
                        //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("pointsAddition");

                        reference.setValue(newValue);


                        //reference.setValue(newValue);



                        //}
                        //}


                        //}
                        //});

                        //points.notify();



                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }


            }

        //reference.setValue(initialPoints);
















//points.setText("vwbkhviu");




