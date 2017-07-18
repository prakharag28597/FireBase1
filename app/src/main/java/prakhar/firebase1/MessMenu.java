package prakhar.firebase1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;

/**
 * Created by prakharag on 13-07-2017.
 */

public class MessMenu extends Activity {
    DatabaseReference myRef;
    Spinner spinner_day,spinner_time;
    Button mess_button;
    ProgressDialog pd;
    LinearLayout layout;
    CircleButton mess_complaint;
    private static final String FIREBASE_STRING ="https://testfirebase-698a9.firebaseio.com/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        android.app.ActionBar action_bar=getActionBar();
        action_bar.setTitle("Mess Menu");
        action_bar.setHomeButtonEnabled(true);
        action_bar.setDisplayHomeAsUpEnabled(true);
        layout=(LinearLayout)findViewById(R.id.linear_layout);
        mess_button=(Button)findViewById(R.id.mess_button);
        mess_complaint=(CircleButton)findViewById(R.id.mess_complaint);

        spinner_day = (Spinner)findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> day_adapter = ArrayAdapter.createFromResource(
                this, R.array.day_array, android.R.layout.simple_spinner_dropdown_item);
        day_adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_day.setAdapter(day_adapter);

        spinner_time = (Spinner)findViewById(R.id.spinner_time);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(
                this, R.array.time_array, android.R.layout.simple_spinner_dropdown_item);
        time_adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_time.setAdapter(time_adapter);

        mess_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"yaaaay",Toast.LENGTH_SHORT).show();
            }
        });


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("AIzaSyCK2CmvjJZSZ-OE82fEZk3EuT90BPZfCLM")
                .setApplicationId("1:963852431486:android:82749dade78a8692")
                .setDatabaseUrl("https://testfirebase-698a9.firebaseio.com/")
                .build();
        FirebaseApp menuApp=null;
        // this is some real shit
        try{
            menuApp = FirebaseApp.getInstance("TestFirebase");
        }catch (Exception e){
            menuApp = FirebaseApp.initializeApp(getApplicationContext(), options,"TestFirebase");
        }
        FirebaseDatabase menuDatabase = FirebaseDatabase.getInstance(menuApp);

        myRef=menuDatabase.getReference();

        mess_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeAllViews();
                pd=new ProgressDialog(MessMenu.this);
                pd.setMessage("Getting food!");
                pd.setCancelable(false);
                pd.show();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        pd.dismiss();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DayMenu day = postSnapshot.getValue(DayMenu.class);
                            String today = day.getDay();
                            if(today.equals(spinner_day.getSelectedItem().toString())){
                                ArrayList<Menu> menu_list=day.getMenulist();
                                for(Menu each_menu: menu_list){
                                    Log.d("fdsfsdfsdfs","--------------");
                                    String time=each_menu.getTime();
                                    if(spinner_time.getSelectedItem().toString().equals("Complete")){
                                        // get complete days schedule
                                        String menu=each_menu.getMenu();
                                        View child = getLayoutInflater().inflate(R.layout.menuview, null);
                                        layout.addView(child);
                                        TextView menu_text = (TextView) child.findViewById(R.id.menu_text);
                                        TextView time_text = (TextView) child.findViewById(R.id.time_text);
                                        time_text.setText(time+" :");
                                        menu_text.setText(menu);
                                    }
                                    if(time.equals(spinner_time.getSelectedItem().toString())){
                                        String menu=each_menu.getMenu();
                                        View child = getLayoutInflater().inflate(R.layout.menuview, null);
                                        layout.addView(child);
                                        TextView menu_text = (TextView) child.findViewById(R.id.menu_text);
                                        TextView time_text = (TextView) child.findViewById(R.id.time_text);
                                        time_text.setText(time+" :");
                                        menu_text.setText(menu);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, Option.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
