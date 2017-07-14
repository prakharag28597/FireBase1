package prakhar.firebase1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by prakharag on 12-07-2017.
 */

public class Option extends AppCompatActivity {

    ImageView bus, food, contact, classroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        bus=(ImageView)findViewById(R.id.bus);
        food=(ImageView)findViewById(R.id.food);
        contact=(ImageView)findViewById(R.id.contact);
        classroom=(ImageView)findViewById(R.id.classroom);

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Option.this, MainActivity.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Option.this, MessMenu.class);
                startActivity(intent);
            }
        });
        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
