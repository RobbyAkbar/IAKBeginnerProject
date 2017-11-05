package es.esy.android_inyourhand.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.esy.android_inyourhand.myapplication.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    new ListFragment(), ListFragment.class.getSimpleName()).commit();
        }

    }
}
