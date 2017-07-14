package prakhar.firebase1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by prakharag on 13-07-2017.
 */

public class MessMenu extends AppCompatActivity {
    DatabaseReference myRef;
    private static final String FIREBASE_STRING ="https://testfirebase-698a9.firebaseio.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("AIzaSyCK2CmvjJZSZ-OE82fEZk3EuT90BPZfCLM")
                .setApplicationId("1:963852431486:android:82749dade78a8692")
                .setDatabaseUrl("https://testfirebase-698a9.firebaseio.com/")
                .build();
        FirebaseApp menuApp = FirebaseApp.initializeApp(getApplicationContext(), options, "menuApp");

        FirebaseDatabase menuDatabase = FirebaseDatabase.getInstance(menuApp);

        //secondDatabase.getReference().setValue(ServerValue.TIMESTAMP);
        myRef=menuDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DayMenu day = postSnapshot.getValue(DayMenu.class);
                    String today=day.getDay();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        


    }
}
