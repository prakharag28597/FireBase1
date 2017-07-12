package prakhar.firebase1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by prakharag on 12-07-2017.
 */

public class Intro extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startActivity = new Intent(Intro.this, Option.class);
                startActivity(startActivity);
                finish();
            }
        }, 3500);
    }
}
