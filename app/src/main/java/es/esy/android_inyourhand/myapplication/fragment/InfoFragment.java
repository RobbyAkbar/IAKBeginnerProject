package es.esy.android_inyourhand.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.esy.android_inyourhand.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    ArrayList<DetailBaris> list;
    DetailBaris detailBaris;
    AdapterSaya adapterSaya;
    ListView listView;

    private static final String[] text = new String[]{
            "IAK Project Beginner",
            "Created by Robby Akbar",
            " WhatsApp 0896-6654-9850",
            " www.facebook.com/robby.akbar.75",
            " www.android-inyourhand.esy.es",
            "Copyright © 2017 Sinau Academy",
            " www.facebook.com/sinauacademy",
            " www.sinauacademy.com",
    };

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        list = new ArrayList<>();
        adapterSaya = new AdapterSaya(getActivity().getApplicationContext(), list);
        listView = view.findViewById(R.id.list_view_about);
        listView.setAdapter(adapterSaya);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Drawable[]icon = new Drawable[8];
                icon[0] = getResources().getDrawable(R.mipmap.ic_launcher);
                icon[1] = null;
                icon[2] = getResources().getDrawable(R.drawable.wa);
                icon[3] = getResources().getDrawable(R.drawable.fb);
                icon[4] = getResources().getDrawable(R.drawable.www);
                icon[5] = null;
                icon[6] = getResources().getDrawable(R.drawable.fb);
                icon[7] = getResources().getDrawable(R.drawable.sinau);

                list.clear();
                for (int i=0;i<text.length;i++){
                    detailBaris = new DetailBaris();
                    detailBaris.string = text[i];
                    detailBaris.drawable = icon[i];
                    list.add(detailBaris);
                }

                adapterSaya.notifyDataSetChanged();
                listView.setOnItemClickListener(detectClick());

            }
        }, 100);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tentang_aplikasi);
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

    private AdapterView.OnItemClickListener detectClick(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (text[i].contains("WhatsApp")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+6289666549850&text=Hallo%20Robby%20Akbar")), "Buka dengan"));
                }
                if (text[i].contains(".com")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + text[i].trim())), "Buka dengan"));
                }
                if (text[i].contains(".esy.es")){
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + text[i].trim())), "Buka dengan"));
                }
            }
        };
    }

    private class DetailBaris{
        Drawable drawable;
        String string;
    }

    private class AdapterSaya extends BaseAdapter {
        List<DetailBaris> list = new ArrayList<>();
        LayoutInflater layoutInflater;
        AdapterSaya(Context context, ArrayList<DetailBaris>list){
            layoutInflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                view = layoutInflater.inflate(R.layout.tentang_list, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = view.findViewById(R.id.about_row_ikon);
                viewHolder.textView = view.findViewById(R.id.about_row_text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)view.getTag();
            }
            if (list.get(i)!=null){
                viewHolder.imageView.setImageDrawable(list.get(i).drawable);
                viewHolder.textView.setText(list.get(i).string);
            }
            return view;
        }

        class ViewHolder{
            ImageView imageView;
            TextView textView;
        }

    }

}
