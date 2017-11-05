package es.esy.android_inyourhand.myapplication.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.esy.android_inyourhand.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodOrderFragment extends Fragment {

    int num, harga, jumlahharga;
    TextView angka, total;
    Button kurang, tambah, order;
    CheckBox checkteh, checkkopi;
    EditText editText;
    TextInputLayout inputLayoutNama;

    public FoodOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_order, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.food_order);
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = view.findViewById(R.id.edit);
        editText.addTextChangedListener(new MyTextWatcher(editText));
        inputLayoutNama = view.findViewById(R.id.input_nama);
        checkteh = view.findViewById(R.id.checkBoxteh);
        checkkopi = view.findViewById(R.id.checkBoxkopi);
        angka = view.findViewById(R.id.jumlahtext);
        total = view.findViewById(R.id.print);
        kurang = view.findViewById(R.id.buttonkurang);
        tambah = view.findViewById(R.id.buttontambah);
        order = view.findViewById(R.id.buttonorder);
        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num==0){
                    Toast.makeText(getActivity(), "Maaf jumlah minimum pembelian adalah 1", Toast.LENGTH_SHORT).show();
                    angka.setText("1");
                    return;
                }
                if (!validateNama()){
                    return;
                }
                String name, print, pesan = "";
                name = editText.getText().toString();
                num = num - 1;
                angka.setText(String.valueOf(num));
                harga = 0;
                if (checkteh.isChecked()){
                    pesan = pesan+"Teh Manis, ";
                    harga = harga + 3000;
                }
                if (checkkopi.isChecked()){
                    pesan = pesan+"Kopi Pahit, ";
                    harga = harga + 5000;
                }
                jumlahharga = harga * num;
                print = name+" anda  membeli "+pesan+"sebanyak "+num+" dan perlu membayar sejumlah Rp. "+jumlahharga;
                total.setText(print);
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateNama()){
                    return;
                }
                String name, print, pesan = "";
                name = editText.getText().toString();
                num = num + 1;
                angka.setText(String.valueOf(num));
                harga = 0;
                if (checkteh.isChecked()){
                    pesan = pesan+"Teh Manis, ";
                    harga = harga + 3000;
                }
                if (checkkopi.isChecked()){
                    pesan = pesan+"Kopi Pahit, ";
                    harga = harga + 5000;
                }
                jumlahharga = harga * num;
                print = name+" anda  membeli "+pesan+"sebanyak "+num+" dan perlu membayar sejumlah Rp. "+jumlahharga;
                total.setText(print);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num==0){
                    Toast.makeText(getActivity(), "Maaf jumlah minimum pembelian adalah 1", Toast.LENGTH_SHORT).show();
                    angka.setText("1");
                    return;
                }
                if (!validateNama()){
                    return;
                }
                Toast.makeText(getActivity(), "Minuman anda akan segera dibuatkan", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateNama(){
        if (editText.getText().toString().trim().isEmpty()){
            inputLayoutNama.setError("Harap masukan nama anda!!");
            requestFocus(editText);
            return false;
        } else {
            inputLayoutNama.setErrorEnabled(false);
        }
        return true;
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_nama:
                    validateNama();
                    break;
            }
        }
    }

}
