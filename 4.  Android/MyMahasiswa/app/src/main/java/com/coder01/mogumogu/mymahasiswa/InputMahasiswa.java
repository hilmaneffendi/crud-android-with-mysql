package com.coder01.mogumogu.mymahasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by MOGU MOGU on 29/07/2016.
 */
public class InputMahasiswa extends AppCompatActivity implements View.OnClickListener {
    EditText etNpm, etNama, etJurusan;
    Button btnSimpan, btnLihat, btnBatal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mahasiswa);

        etNpm = (EditText)findViewById(R.id.et_npm);
        etNama = (EditText)findViewById(R.id.et_id);
        etJurusan = (EditText)findViewById(R.id.et_jurusan);

        btnSimpan = (Button)findViewById(R.id.btn_simpan);
        btnLihat = (Button)findViewById(R.id.btn_lihat);
        btnBatal = (Button)findViewById(R.id.btn_batal);

        btnSimpan.setOnClickListener(this);
        btnLihat.setOnClickListener(this);
    }
    private void addMahasiswa(){
        final String npm = etNpm.getText().toString().trim();
        final String nama = etNama.getText().toString().trim();
        final String jurusan = etJurusan.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputMahasiswa.this,"Menambahkan data...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputMahasiswa.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_MHS_NPM,npm);
                params.put(Config.KEY_MHS_NAMA,nama);
                params.put(Config.KEY_MHS_JURUSAN,jurusan);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();

    }

    @Override
    public void onClick(View v) {
        if(v == btnSimpan){
            addMahasiswa();
        }
        if(v == btnLihat){
            startActivity(new Intent(this,TampilMahasiswa.class));
        }

    }
}