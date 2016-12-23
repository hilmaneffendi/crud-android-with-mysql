package com.coder01.mogumogu.mymahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnInput, btnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnInput = (Button)findViewById(R.id.btn_input);
        btnList = (Button)findViewById(R.id.btn_list);

        btnInput.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == btnInput){
            startActivity(new Intent(this,InputMahasiswa.class));
        }
        if(v == btnList){
            startActivity(new Intent(this,TampilMahasiswa.class));
        }

    }
}
