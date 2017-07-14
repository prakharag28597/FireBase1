package prakhar.firebase1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

/**
 * Created by prakharag on 13-07-2017.
 */

public class MessMenu extends AppCompatActivity {
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
        FirebaseDatabase secondDatabase = FirebaseDatabase.getInstance(menuApp);
        secondDatabase.getReference().setValue(ServerValue.TIMESTAMP);
        


    }
}
