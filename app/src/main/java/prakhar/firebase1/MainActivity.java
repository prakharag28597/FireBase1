package prakhar.firebase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
    Button bus_button;
    Spinner spinner_day,spinner_to,spinner_from;
    String query_day,query_to,query_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        bus_button=(Button)findViewById(R.id.bus_button) ;

        final Spinner spinner_day = (Spinner)findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> day_adapter = ArrayAdapter.createFromResource(
                this, R.array.day_array, android.R.layout.simple_spinner_dropdown_item);
        day_adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_day.setAdapter(day_adapter);

        spinner_from = (Spinner)findViewById(R.id.spinner_from);
        ArrayAdapter<CharSequence> from_adapter = ArrayAdapter.createFromResource(
                this, R.array.to_array, android.R.layout.simple_spinner_dropdown_item);
        from_adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_from.setAdapter(from_adapter);

        spinner_to = (Spinner)findViewById(R.id.spinner_to);
        ArrayAdapter<CharSequence> to_adapter = ArrayAdapter.createFromResource(
                this, R.array.from_array,android.R.layout.simple_spinner_dropdown_item);
        to_adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_to.setAdapter(to_adapter);

        bus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query_day = spinner_day.getSelectedItem().toString();
                query_from = spinner_from.getSelectedItem().toString();
                query_to = spinner_to.getSelectedItem().toString();
                // setting database reference
                database= FirebaseDatabase.getInstance();
                myRef=database.getReference();
                // getting data from database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DayBus day = postSnapshot.getValue(DayBus.class);
                            String today = day.getDay();

                            // if query day is the day in database
                            if(today.equals(query_day)) {
                                ArrayList<Bus> bus_list=day.getBuslist();
                                for(Bus each_bus: bus_list){
                                    String start=each_bus.getStart();
                                    String end=each_bus.getEnd();
                                    String via1=each_bus.getVia1();
                                    String via2=each_bus.getVia2();

                                    Log.d("start",start);
                                    Log.d("via1",via1);
                                    Log.d("via2",via2);
                                    Log.d("end",end);
                                    //query_route
                                }

                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });




    }
}
