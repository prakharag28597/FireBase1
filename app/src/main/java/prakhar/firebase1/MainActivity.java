package prakhar.firebase1;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends Activity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    Button bus_button;
    Spinner spinner_day,spinner_to,spinner_from;
    String query_day,query_to,query_from;
    LinearLayout item;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);
        getActionBar().setTitle("Bus Schedule");
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        bus_button=(Button)findViewById(R.id.bus_button) ;
        item = (LinearLayout) findViewById(R.id.linear_layout);

        spinner_day = (Spinner)findViewById(R.id.spinner_day);
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
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("loading");
                pd.show();
                pd.setCancelable(false);
                // getting the query day
                query_day = spinner_day.getSelectedItem().toString();
                // getting query from and to
                query_from = spinner_from.getSelectedItem().toString();
                query_to = spinner_to.getSelectedItem().toString();
                // setting database reference
                database= FirebaseDatabase.getInstance();
                myRef=database.getReference();
                // getting data from database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        pd.dismiss();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DayBus day = postSnapshot.getValue(DayBus.class);
                            String today = day.getDay();
                            // if query day is the day in database
                            if(today.equals(query_day)) {
                                ArrayList<Bus> bus_list=day.getBuslist();
                                item.removeAllViews();
                                for(Bus each_bus: bus_list){
                                    String start=each_bus.getStart();
                                    String end=each_bus.getEnd();
                                    String via1=each_bus.getVia1();
                                    String via2=each_bus.getVia2();
                                    int busnumber=each_bus.getBusnumber();
                                    String time=each_bus.getTime();
                                    if(start.equals(query_from)){
                                        if(via1.equals(query_to) || via2.equals(query_to) || end.equals(query_to)){
                                            View child = getLayoutInflater().inflate(R.layout.listview, null);
                                            item.addView(child);
                                            TextView bus_number_text = (TextView) child.findViewById(R.id.bus_number_text);
                                            TextView from_text = (TextView) child.findViewById(R.id.from_text);
                                            TextView via1_text = (TextView) child.findViewById(R.id.via1_text);
                                            TextView via2_text = (TextView) child.findViewById(R.id.via2_text);
                                            TextView end_text = (TextView) child.findViewById(R.id.end_text);
                                            TextView time_text = (TextView) child.findViewById(R.id.time_text);
                                            bus_number_text.setText(Integer.toString(busnumber));
                                            from_text.setText(start);
                                            via1_text.setText(via1);
                                            via2_text.setText(via2);
                                            end_text.setText(end);
                                            time_text.setText(time);
                                        }
                                    }else if(via1.equals(query_from)){
                                        if(via2.equals(query_to) || end.equals(query_to)){
                                            View child = getLayoutInflater().inflate(R.layout.listview, null);
                                            item.addView(child);
                                            TextView bus_number_text = (TextView) child.findViewById(R.id.bus_number_text);
                                            TextView from_text = (TextView) child.findViewById(R.id.from_text);
                                            TextView via1_text = (TextView) child.findViewById(R.id.via1_text);
                                            TextView via2_text = (TextView) child.findViewById(R.id.via2_text);
                                            TextView end_text = (TextView) child.findViewById(R.id.end_text);
                                            TextView time_text = (TextView) child.findViewById(R.id.time_text);
                                            bus_number_text.setText(Integer.toString(busnumber));
                                            from_text.setText(start);
                                            via1_text.setText(via1);
                                            via2_text.setText(via2);
                                            end_text.setText(end);
                                            time_text.setText(time);
                                        }
                                    }else if(via2.equals(query_from)){
                                        if(end.equals(query_to)){
                                            View child = getLayoutInflater().inflate(R.layout.listview, null);
                                            item.addView(child);
                                            TextView bus_number_text = (TextView) child.findViewById(R.id.bus_number_text);
                                            TextView from_text = (TextView) child.findViewById(R.id.from_text);
                                            TextView via1_text = (TextView) child.findViewById(R.id.via1_text);
                                            TextView via2_text = (TextView) child.findViewById(R.id.via2_text);
                                            TextView end_text = (TextView) child.findViewById(R.id.end_text);
                                            TextView time_text = (TextView) child.findViewById(R.id.time_text);
                                            bus_number_text.setText(Integer.toString(busnumber));
                                            from_text.setText(start);
                                            via1_text.setText(via1);
                                            via2_text.setText(via2);
                                            end_text.setText(end);
                                            time_text.setText(time);
                                        }
                                    }
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, Option.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
