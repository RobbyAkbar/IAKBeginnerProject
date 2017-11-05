package es.esy.android_inyourhand.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import es.esy.android_inyourhand.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourtCounterFragment extends Fragment {

    int pointa, pointb;
    Button point3a, point2a, freethrowa, point3b, point2b, freethrowb, reset;
    TextView scorea, scoreb;

    public CourtCounterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_court_counter, container, false);

        point3a = view.findViewById(R.id.pointa3);
        point2a = view.findViewById(R.id.pointa2);
        point3b = view.findViewById(R.id.pointb3);
        point2b = view.findViewById(R.id.pointb2);
        freethrowa = view.findViewById(R.id.freethrowa);
        freethrowb = view.findViewById(R.id.freethrowb);
        reset = view.findViewById(R.id.reset);
        scorea = view.findViewById(R.id.scorea);
        scoreb = view.findViewById(R.id.scoreb);
        scorea.setText(String.valueOf(pointa));
        scoreb.setText(String.valueOf(pointb));

        point3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointa = pointa + 3;
                scorea.setText(String.valueOf(pointa));
            }
        });
        point2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointa = pointa + 2;
                scorea.setText(String.valueOf(pointa));
            }
        });
        freethrowa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointa = pointa + 5;
                scorea.setText(String.valueOf(pointa));
            }
        });
        point3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointb = pointb + 3;
                scoreb.setText(String.valueOf(pointb));
            }
        });
        point2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointb = pointb + 2;
                scoreb.setText(String.valueOf(pointb));
            }
        });
        freethrowb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointb = pointb + 5;
                scoreb.setText(String.valueOf(pointb));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointa = 0;
                pointb = 0;
                scorea.setText(String.valueOf(pointa));
                scoreb.setText(String.valueOf(pointb));
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.court_counter);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

}
