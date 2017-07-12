package prakhar.firebase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        Log.d("myTag", "Inside main activity");

        database= FirebaseDatabase.getInstance();

        database.setPersistenceEnabled(true);
        myRef=database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("myTag", "This is my message");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    DayBus day = postSnapshot.getValue(DayBus.class);

                    String today = day.getDay();
                    ArrayList<Bus> bus_list= day.getBusList();

//                    int bus_number = day.getBus().getBusNumber();
//                    String start = day.getBus().getStart();
//                    String end = day.getBus().getEnd();
//                    String via = day.getBus().getVia();
//                    String time = day.getBus().getTime();


                    System.out.println("time" + today);
                    Toast.makeText(getApplicationContext(), "time" + today, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
