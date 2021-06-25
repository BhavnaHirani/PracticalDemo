package com.bizita.tatvademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bizita.tatvademo.databinding.ActivityMainBinding;
import com.bizita.tatvademo.prect1.PrectOneActivity;
import com.bizita.tatvademo.prect2.PrectTwoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnPr1.setOnClickListener(this);
        binding.btnPr2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnPr1:
                Intent intent1 = new Intent(this, PrectOneActivity.class);
                startActivity(intent1);
                break;

            case R.id.btnPr2:
                Intent intent2 = new Intent(this, PrectTwoActivity.class);
                startActivity(intent2);
                break;


        }
    }
}