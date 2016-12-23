package com.coder01.mogumogu.mymahasiswa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by MOGU MOGU on 29/07/2016.
 */
public class TampilDetailMahasiswa extends AppCompatActivity implements View.OnClickListener{

    private EditText etId, etNpm, etNama, etJurusan;
    private Button btnPerbaharui;
    private Button btnHapus;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.MHS_ID);

        etId = (EditText) findViewById(R.id.et_id);
        etNpm = (EditText) findViewById(R.id.et_npm);
        etNama = (EditText) findViewById(R.id.et_nama);
        etJurusan = (EditText) findViewById(R.id.et_jurusan);

        btnPerbaharui = (Button) findViewById(R.id.btn_perbaharui);
        btnHapus = (Button) findViewById(R.id.btn_hapus);

        btnPerbaharui.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

        etId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetailMahasiswa.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_MHS,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String npm = c.getString(Config.TAG_NPM);
            String nama = c.getString(Config.TAG_NAMA);
            String jurusan = c.getString(Config.TAG_JURUSAN);

            etNpm.setText(npm);
            etNama.setText(nama);
            etJurusan.setText(jurusan);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void perbaharuiData(){
        final String npm = etNpm.getText().toString().trim();
        final String nama = etNama.getText().toString().trim();
        final String jurusan = etJurusan.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetailMahasiswa.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilDetailMahasiswa.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_MHS_ID,id);
                hashMap.put(Config.KEY_MHS_NPM,npm);
                hashMap.put(Config.KEY_MHS_NAMA,nama);
                hashMap.put(Config.KEY_MHS_JURUSAN,jurusan);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_MHS,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilDetailMahasiswa.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilDetailMahasiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_MHS, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void konfirmasiHapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin akan menghapus data tersebut ?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilDetailMahasiswa.this,TampilMahasiswa.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnPerbaharui){
            perbaharuiData();
        }

        if(v == btnHapus){
            konfirmasiHapus();
        }
    }
}
